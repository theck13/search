package com.heckofanapp.storybook.theme

import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat.getInsetsController
import com.heckofanapp.search.theme.Color
import com.heckofanapp.search.theme.Theme
import com.heckofanapp.search.theme.ThemeMode
import com.heckofanapp.search.theme.ThemeType

@Composable
fun StorybookTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    type: ThemeType = ThemeType.Default,
    content: @Composable () -> Unit,
) {
    val alpha80 = 0.80f
    val colorsDark = darkColorScheme(
        background = Color.backgroundNeutral,
        primary = Color.accent,
        secondary = Color.backgroundTranslucentPressed,
        surface = Color.backgroundNeutralHigh,
        surfaceVariant = Color.backgroundNeutralHigh,
    )
    val colorsLight = lightColorScheme(
        background = Color.backgroundNeutral,
        primary = Color.accent,
        secondary = Color.backgroundTranslucentPressed,
        surface = Color.backgroundNeutralHigh,
        surfaceVariant = Color.backgroundNeutralHigh,
    )
    val colorTransparent = Color.backgroundTransparent
    val view = LocalView.current

    if (view.isInEditMode.not()) {
        DisposableEffect(
            isDarkTheme,
        ) {
            (view.context as ComponentActivity).window?.let { window ->
                window.navigationBarColor =
                    if (isDarkTheme) {
                        colorsDark.surface.copy(
                            alpha = alpha80,
                        ).toArgb()
                    } else {
                        colorsLight.surface.copy(
                            alpha = alpha80,
                        ).toArgb()
                    }

                window.statusBarColor = colorTransparent.toArgb()

                getInsetsController(window, view).let { view ->
                    view.isAppearanceLightNavigationBars = isDarkTheme.not()
                    view.isAppearanceLightStatusBars = isDarkTheme.not()
                }
            }

            onDispose {}
        }
    }

    MaterialTheme(
        colorScheme = when {
            isDarkTheme -> colorsDark
            else -> colorsLight
        },
        content = {
            Theme(
                mode = if (isDarkTheme) ThemeMode.Dark else ThemeMode.Light,
                type = type,
            ) {
                content()
            }
        },
    )
}
