package com.heckofanapp.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.heckofanapp.search.PreviewDarkLight
import com.heckofanapp.search.Token
import com.heckofanapp.search.internal.Constant
import com.heckofanapp.search.theme.Color
import com.heckofanapp.search.theme.LocalThemeMode
import com.heckofanapp.search.theme.RippleConfiguration
import com.heckofanapp.search.theme.ThemeMode
import androidx.compose.material3.Switch as MaterialSwitch

/**
 * [MaterialSwitch] with optional primary and secondary [Text].
 *
 * @param modifier optional [Modifier] to apply to [MaterialSwitch] and [Text]
 * @param isChecked required [Boolean] to set checked state
 * @param isEnabled optional [Boolean] to set enabled state, [MaterialSwitch] and [Text] are grayed out when false
 * @param onCheckedChange optional [Unit] callback triggered when [MaterialSwitch] is switched
 * @param padding optional [PaddingValues] to apply to layout between container and content
 * @param textPrimary optional [String] at start of [MaterialSwitch] and above secondary [Text]
 * @param textSecondary optional [String] at start of [MaterialSwitch] and below primary [Text]
 */
@Composable
fun Switch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    isEnabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    padding: PaddingValues = PaddingValues(
        all = 0.dp,
    ),
    textPrimary: String? = null,
    textSecondary: String? = null,
) {
    val themeType = LocalThemeMode.current

    val checked = remember { mutableStateOf(isChecked) }
    checked.value = isChecked

    val colorPrimary = if (isEnabled) Color.textIconNeutral else Color.textIconNeutralWeak
    val colorSecondary = if (isEnabled) Color.textIconNeutralWeak else Color.textIconNeutralWeak

    // When there is text, make entire layout clickable/tappable for accessibility.
    val modifierToggleable =
        if (textPrimary.isNullOrEmpty()) {
            modifier
        } else {
            modifier.toggleable(
                enabled = isEnabled,
                onValueChange = {
                    checked.value = checked.value.not()
                    onCheckedChange?.invoke(checked.value)
                },
                role = Role.Switch,
                value = isChecked,
            )
        }

    BoxWithConstraints {
        val endMargin =
            if (constraints.hasFixedWidth) {
                Token.SpacingXs
            } else {
                Token.SpacingMedium
            }

        RippleConfiguration {
            ConstraintLayout(
                modifier = modifierToggleable
                    .padding(
                        paddingValues = padding,
                    )
                    .semantics(
                        mergeDescendants = true,
                    ) {},
            ) {
                val (primary, secondary, switch) = createRefs()

                Text(
                    modifier = Modifier.constrainAs(primary) {
                        bottom.linkTo(
                            anchor = secondary.top,
                        )
                        top.linkTo(
                            anchor = parent.top,
                        )
                        visibility = if (textPrimary.isNullOrEmpty()) Visibility.Gone else Visibility.Visible
                        width = Dimension.preferredWrapContent
                        linkTo(
                            bias = Constant.BiasStartOrTop,
                            end = switch.start,
                            endMargin = endMargin,
                            start = parent.start,
                        )
                    },
                    color = colorPrimary,
                    style = Typography.BodyLarge.Default,
                    text = textPrimary.orEmpty(),
                )

                Text(
                    modifier = Modifier.constrainAs(secondary) {
                        bottom.linkTo(
                            anchor = parent.bottom,
                        )
                        top.linkTo(
                            anchor = primary.bottom,
                        )
                        visibility = if (textPrimary.isNullOrEmpty() || textSecondary.isNullOrEmpty()) Visibility.Gone else Visibility.Visible
                        width = Dimension.preferredWrapContent
                        linkTo(
                            bias = Constant.BiasStartOrTop,
                            end = switch.start,
                            endMargin = endMargin,
                            start = parent.start,
                        )
                    },
                    color = colorSecondary,
                    style = Typography.BodySmall.Default,
                    text = textSecondary.orEmpty(),
                )

                MaterialSwitch(
                    modifier = Modifier.constrainAs(switch) {
                        bottom.linkTo(
                            anchor = parent.bottom,
                        )
                        end.linkTo(
                            anchor = parent.end,
                        )
                        top.linkTo(
                            anchor = parent.top,
                        )
                    }.size(
                        size = 48.dp,
                    ),
                    checked = checked.value,
                    colors = SwitchDefaults.colors(
                        checkedBorderColor = Color.borderNeutralStrong,
                        checkedThumbColor = Token.TextIconNeutralDark,
                        checkedTrackColor = Color.accent,
                        disabledCheckedBorderColor = Color.borderNeutralWeak,
                        disabledCheckedThumbColor = Color.textIconInverse.copy(
                            alpha = if (isEnabled || themeType == ThemeMode.Light) 1.00f else 0.40f,
                        ),
                        disabledCheckedTrackColor = Color.backgroundActivated.copy(
                            alpha = 0.125f,
                        ),
                        disabledUncheckedBorderColor = Color.borderNeutralWeak,
                        disabledUncheckedThumbColor = Color.backgroundActivated,
                        disabledUncheckedTrackColor = Color.borderNeutralWeak,
                        uncheckedBorderColor = Color.backgroundNeutralDisabled,
                        uncheckedThumbColor = Color.backgroundNeutralDisabled.copy(
                            alpha = 0.40f
                        ),
                        uncheckedTrackColor = Color.borderNeutralWeak,
                    ),
                    enabled = isEnabled,
                    onCheckedChange =
                    if (textPrimary.isNullOrEmpty()) {
                        {
                            checked.value = checked.value.not()
                            onCheckedChange?.invoke(checked.value)
                        }
                    } else {
                        null
                    },
                )
            }
        }
    }
}

