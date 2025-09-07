package com.heckofanapp.search.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.heckofanapp.search.PreviewDarkLight
import com.heckofanapp.search.Token
import com.heckofanapp.search.component.Typography

@Composable
internal fun ColoredBox(
    label: String,
    token: ColorSchemeToken = ColorSchemeToken.BackgroundCriticalWeak,
) {
    val colorBackground = Color.fromToken(
        token = token,
    )
    val colorHexadecimal = String.format(
        format = "#%08X",
        colorBackground.toArgb(),
    ).lowercase()
    val size = 128.00.dp

    Box(
        modifier = Modifier
            .background(
                color = colorBackground,
            )
            .defaultMinSize(
                minHeight = size,
                minWidth = size,
            )
    ) {
        Column(
            modifier = Modifier
                .align(
                    alignment = Alignment.Center,
                )
                .padding(
                    all = Token.SpacingXs,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CompositionLocalProvider(
                LocalTextStyle provides Typography.BodySmall.Default.copy(
                    color = Color.textIconNeutral,
                )
            ) {
                Text(
                    text = label,
                )
            }

            CompositionLocalProvider(
                LocalTextStyle provides Typography.BodySmall.Monospace.copy(
                    color = Color.textIconNeutral,
                )
            ) {
                Text(
                    text = colorHexadecimal,
                )
            }
        }
    }
}

@PreviewDarkLight
@Composable
private fun PreviewColoredBox() {
    val spacing = Token.SpacingXs

    Column(
        modifier = Modifier.padding(
            vertical = spacing,
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = spacing
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = spacing,
            ),
            horizontalArrangement = Arrangement.spacedBy(
                space = spacing,
            ),
        ) {
            ColoredBox(
                label = "Background\nNeutral",
                token = ColorSchemeToken.BackgroundNeutral,
            )

            ColoredBox(
                label = "Background\nInformational",
                token = ColorSchemeToken.BackgroundInformational,
            )

            ColoredBox(
                label = "Background\nAttentional",
                token = ColorSchemeToken.BackgroundAttentional,
            )

            ColoredBox(
                label = "Background\nSuccessful",
                token = ColorSchemeToken.BackgroundSuccessful,
            )

            ColoredBox(
                label = "Background\nCareful",
                token = ColorSchemeToken.BackgroundCareful,
            )

            ColoredBox(
                label = "Background\nCritical",
                token = ColorSchemeToken.BackgroundCritical,
            )
        }
    }
}
