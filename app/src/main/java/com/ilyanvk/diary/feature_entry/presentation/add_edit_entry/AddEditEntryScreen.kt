package com.ilyanvk.diary.feature_entry.presentation.add_edit_entry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ilyanvk.diary.R
import com.ilyanvk.diary.feature_entry.presentation.add_edit_entry.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditEntryScreen(
    navController: NavController, viewModel: AddEditEntryViewModel = hiltViewModel()
) {
    val titleState = viewModel.entryTitle.value
    val contentState = viewModel.entryContent.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                AddEditEntryViewModel.UiEvent.SaveEntry -> {
                    navController.navigateUp()
                }

                is AddEditEntryViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditEntryEvent.SaveEntry) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = stringResource(R.string.save_entry)
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = titleState.text,
                hintId = titleState.hintId,
                onValueChange = {
                    viewModel.onEvent(AddEditEntryEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditEntryEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = contentState.text,
                hintId = contentState.hintId,
                onValueChange = {
                    viewModel.onEvent(AddEditEntryEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditEntryEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxHeight()
            )
        }

    }
}
