package com.heckofanapp.search.component

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.heckofanapp.search.R
import com.heckofanapp.search.Token

private const val FontFeatureSettings = "'calt' 0, 'ss01', 'tnum'"

private val FontFamilyDefault = FontFamily(Font(R.font.roboto))
private val FontFamilyMono = FontFamily(Font(R.font.roboto_mono))

object Typography {
    object BodyLarge {
        val Default = TextStyle(
            fontFamily = FontFamilyDefault,
            fontFeatureSettings = FontFeatureSettings,
            fontSize = Token.FontSizeBodyLarge,
            fontWeight = FontWeight(Token.FontWeightBodyDefault),
            letterSpacing = Token.LetterSpacingBodyLarge,
            lineHeight = Token.LineHeightBodyLarge,
        )
    }

    object BodyMedium {
        val Default = TextStyle(
            fontFamily = FontFamilyDefault,
            fontFeatureSettings = FontFeatureSettings,
            fontSize = Token.FontSizeBodyMedium,
            fontWeight = FontWeight(Token.FontWeightBodyDefault),
            letterSpacing = Token.LetterSpacingBodyMedium,
            lineHeight = Token.LineHeightBodyMedium,
        )
    }

    object BodySmall {
        val Default = TextStyle(
            fontFamily = FontFamilyDefault,
            fontFeatureSettings = FontFeatureSettings,
            fontSize = Token.FontSizeBodySmall,
            fontWeight = FontWeight(Token.FontWeightBodyDefault),
            letterSpacing = Token.LetterSpacingBodySmall,
            lineHeight = Token.LineHeightBodySmall,
        )

        val Monospace = TextStyle(
            fontFamily = FontFamilyMono,
            fontFeatureSettings = FontFeatureSettings,
            fontSize = Token.FontSizeBodySmall,
            fontWeight = FontWeight(Token.FontWeightBodyDefault),
            letterSpacing = Token.LetterSpacingBodySmall,
            lineHeight = Token.LineHeightBodySmall,
        )
    }

    object HeadingLarge {
        val Default = TextStyle(
            fontFamily = FontFamilyDefault,
            fontFeatureSettings = FontFeatureSettings,
            fontSize = Token.FontSizeHeadingLarge,
            fontWeight = FontWeight(Token.FontWeightHeadingDefault),
            letterSpacing = Token.LetterSpacingHeadingLarge,
            lineHeight = Token.LineHeightHeadingLarge,
        )
    }

    object HeadingMedium {
        val Default = TextStyle(
            fontFamily = FontFamilyDefault,
            fontFeatureSettings = FontFeatureSettings,
            fontSize = Token.FontSizeHeadingMedium,
            fontWeight = FontWeight(Token.FontWeightHeadingDefault),
            letterSpacing = Token.LetterSpacingHeadingMedium,
            lineHeight = Token.LineHeightHeadingMedium,
        )
    }
}