@PreviewDarkLight
@Composable
fun PreviewSwitch() {
    Row {
        listOf(true, false).forEach { isEnabled ->
            Column(
                modifier = Modifier.padding(
                    all = Token.SpacingMedium,
                ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(
                    space = Token.SpacingSmall,
                ),
            ) {
                listOf(true, false).forEach { isChecked ->
                    Switch(
                        isChecked = isChecked,
                        isEnabled = isEnabled,
                        onCheckedChange = {},
                    )
                }
            }
        }
    }
}

@PreviewDarkLight
@Composable
fun PreviewSwitchPrimary() {
    PreviewSwitchText(
        showSecondary = false,
    )
}

@PreviewDarkLight
@Composable
fun PreviewSwitchPrimarySecondary() {
    PreviewSwitchText(
        showSecondary = true,
    )
}

@Composable
fun PreviewSwitchText(
    showSecondary: Boolean,
) {
    val modifierColumn = Modifier
        .padding(
            all = Token.SpacingMedium,
        )
        .width(
            width = 256.dp,
        )
    val modifierRow = Modifier
    val modifierRowFillWidth = Modifier.fillMaxWidth()
    val pairChecked = "Checked" to "Unchecked"
    val pairEnabled = "Enabled" to "Disabled"

    Column {
        listOf(modifierRow, modifierRowFillWidth).forEach { modifier ->
            Row {
                listOf(true, false).forEach { isEnabled ->
                    Column(
                        modifier = modifierColumn,
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(
                            space = Token.SpacingSmall,
                        ),
                    ) {
                        listOf(true, false).forEach { isChecked ->
                            Switch(
                                modifier = modifier,
                                isChecked = isChecked,
                                isEnabled = isEnabled,
                                onCheckedChange = {},
                                textPrimary =
                                    if (showSecondary) {
                                        if (isChecked) pairChecked.first else pairChecked.second
                                    } else {
                                        (if (isChecked) pairChecked.first else pairChecked.second) +
                                        " / " +
                                        (if (isEnabled) pairEnabled.first else pairEnabled.second)
                                    },
                                textSecondary =
                                    if (showSecondary) {
                                        if (isEnabled) pairEnabled.first else pairEnabled.second
                                    } else {
                                        ""
                                    },
                            )
                        }
                    }
                }
            }
        }
    }
}
