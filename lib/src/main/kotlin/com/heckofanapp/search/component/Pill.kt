package com.heckofanapp.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.heckofanapp.search.theme.Color
import com.heckofanapp.search.PreviewDarkLight
import com.heckofanapp.search.Token
import com.heckofanapp.search.theme.LocalThemeMode
import com.heckofanapp.search.theme.Theme
import com.heckofanapp.search.theme.ThemeMode
import androidx.compose.ui.graphics.Color as ComposeColor

enum class PillColor {
    Attentional,
    Careful,
    Critical,
    Informational,
    Neutral,
    Successful,
}

enum class PillEmphasis {
    Strong,
    Weak,
}

private object PillTokens {
    val ColorNeutralBackgroundDark = ComposeColor(0x47ffffff)
    val ColorNeutralBackgroundLight = ComposeColor(0x9e000000)
    val ColorWeakBackgroundDark = ComposeColor(0x17ffffff)
    val ColorWeakBackgroundLight = ComposeColor(0x12000000)
}

/**
 * [Text] with pill-shaped background using colors set by [PillColor] and [PillEmphasis].
 *
 * @param color optional [PillColor] to set color of background and text
 * @param emphasis optional [PillEmphasis] to set color of background and text
 * @param text required [String] to set as text of [Pill]
 */
@Composable
fun Pill(
    color: PillColor = PillColor.Neutral,
    emphasis: PillEmphasis = PillEmphasis.Weak,
    text: String,
) {
    val themeType = LocalThemeMode.current

    val colorBackground = when (emphasis) {
        PillEmphasis.Strong -> when (color) {
            PillColor.Attentional -> Color.backgroundAttentional
            PillColor.Careful -> Color.backgroundCareful
            PillColor.Critical -> Color.backgroundCritical
            PillColor.Informational -> Color.backgroundInformational
            PillColor.Neutral -> when (themeType) {
                ThemeMode.Dark -> PillTokens.ColorNeutralBackgroundDark
                ThemeMode.Light -> PillTokens.ColorNeutralBackgroundLight
            }
            PillColor.Successful -> Color.backgroundSuccessful
        }
        PillEmphasis.Weak -> when (color) {
            PillColor.Attentional -> Color.backgroundAttentionalWeak
            PillColor.Careful -> Color.backgroundCarefulWeak
            PillColor.Critical -> Color.backgroundCriticalWeak
            PillColor.Informational -> Color.backgroundInformationalWeak
            PillColor.Neutral -> when (themeType) {
                ThemeMode.Dark -> PillTokens.ColorWeakBackgroundDark
                ThemeMode.Light -> PillTokens.ColorWeakBackgroundLight
            }
            PillColor.Successful -> Color.backgroundSuccessfulWeak
        }
    }
    val colorText = when (emphasis) {
        PillEmphasis.Strong -> Color.textIconInverse
        PillEmphasis.Weak -> when (color) {
            PillColor.Attentional -> Color.textIconAttentional
            PillColor.Careful -> Color.textIconCareful
            PillColor.Critical -> Color.textIconCritical
            PillColor.Informational -> Color.textIconInformational
            PillColor.Neutral -> Color.textIconNeutral
            PillColor.Successful -> Color.textIconSuccessful
        }
    }
    val shape = RoundedCornerShape(
        size = Token.CornerRadiusLarge,
    )

    Text(
        modifier = Modifier
            .clip(
                shape = shape,
            )
            .background(
                color = colorBackground,
                shape = shape,
            )
            .defaultMinSize(
                minHeight = 22.dp,
            )
            .padding(
                horizontal = Token.SpacingXs,
            )
            .wrapContentHeight(
                align = Alignment.CenterVertically,
            ),
        color = colorText,
        style = Typography.BodySmall.Default,
        text = text,
    )
}

@PreviewDarkLight
@Composable
fun PreviewPill() {
    val space = Token.SpacingMedium
    val spacedBy = Arrangement.spacedBy(
        space = space,
    )

    Row(
        modifier = Modifier.padding(
            all = space,
        ),
        horizontalArrangement = spacedBy,
    ) {
        PillColor.entries.forEach { color ->
            Column(
                verticalArrangement = spacedBy,
            ) {
                PillEmphasis.entries.forEach { emphasis ->
                    Pill(
                        color = color,
                        emphasis = emphasis,
                        text = color.name,
                    )
                }
            }
        }
    }
}

@PreviewDarkLight
@Composable
fun PreviewPillTheme() {
    Column(
        modifier = Modifier.padding(
            all = Token.SpacingXs,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = Token.SpacingSmall,
        ),
    ) {
        Text(
            text = "Dark",
            color = Color.textIconNeutral
        )

        Theme(
            mode = ThemeMode.Dark,
        ) {
            PreviewPill()
        }

        Text(
            text = "Light",
            color = Color.textIconNeutral
        )

        Theme(
            mode = ThemeMode.Light,
        ) {
            PreviewPill()
        }

        Text(
            text = "System",
            color = Color.textIconNeutral
        )

        PreviewPill()
    }
}
