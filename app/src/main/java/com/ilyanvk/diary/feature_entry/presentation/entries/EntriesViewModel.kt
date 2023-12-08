package com.ilyanvk.diary.feature_entry.presentation.entries

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.use_case.EntryUseCases
import com.ilyanvk.diary.feature_entry.domain.util.EntryOrder
import com.ilyanvk.diary.feature_entry.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntriesViewModel @Inject constructor(
    private val entryUseCases: EntryUseCases
) : ViewModel() {

    private val _state = mutableStateOf(EntriesState())
    val state: State<EntriesState> = _state

    private var lastDeletedEntry: Entry? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(EntryOrder.ByTimeCreated(OrderType.Descending))
    }

    fun onEvent(event: EntriesEvent) {
        when (event) {
            is EntriesEvent.DeleteEntry -> {
                viewModelScope.launch(Dispatchers.IO) {
                    entryUseCases.deleteEntryUseCase(event.entry)
                    lastDeletedEntry = event.entry
                }
            }

            EntriesEvent.RestoreEntry -> {
                viewModelScope.launch(Dispatchers.IO) {
                    entryUseCases.addEntryUseCase(lastDeletedEntry ?: return@launch)
                    lastDeletedEntry = null
                }
            }

            is EntriesEvent.Order -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (event.entryOrder::class == state.value.entryOrder::class && event.entryOrder.orderType == state.value.entryOrder.orderType) {
                        return@launch
                    }
                    getNotes(event.entryOrder)
                }
            }

            EntriesEvent.ToggleOrderSection -> {
                val isOrderSectionVisible = state.value.isOrderSectionVisible
                _state.value = state.value.copy(isOrderSectionVisible = !isOrderSectionVisible)
            }
        }
    }

    private fun getNotes(entryOrder: EntryOrder) {
        getNotesJob?.cancel()
        getNotesJob = entryUseCases.getEntriesUseCase(entryOrder).onEach { entries ->
            _state.value = state.value.copy(
                entryOrder = entryOrder, entries = entries
            )
        }.launchIn(viewModelScope)
    }

}
