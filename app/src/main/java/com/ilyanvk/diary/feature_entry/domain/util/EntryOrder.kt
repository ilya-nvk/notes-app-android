package com.ilyanvk.diary.feature_entry.domain.util

sealed class EntryOrder(val orderType: OrderType) {
    class ByTimeCreated(orderType: OrderType) : EntryOrder(orderType)
    class ByTimeModified(orderType: OrderType) : EntryOrder(orderType)
}
