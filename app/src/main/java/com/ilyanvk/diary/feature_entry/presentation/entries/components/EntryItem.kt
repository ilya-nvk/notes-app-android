package com.ilyanvk.diary.feature_entry.presentation.entries.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyanvk.diary.feature_entry.domain.model.Entry

@Composable
fun EntryItem(
    entry: Entry,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick() }
                    .padding(16.dp)
                    .padding(end = 32.dp)
            ) {
                Text(
                    text = entry.title,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = entry.content,
                    style = MaterialTheme.typography.bodySmall,
                    minLines = 3,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(
                onClick = onDeleteClick, modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete, contentDescription = "Delete note"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewEntryItem() {
    EntryItem(
        entry = Entry(
            title = "Title",
            content = "Content content content content content content content content content content content content content",
            timeCreated = 0,
            timeModified = 0,
            id = ""
        ),
        onClick = {},
        onDeleteClick = { }
    )
}
