package com.ilyanvk.diary.feature_entry.presentation.util

sealed class Screen(val route: String) {
    data object EntriesScreen : Screen("entries_screen")
    data object AddEditEntryScreen : Screen("add_edit_entry_screen")
}
