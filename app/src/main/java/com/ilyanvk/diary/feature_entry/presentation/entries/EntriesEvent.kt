package com.ilyanvk.diary.feature_entry.presentation.entries

import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.util.EntryOrder

sealed class EntriesEvent {
    data class Order(val entryOrder: EntryOrder) : EntriesEvent()
    data class DeleteEntry(val entry: Entry) : EntriesEvent()
    data object RestoreEntry : EntriesEvent()
    data object ToggleOrderSection : EntriesEvent()
}