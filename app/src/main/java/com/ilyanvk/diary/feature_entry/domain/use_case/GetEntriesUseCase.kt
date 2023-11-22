package com.ilyanvk.diary.feature_entry.domain.use_case

import com.ilyanvk.diary.feature_entry.domain.model.Entry
import com.ilyanvk.diary.feature_entry.domain.repository.EntryRepository
import com.ilyanvk.diary.feature_entry.domain.util.EntryOrder
import com.ilyanvk.diary.feature_entry.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetEntriesUseCase(
    private val entryRepository: EntryRepository
) {

    operator fun invoke(
        entryOrder: EntryOrder = EntryOrder.ByTimeCreated(OrderType.Descending)
    ): Flow<List<Entry>> {
        return entryRepository.getEntries().map { entries ->
            when (entryOrder.orderType) {
                is OrderType.Ascending -> {
                    when (entryOrder) {
                        is EntryOrder.ByTimeCreated -> entries.sortedBy { it.timeCreated }
                        is EntryOrder.ByTimeModified -> entries.sortedBy { it.timeModified }
                    }
                }

                is OrderType.Descending -> {
                    when (entryOrder) {
                        is EntryOrder.ByTimeCreated -> entries.sortedByDescending { it.timeCreated }
                        is EntryOrder.ByTimeModified -> entries.sortedByDescending { it.timeModified }
                    }
                }
            }
        }
    }
}