package com.ilyanvk.diary.feature_entry.presentation.entries

import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.util.EntryOrder
import com.ilyanvk.diary.feature_entry.domain.util.OrderType

data class EntriesState(
    val entries: List<Entry> = emptyList(),
    val entryOrder: EntryOrder = EntryOrder.ByTimeCreated(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)