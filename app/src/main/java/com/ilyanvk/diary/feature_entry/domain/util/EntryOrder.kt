package com.ilyanvk.diary.feature_entry.domain.util

sealed class EntryOrder(val orderType: OrderType) {
    class ByTimeCreated(orderType: OrderType) : EntryOrder(orderType)
    class ByTimeModified(orderType: OrderType) : EntryOrder(orderType)

    fun copy(orderType: OrderType): EntryOrder {
        return when (this) {
            is ByTimeCreated -> ByTimeCreated(orderType)
            is ByTimeModified -> ByTimeModified(orderType)
        }
    }
}
