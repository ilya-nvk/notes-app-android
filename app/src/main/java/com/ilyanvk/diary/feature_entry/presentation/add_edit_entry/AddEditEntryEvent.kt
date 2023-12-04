package com.ilyanvk.diary.feature_entry.presentation.add_edit_entry

import androidx.compose.ui.focus.FocusState

sealed class AddEditEntryEvent {
    data class EnteredTitle(val value: String) : AddEditEntryEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditEntryEvent()
    data class EnteredContent(val value: String) : AddEditEntryEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditEntryEvent()
    data object SaveEntry : AddEditEntryEvent()
}