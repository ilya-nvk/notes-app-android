package com.ilyanvk.diary.feature_entry.presentation.add_edit_entry

sealed class AddEditEntryEvent {
    data class EnteredTitle(val value: String) : AddEditEntryEvent()
    data class EnteredContent(val value: String) : AddEditEntryEvent()
    data object SaveEntry : AddEditEntryEvent()
}
