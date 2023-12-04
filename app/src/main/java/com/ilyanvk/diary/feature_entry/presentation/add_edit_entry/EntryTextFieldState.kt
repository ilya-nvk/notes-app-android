package com.ilyanvk.diary.feature_entry.presentation.add_edit_entry

import com.ilyanvk.diary.R

data class EntryTextFieldState(
    val text: String = "",
    val hintId: Int = R.id.empty_string,
    val isHintVisible: Boolean = true
)
