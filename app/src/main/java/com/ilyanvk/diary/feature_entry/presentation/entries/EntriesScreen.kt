package com.ilyanvk.diary.feature_entry.presentation.entries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ilyanvk.diary.R
import com.ilyanvk.diary.feature_entry.presentation.entries.components.EntryItem
import com.ilyanvk.diary.feature_entry.presentation.entries.components.OrderSection
import com.ilyanvk.diary.feature_entry.presentation.util.Screen
import kotlinx.coroutines.launch

@Composable
fun EntriesScreen(
    navController: NavController, viewModel: EntriesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(Screen.AddEditEntryScreen.route) },
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_entry)
            )
        }
    }, snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.your_diary),
                    style = MaterialTheme.typography.headlineLarge
                )

                IconButton(onClick = { viewModel.onEvent(EntriesEvent.ToggleOrderSection) }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(R.string.sort)
                    )
                }
            }

            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                    entryOrder = state.entryOrder,
                    onOrderChange = { orderType ->
                        viewModel.onEvent(
                            EntriesEvent.Order(
                                orderType
                            )
                        )
                    })
            }

            Spacer(modifier = Modifier.height(16.dp))

            val entryDeletedString = stringResource(R.string.entry_deleted)
            val undoString = stringResource(R.string.undo)
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.entries) { entry ->
                    EntryItem(entry = entry,
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            navController.navigate(
                                Screen.AddEditEntryScreen.route + "?entryId=${entry.id}"
                            )
                        },
                        onDeleteClick = {
                            viewModel.onEvent(EntriesEvent.DeleteEntry(entry))
                            coroutineScope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = entryDeletedString, actionLabel = undoString
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(EntriesEvent.RestoreEntry)
                                }
                            }
                        })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}