package com.ilyanvk.diary.feature_entry.presentation.add_edit_entry

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyanvk.diary.R
import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.model.InvalidEntryException
import com.ilyanvk.diary.feature_entry.domain.use_case.EntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditEntryViewModel @Inject constructor(
    private val entriesUseCases: EntryUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _entryTitle = mutableStateOf(
        EntryTextFieldState(
            hintId = R.string.enter_title
        )
    )
    val entryTitle: State<EntryTextFieldState> = _entryTitle

    private val _entryContent = mutableStateOf(
        EntryTextFieldState(
            hintId = R.string.enter_content
        )
    )
    val entryContent: State<EntryTextFieldState> = _entryContent

    private var timeCreated: Long? = null
    private var currentEntryId: String = ""

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("entryId")?.let { entryId ->
            if (entryId.isNotBlank()) {
                viewModelScope.launch {
                    entriesUseCases.getEntryByIdUseCase(entryId)?.also { entry ->
                        currentEntryId = entry.id
                        _entryTitle.value = entryTitle.value.copy(
                            text = entry.title,
                            isHintVisible = false
                        )
                        _entryContent.value = entryContent.value.copy(
                            text = entry.content,
                            isHintVisible = false
                        )
                        timeCreated = entry.timeCreated
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditEntryEvent) {
        when (event) {
            is AddEditEntryEvent.EnteredTitle -> {
                _entryTitle.value = entryTitle.value.copy(text = event.value)
            }

            is AddEditEntryEvent.ChangeTitleFocus -> {
                _entryTitle.value = entryTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && entryTitle.value.text.isBlank()
                )
            }

            is AddEditEntryEvent.EnteredContent -> {
                _entryContent.value = entryContent.value.copy(text = event.value)
            }

            is AddEditEntryEvent.ChangeContentFocus -> {
                _entryContent.value = entryContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && entryContent.value.text.isBlank()
                )
            }

            AddEditEntryEvent.SaveEntry -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        entriesUseCases.addEntryUseCase(
                            Entry(
                                title = entryTitle.value.text,
                                content = entryContent.value.text,
                                timeModified = System.currentTimeMillis(),
                                timeCreated = timeCreated ?: System.currentTimeMillis(),
                                id = currentEntryId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveEntry)
                    } catch (e: InvalidEntryException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Couldn't save entry"))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveEntry : UiEvent()
    }
}