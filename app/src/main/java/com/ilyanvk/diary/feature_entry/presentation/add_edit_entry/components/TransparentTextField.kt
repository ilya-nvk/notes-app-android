package com.ilyanvk.diary.feature_entry.presentation.add_edit_entry.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TransparentTextField(
    text: String,
    hintId: Int,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = modifier.fillMaxWidth()
    ) { innerTextField ->
        if (text.isBlank()) {
            Text(text = stringResource(id = hintId), style = textStyle, color = Color.DarkGray)
        }
        innerTextField.invoke()
    }
}
