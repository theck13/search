@file:OptIn(
    ExperimentalMaterial3Api::class,
)

package com.heckofanapp.search.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.RippleDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color as ComposeColor

@Composable
internal fun RippleConfiguration(
    color: ComposeColor = Color.backgroundTranslucentPressed,
    rippleAlpha: RippleAlpha = RippleDefaults.RippleAlpha,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        content = content,
        values = arrayOf(
            LocalRippleConfiguration provides RippleConfiguration(
                color = color,
                rippleAlpha = rippleAlpha,
            ),
        ),
    )
}
