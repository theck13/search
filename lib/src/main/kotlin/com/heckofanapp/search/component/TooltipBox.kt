@file:OptIn(
    ExperimentalMaterial3Api::class,
)

package com.heckofanapp.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import com.heckofanapp.search.PreviewDarkLight
import com.heckofanapp.search.Token
import com.heckofanapp.search.theme.Color
import com.heckofanapp.search.theme.Theme
import com.heckofanapp.search.theme.ThemeMode
import androidx.compose.material3.TooltipBox as MaterialTooltipBox

enum class TooltipBoxPosition {
    Above,
    Below,
}

/**
 * Private version of [Material 3 tooltip tokens](https://m3.material.io/components/tooltips/specs).
 * See [Tooltip.kt source code](https://cs.android.com/androidx/platform/frameworks/support/+/c602f9aff2990121fa96881651b54f1c4ecd8e11:compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/Tooltip.kt) for details.
 */
private object TooltipBoxToken {
    val MaxWidth = 200.dp // https://cs.android.com/androidx/platform/frameworks/support/+/c602f9aff2990121fa96881651b54f1c4ecd8e11:compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/Tooltip.kt;l=664
    val MinHeight = 24.dp // https://cs.android.com/androidx/platform/frameworks/support/+/c602f9aff2990121fa96881651b54f1c4ecd8e11:compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/Tooltip.kt;l=1635
    val MinWidth = 40.dp // https://cs.android.com/androidx/platform/frameworks/support/+/c602f9aff2990121fa96881651b54f1c4ecd8e11:compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/Tooltip.kt;l=1636
}

/**
 * [Text] wrapped by [Box] with dark theme.
 *
 * @param text required [String] to set as text of [TooltipBox]
 */
@Composable
private fun TooltipBox(
    text: String,
) {
    Theme(
        mode = ThemeMode.Dark,
    ) {
        Box(
            modifier = Modifier
                .sizeIn(
                    maxWidth = TooltipBoxToken.MaxWidth,
                    minHeight = TooltipBoxToken.MinHeight,
                    minWidth = TooltipBoxToken.MinWidth,
                )
                .clip(
                    shape = RoundedCornerShape(
                        size = Token.CornerRadiusSmall,
                    )
                )
                .background(
                    color = Color.backgroundNeutralHigh,
                )
                .padding(
                    horizontal = Token.SpacingXs,
                    vertical = Token.Spacing2xs,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                color = Color.textIconNeutral,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.BodyMedium.Default,
                text = text,
            )
        }
    }
}

/**
 * [TooltipBox] with optional [Modifier], [TooltipBoxPosition], and [Unit] content.
 *
 * @param modifier optional [Modifier] to apply to [MaterialTooltipBox]
 * @param enableUserInput optional [Boolean] to handle long-press and mouse-over through provided state
 * @param isFocusable optional [Boolean] to set as focusable and consume touch events or not
 * @param position optional [TooltipBoxPosition] to place [TooltipBox] relative to anchor
 * @param text optional [String] to set as text of [TooltipBox]
 * @param content optional [Unit] to set as content of [TooltipBox]
 */
@Composable
fun TooltipBox(
    modifier: Modifier = Modifier,
    enableUserInput: Boolean = true,
    isFocusable: Boolean = true,
    position: TooltipBoxPosition = TooltipBoxPosition.Above,
    text: String?,
    content: @Composable () -> Unit,
) {
    TooltipBox(
        modifier = modifier,
        enableUserInput = enableUserInput,
        isFocusable = isFocusable,
        position = position,
        state = rememberTooltipState(),
        text = text,
        content = content,
    )
}

/**
 * [TooltipBox] with optional [Modifier], [TooltipBoxPosition], [TooltipState], and [Unit] content.
 *
 * @param modifier optional [Modifier] to apply to [MaterialTooltipBox]
 * @param enableUserInput optional [Boolean] to handle long-press and mouse-over through provided state
 * @param isFocusable optional [Boolean] to set as focusable and consume touch events or not
 * @param position optional [TooltipBoxPosition] to place [TooltipBox] relative to anchor
 * @param state optional [TooltipState] to handle visibility of [TooltipBox]
 * @param text optional [String] to set as text of [TooltipBox]
 * @param content optional [Unit] to set as content of [TooltipBox]
 */
@Composable
fun TooltipBox(
    modifier: Modifier = Modifier,
    enableUserInput: Boolean = true,
    isFocusable: Boolean = true,
    position: TooltipBoxPosition = TooltipBoxPosition.Above,
    state: TooltipState = rememberTooltipState(),
    text: String?,
    content: @Composable () -> Unit,
) {
    TooltipBox(
        modifier = modifier,
        enableUserInput = enableUserInput,
        isFocusable = isFocusable,
        positionProvider = rememberTooltipPositionProvider(
            position = position,
        ),
        state = state,
        text = text,
        content = content,
    )
}

/**
 * [TooltipBox] wrapped by [MaterialTooltipBox].
 *
 * @param modifier optional [Modifier] to apply to [MaterialTooltipBox]
 * @param enableUserInput optional [Boolean] to handle long-press and mouse-over through provided state
 * @param isFocusable optional [Boolean] to set as focusable and consume touch events or not
 * @param positionProvider required [PopupPositionProvider] to place [TooltipBox] relative to anchor
 * @param state optional [TooltipState] to handle visibility of [TooltipBox]
 * @param text optional [String] to set as text of [TooltipBox]
 * @param content optional [Unit] to set as content of [TooltipBox]
 */
@Composable
fun TooltipBox(
    modifier: Modifier = Modifier,
    enableUserInput: Boolean = true,
    isFocusable: Boolean = true,
    positionProvider: PopupPositionProvider,
    state: TooltipState = rememberTooltipState(),
    text: String?,
    content: @Composable () -> Unit,
) {
    if (text.isNullOrBlank()) {
        content()
    } else {
        MaterialTooltipBox(
            modifier = modifier,
            enableUserInput = enableUserInput,
            focusable = isFocusable,
            positionProvider = positionProvider,
            tooltip = {
                TooltipBox(
                    text = text,
                )
            },
            state = state,
        ) {
            content()
        }
    }
}

@Composable
private fun rememberTooltipPositionProvider(
    position: TooltipBoxPosition,
    spacingBetweenTooltipAndAnchor: Dp = Token.Spacing2xs,
): PopupPositionProvider {
    val tooltipAnchorSpacing = with(
        receiver = LocalDensity.current
    ) {
        spacingBetweenTooltipAndAnchor.roundToPx()
    }

    return remember(position, tooltipAnchorSpacing) {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize,
            ): IntOffset {
                val x = anchorBounds.left + (anchorBounds.width - popupContentSize.width) / 2

                val y = when (position) {
                    TooltipBoxPosition.Above -> {
                        anchorBounds.top - popupContentSize.height - tooltipAnchorSpacing
                    }
                    TooltipBoxPosition.Below -> {
                        anchorBounds.bottom + tooltipAnchorSpacing
                    }
                }

                // Position is restricted to be inside the window bounds automatically.
                return IntOffset(x, y)
            }
        }
    }
}

@PreviewDarkLight
@Composable
fun PreviewTooltipBox() {
    Column(
        modifier = Modifier.padding(
            all = Token.SpacingMedium,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            alignment = Alignment.CenterVertically,
            space = Token.SpacingMedium,
        ),
    ) {
        TooltipBox(
            text = "",
        )

        TooltipBox(
            text = "Tooltip",
        )

        TooltipBox(
            text = "Tooltip with text that truncates",
        )
    }
}
