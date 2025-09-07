package com.heckofanapp.search.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.heckofanapp.search.Token
import java.util.EnumMap

enum class ColorSchemeToken {
    Accent,
    AccentWeak,
    BackgroundActivated,
    BackgroundAttentional,
    BackgroundAttentionalWeak,
    BackgroundCareful,
    BackgroundCarefulWeak,
    BackgroundCritical,
    BackgroundCriticalWeak,
    BackgroundInformational,
    BackgroundInformationalWeak,
    BackgroundInverse,
    BackgroundNeutral,
    BackgroundNeutralDisabled,
    BackgroundNeutralHigh,
    BackgroundNeutralLow,
    BackgroundOverlay,
    BackgroundSuccessful,
    BackgroundSuccessfulWeak,
    BackgroundTranslucent,
    BackgroundTranslucentPressed,
    BackgroundTransparent,
    BorderAttentional,
    BorderCareful,
    BorderCritical,
    BorderFocus,
    BorderFocusInverse,
    BorderInformational,
    BorderInverse,
    BorderNeutral,
    BorderNeutralStrong,
    BorderNeutralWeak,
    BorderSuccessful,
    TextIconAttentional,
    TextIconCareful,
    TextIconCritical,
    TextIconDisabled,
    TextIconInformational,
    TextIconInverse,
    TextIconNeutral,
    TextIconNeutralWeak,
    TextIconSuccessful,
}

@Immutable
class ColorScheme internal constructor(
    private val colors: EnumMap<ColorSchemeToken, Color>,
) {
    init {
        require(colors.size == ColorSchemeToken.entries.size) {
            "Color must be provided for each ColorSchemeKeyToken"
        }
    }

    fun fromToken(token: ColorSchemeToken): Color = colors[token]!!

    val accent get() = fromToken(ColorSchemeToken.Accent)
    val accentWeak get() = fromToken(ColorSchemeToken.AccentWeak)
    val backgroundActivated get() = fromToken(ColorSchemeToken.BackgroundActivated)
    val backgroundAttentional get() = fromToken(ColorSchemeToken.BackgroundAttentional)
    val backgroundAttentionalWeak get() = fromToken(ColorSchemeToken.BackgroundAttentionalWeak)
    val backgroundCareful get() = fromToken(ColorSchemeToken.BackgroundCareful)
    val backgroundCarefulWeak get() = fromToken(ColorSchemeToken.BackgroundCarefulWeak)
    val backgroundCritical get() = fromToken(ColorSchemeToken.BackgroundCritical)
    val backgroundCriticalWeak get() = fromToken(ColorSchemeToken.BackgroundCriticalWeak)
    val backgroundNeutralDisabled get() = fromToken(ColorSchemeToken.BackgroundNeutralDisabled)
    val backgroundInformational get() = fromToken(ColorSchemeToken.BackgroundInformational)
    val backgroundInformationalWeak get() = fromToken(ColorSchemeToken.BackgroundInformationalWeak)
    val backgroundInverse get() = fromToken(ColorSchemeToken.BackgroundInverse)
    val backgroundNeutral get() = fromToken(ColorSchemeToken.BackgroundNeutral)
    val backgroundNeutralHigh get() = fromToken(ColorSchemeToken.BackgroundNeutralHigh)
    val backgroundNeutralLow get() = fromToken(ColorSchemeToken.BackgroundNeutralLow)
    val backgroundOverlay get() = fromToken(ColorSchemeToken.BackgroundOverlay)
    val backgroundSuccessful get() = fromToken(ColorSchemeToken.BackgroundSuccessful)
    val backgroundSuccessfulWeak get() = fromToken(ColorSchemeToken.BackgroundSuccessfulWeak)
    val backgroundTranslucent get() = fromToken(ColorSchemeToken.BackgroundTranslucent)
    val backgroundTranslucentPressed get() = fromToken(ColorSchemeToken.BackgroundTranslucentPressed)
    val backgroundTransparent get() = fromToken(ColorSchemeToken.BackgroundTransparent)
    val borderAttentional get() = fromToken(ColorSchemeToken.BorderAttentional)
    val borderCareful get() = fromToken(ColorSchemeToken.BorderCareful)
    val borderCritical get() = fromToken(ColorSchemeToken.BorderCritical)
    val borderFocus get() = fromToken(ColorSchemeToken.BorderFocus)
    val borderFocusInverse get() = fromToken(ColorSchemeToken.BorderFocusInverse)
    val borderInformational get() = fromToken(ColorSchemeToken.BorderInformational)
    val borderInverse get() = fromToken(ColorSchemeToken.BorderInverse)
    val borderNeutral get() = fromToken(ColorSchemeToken.BorderNeutral)
    val borderNeutralStrong get() = fromToken(ColorSchemeToken.BorderNeutralStrong)
    val borderNeutralWeak get() = fromToken(ColorSchemeToken.BorderNeutralWeak)
    val borderSuccessful get() = fromToken(ColorSchemeToken.BorderSuccessful)
    val textIconAttentional get() = fromToken(ColorSchemeToken.TextIconAttentional)
    val textIconCareful get() = fromToken(ColorSchemeToken.TextIconCareful)
    val textIconCritical get() = fromToken(ColorSchemeToken.TextIconCritical)
    val textIconDisabled get() = fromToken(ColorSchemeToken.TextIconDisabled)
    val textIconInformational get() = fromToken(ColorSchemeToken.TextIconInformational)
    val textIconInverse get() = fromToken(ColorSchemeToken.TextIconInverse)
    val textIconNeutral get() = fromToken(ColorSchemeToken.TextIconNeutral)
    val textIconNeutralWeak get() = fromToken(ColorSchemeToken.TextIconNeutralWeak)
    val textIconSuccessful get() = fromToken(ColorSchemeToken.TextIconSuccessful)
}

