package com.ilyanvk.diary.feature_entry.presentation.entries.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilyanvk.diary.R
import com.ilyanvk.diary.feature_entry.domain.util.EntryOrder
import com.ilyanvk.diary.feature_entry.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    entryOrder: EntryOrder = EntryOrder.ByTimeCreated(OrderType.Descending),
    onOrderChange: (EntryOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(text = stringResource(R.string.time_created),
                isSelected = entryOrder is EntryOrder.ByTimeCreated,
                onSelected = { onOrderChange(EntryOrder.ByTimeCreated(entryOrder.orderType)) })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = stringResource(R.string.time_modified),
                isSelected = entryOrder is EntryOrder.ByTimeModified,
                onSelected = { onOrderChange(EntryOrder.ByTimeModified(entryOrder.orderType)) })
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(text = stringResource(R.string.ascending),
                isSelected = entryOrder.orderType is OrderType.Ascending,
                onSelected = { onOrderChange(entryOrder.copy(OrderType.Ascending)) })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = stringResource(R.string.descending),
                isSelected = entryOrder.orderType is OrderType.Descending,
                onSelected = { onOrderChange(entryOrder.copy(OrderType.Descending)) })
        }
    }
}
