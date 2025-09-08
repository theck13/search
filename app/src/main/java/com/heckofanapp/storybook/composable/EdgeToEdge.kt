package com.heckofanapp.storybook.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import com.heckofanapp.search.Token
import com.heckofanapp.storybook.extension.plus

@Composable
fun navigationBarsOnlyHorizontal(): WindowInsets {
    return WindowInsets
        .navigationBars
        .only(
            sides = WindowInsetsSides.Horizontal,
        )
}

@Composable
fun navigationBarsWithPadding(
    padding: PaddingValues = PaddingValues(
        all = Token.SpacingMedium,
    ),
): PaddingValues {
    return WindowInsets.navigationBars.asPaddingValues() + padding
}

@Composable
fun navigationBarsWithPaddingOnlyVertical(
    padding: PaddingValues = PaddingValues(
        vertical = Token.SpacingMedium,
    ),
): PaddingValues {
    return WindowInsets
        .navigationBars
        .only(
            sides = WindowInsetsSides.Vertical,
        )
        .asPaddingValues() + padding
}

@Composable
fun safeDrawingExcludeSystemBars(): WindowInsets {
    return WindowInsets
        .safeDrawing
        .exclude(
            insets = WindowInsets.systemBars,
        )
}

@Composable
fun safeDrawingOnlyHorizontal(): WindowInsets {
    return WindowInsets
        .safeDrawing
        .only(
            sides = WindowInsetsSides.Horizontal,
        )
}
