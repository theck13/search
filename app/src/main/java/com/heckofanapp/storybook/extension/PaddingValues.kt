package com.heckofanapp.storybook.extension

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection

/**
 * Add two [PaddingValues].
 *
 * @param that required [PaddingValues] to add to this [PaddingValues]
 *
 * @return [PaddingValues] sum of that [PaddingValues] and this [PaddingValues]
 */
@Composable
operator fun PaddingValues.plus(
    that: PaddingValues,
): PaddingValues {
    val direction = LocalLayoutDirection.current
    return PaddingValues(
        bottom =
            this.calculateBottomPadding() +
            that.calculateBottomPadding(),
        end =
            this.calculateEndPadding(
                layoutDirection = direction,
            ) +
            that.calculateEndPadding(
                layoutDirection = direction,
            ),
        start =
            this.calculateStartPadding(
                layoutDirection = direction,
            ) +
            that.calculateStartPadding(
                layoutDirection = direction,
            ),
        top =
            this.calculateTopPadding() +
            that.calculateTopPadding(),
    )
}
