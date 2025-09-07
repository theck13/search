package com.heckofanapp.search.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.heckofanapp.search.PreviewDarkLight
import com.heckofanapp.search.Token

internal object LocalThemeMode {
    private val LocalThemeMode = staticCompositionLocalOf<ThemeMode?> { null }

    val current: ThemeMode
        @ReadOnlyComposable
        @Composable
        get() = LocalThemeMode.current ?: if (isSystemInDarkTheme()) ThemeMode.Dark else ThemeMode.Light

    infix fun provides(type: ThemeMode): ProvidedValue<ThemeMode?> {
        return LocalThemeMode.provides(type)
    }
}

internal object LocalThemeType {
    private val LocalThemeType = staticCompositionLocalOf<ThemeType?> { null }

    val current: ThemeType
        @ReadOnlyComposable
        @Composable
        get() = LocalThemeType.current ?: ThemeType.Default

    infix fun provides(type: ThemeType): ProvidedValue<ThemeType?> {
        return LocalThemeType.provides(type)
    }
}

enum class ThemeMode {
    Dark,
    Light,
}

enum class ThemeType {
    Default,
}

@Composable
fun Theme(
    mode: ThemeMode,
    type: ThemeType = ThemeType.Default,
    content: @Composable () -> Unit,
) {
    val colorScheme = when (type) {
        ThemeType.Default -> {
            when (mode) {
                ThemeMode.Dark -> defaultDarkColorScheme
                ThemeMode.Light -> defaultLightColorScheme
            }
        }
    }

    CompositionLocalProvider(
        content = {
            content()
        },
        values = arrayOf(
            LocalColorScheme provides colorScheme,
            LocalThemeMode provides mode,
            LocalThemeType provides type,
        ),
    )
}

object Theme {
    val color: ColorScheme
        @ReadOnlyComposable
        @Composable
        get() = LocalColorScheme.current
    val mode: ThemeMode
        @ReadOnlyComposable
        @Composable
        get() = LocalThemeMode.current
    val type: ThemeType
        @ReadOnlyComposable
        @Composable
        get() = LocalThemeType.current
}

@PreviewDarkLight
@Composable
fun PreviewTheme() {
    val spacing = Token.SpacingXs

    Column(
        modifier = Modifier.padding(
            vertical = spacing,
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = spacing
        )
    ) {
        ThemeType.entries.forEach { theme ->
            Row(
                modifier = Modifier.padding(
                    horizontal = spacing,
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    space = spacing,
                ),
            ) {
                Theme(
                    mode = ThemeMode.Dark,
                    type = theme,
                ) {
                    ColoredBox(
                        label = "$theme[Dark]",
                    )
                }

                Theme(
                    mode = ThemeMode.Light,
                    type = theme,
                ) {
                    ColoredBox(
                        label = "$theme[Light]",
                    )
                }

                ColoredBox(
                    label = "$theme[System]",
                )
            }
        }
    }
}
