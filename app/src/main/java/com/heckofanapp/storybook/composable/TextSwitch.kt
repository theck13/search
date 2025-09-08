package com.heckofanapp.storybook.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.heckofanapp.search.PreviewDarkLight
import com.heckofanapp.search.Token
import com.heckofanapp.search.component.Switch

@Composable
fun TextSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    isDependent: Boolean = false,
    isEnabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
    padding: PaddingValues = PaddingValues(
        all = 0.dp,
    ),
    text: String,
) {
    Switch(
        modifier = modifier.fillMaxWidth(),
        isChecked = if (isDependent) isEnabled && isChecked else isChecked,
        isEnabled = isEnabled,
        onCheckedChange = { checked ->
            onCheckedChange.invoke(checked)
        },
        padding = padding,
        textPrimary = text,
    )
}

@Composable
fun PreviewTextSwitch(
    padding: PaddingValues,
) {
    Column(
        modifier = Modifier.width(
            width = 256.dp,
        )
    ) {
        listOf(true, false).forEach { isChecked ->
            TextSwitch(
                isChecked = isChecked,
                onCheckedChange = {},
                padding = padding,
                text = if (isChecked) "Checked" else "Unchecked",
            )
        }
    }
}

@PreviewDarkLight
@Composable
fun PreviewTextSwitchWithoutPadding() {
    PreviewTextSwitch(
        padding = PaddingValues(
            all = 0.dp,
        )
    )
}

@PreviewDarkLight
@Composable
fun PreviewTextSwitchWithPaddingAll() {
    PreviewTextSwitch(
        padding = PaddingValues(
            all = Token.SpacingMedium,
        )
    )
}

@PreviewDarkLight
@Composable
fun PreviewTextSwitchWithPaddingHorizontal() {
    PreviewTextSwitch(
        padding = PaddingValues(
            horizontal = Token.SpacingMedium,
        )
    )
}

@PreviewDarkLight
@Composable
fun PreviewTextSwitchWithPaddingVertical() {
    PreviewTextSwitch(
        padding = PaddingValues(
            vertical = Token.SpacingMedium,
        )
    )
}