val defaultDarkColorScheme = ColorScheme(
    EnumMap<ColorSchemeToken, Color>(ColorSchemeToken::class.java).apply {
        put(ColorSchemeToken.Accent, Token.AccentDark)
        put(ColorSchemeToken.AccentWeak, Token.AccentWeakDark)
        put(ColorSchemeToken.BackgroundActivated, Token.BackgroundActivatedDark)
        put(ColorSchemeToken.BackgroundAttentional, Token.BackgroundAttentionalDark)
        put(ColorSchemeToken.BackgroundAttentionalWeak, Token.BackgroundAttentionalWeakDark)
        put(ColorSchemeToken.BackgroundCareful, Token.BackgroundCarefulDark)
        put(ColorSchemeToken.BackgroundCarefulWeak, Token.BackgroundCarefulWeakDark)
        put(ColorSchemeToken.BackgroundCritical, Token.BackgroundCriticalDark)
        put(ColorSchemeToken.BackgroundCriticalWeak, Token.BackgroundCriticalWeakDark)
        put(ColorSchemeToken.BackgroundInformational, Token.BackgroundInformationalDark)
        put(ColorSchemeToken.BackgroundInformationalWeak, Token.BackgroundInformationalWeakDark)
        put(ColorSchemeToken.BackgroundInverse, Token.BackgroundInverseDark)
        put(ColorSchemeToken.BackgroundNeutral, Token.BackgroundNeutralDark)
        put(ColorSchemeToken.BackgroundNeutralDisabled, Token.BackgroundNeutralDisabledDark)
        put(ColorSchemeToken.BackgroundNeutralHigh, Token.BackgroundNeutralHighDark)
        put(ColorSchemeToken.BackgroundNeutralLow, Token.BackgroundNeutralLowDark)
        put(ColorSchemeToken.BackgroundOverlay, Token.BackgroundOverlayDark)
        put(ColorSchemeToken.BackgroundSuccessful, Token.BackgroundSuccessfulDark)
        put(ColorSchemeToken.BackgroundSuccessfulWeak, Token.BackgroundSuccessfulWeakDark)
        put(ColorSchemeToken.BackgroundTranslucent, Token.BackgroundTranslucentDark)
        put(ColorSchemeToken.BackgroundTranslucentPressed, Token.BackgroundTranslucentPressedDark)
        put(ColorSchemeToken.BackgroundTransparent, Token.BackgroundTransparentDark)
        put(ColorSchemeToken.BorderAttentional, Token.BorderAttentionalDark)
        put(ColorSchemeToken.BorderCareful, Token.BorderCarefulDark)
        put(ColorSchemeToken.BorderCritical, Token.BorderCriticalDark)
        put(ColorSchemeToken.BorderFocus, Token.BorderFocusDark)
        put(ColorSchemeToken.BorderFocusInverse, Token.BorderFocusInverseDark)
        put(ColorSchemeToken.BorderInformational, Token.BorderInformationalDark)
        put(ColorSchemeToken.BorderInverse, Token.BorderInverseDark)
        put(ColorSchemeToken.BorderNeutral, Token.BorderNeutralDark)
        put(ColorSchemeToken.BorderNeutralStrong, Token.BorderNeutralStrongDark)
        put(ColorSchemeToken.BorderNeutralWeak, Token.BorderNeutralWeakDark)
        put(ColorSchemeToken.BorderSuccessful, Token.BorderSuccessfulDark)
        put(ColorSchemeToken.TextIconAttentional, Token.TextIconAttentionalDark)
        put(ColorSchemeToken.TextIconCareful, Token.TextIconCarefulDark)
        put(ColorSchemeToken.TextIconCritical, Token.TextIconCriticalDark)
        put(ColorSchemeToken.TextIconDisabled, Token.TextIconDisabledDark)
        put(ColorSchemeToken.TextIconInformational, Token.TextIconInformationalDark)
        put(ColorSchemeToken.TextIconInverse, Token.TextIconInverseDark)
        put(ColorSchemeToken.TextIconNeutral, Token.TextIconNeutralDark)
        put(ColorSchemeToken.TextIconNeutralWeak, Token.TextIconNeutralWeakDark)
        put(ColorSchemeToken.TextIconSuccessful, Token.TextIconSuccessfulDark)
    }
)
val defaultLightColorScheme = ColorScheme(
    EnumMap<ColorSchemeToken, Color>(ColorSchemeToken::class.java).apply {
        put(ColorSchemeToken.Accent, Token.AccentLight)
        put(ColorSchemeToken.AccentWeak, Token.AccentWeakLight)
        put(ColorSchemeToken.BackgroundActivated, Token.BackgroundActivatedLight)
        put(ColorSchemeToken.BackgroundAttentional, Token.BackgroundAttentionalLight)
        put(ColorSchemeToken.BackgroundAttentionalWeak, Token.BackgroundAttentionalWeakLight)
        put(ColorSchemeToken.BackgroundCareful, Token.BackgroundCarefulLight)
        put(ColorSchemeToken.BackgroundCarefulWeak, Token.BackgroundCarefulWeakLight)
        put(ColorSchemeToken.BackgroundCritical, Token.BackgroundCriticalLight)
        put(ColorSchemeToken.BackgroundCriticalWeak, Token.BackgroundCriticalWeakLight)
        put(ColorSchemeToken.BackgroundInformational, Token.BackgroundInformationalLight)
        put(ColorSchemeToken.BackgroundInformationalWeak, Token.BackgroundInformationalWeakLight)
        put(ColorSchemeToken.BackgroundInverse, Token.BackgroundInverseLight)
        put(ColorSchemeToken.BackgroundNeutral, Token.BackgroundNeutralLight)
        put(ColorSchemeToken.BackgroundNeutralDisabled, Token.BackgroundNeutralDisabledLight)
        put(ColorSchemeToken.BackgroundNeutralHigh, Token.BackgroundNeutralHighLight)
        put(ColorSchemeToken.BackgroundNeutralLow, Token.BackgroundNeutralLowLight)
        put(ColorSchemeToken.BackgroundOverlay, Token.BackgroundOverlayLight)
        put(ColorSchemeToken.BackgroundSuccessful, Token.BackgroundSuccessfulLight)
        put(ColorSchemeToken.BackgroundSuccessfulWeak, Token.BackgroundSuccessfulWeakLight)
        put(ColorSchemeToken.BackgroundTranslucent, Token.BackgroundTranslucentLight)
        put(ColorSchemeToken.BackgroundTranslucentPressed, Token.BackgroundTranslucentPressedLight)
        put(ColorSchemeToken.BackgroundTransparent, Token.BackgroundTransparentLight)
        put(ColorSchemeToken.BorderAttentional, Token.BorderAttentionalLight)
        put(ColorSchemeToken.BorderCareful, Token.BorderCarefulLight)
        put(ColorSchemeToken.BorderCritical, Token.BorderCriticalLight)
        put(ColorSchemeToken.BorderFocus, Token.BorderFocusLight)
        put(ColorSchemeToken.BorderFocusInverse, Token.BorderFocusInverseLight)
        put(ColorSchemeToken.BorderInformational, Token.BorderInformationalLight)
        put(ColorSchemeToken.BorderInverse, Token.BorderInverseLight)
        put(ColorSchemeToken.BorderNeutral, Token.BorderNeutralLight)
        put(ColorSchemeToken.BorderNeutralStrong, Token.BorderNeutralStrongLight)
        put(ColorSchemeToken.BorderNeutralWeak, Token.BorderNeutralWeakLight)
        put(ColorSchemeToken.BorderSuccessful, Token.BorderSuccessfulLight)
        put(ColorSchemeToken.TextIconAttentional, Token.TextIconAttentionalLight)
        put(ColorSchemeToken.TextIconCareful, Token.TextIconCarefulLight)
        put(ColorSchemeToken.TextIconCritical, Token.TextIconCriticalLight)
        put(ColorSchemeToken.TextIconDisabled, Token.TextIconDisabledLight)
        put(ColorSchemeToken.TextIconInformational, Token.TextIconInformationalLight)
        put(ColorSchemeToken.TextIconInverse, Token.TextIconInverseLight)
        put(ColorSchemeToken.TextIconNeutral, Token.TextIconNeutralLight)
        put(ColorSchemeToken.TextIconNeutralWeak, Token.TextIconNeutralWeakLight)
        put(ColorSchemeToken.TextIconSuccessful, Token.TextIconSuccessfulLight)
    }
)

val Color: ColorScheme
    @ReadOnlyComposable
    @Composable
    get() = Theme.color

internal object LocalColorScheme {
    private val LocalColorScheme = staticCompositionLocalOf<ColorScheme?> { null }

    val current: ColorScheme
        @ReadOnlyComposable
        @Composable
        get() = LocalColorScheme.current ?: if (isSystemInDarkTheme()) defaultDarkColorScheme else defaultLightColorScheme

    infix fun provides(
        scheme: ColorScheme,
    ): ProvidedValue<ColorScheme?> {
        return LocalColorScheme.provides(scheme)
    }
}
