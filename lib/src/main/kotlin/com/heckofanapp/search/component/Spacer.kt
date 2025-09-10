package com.heckofanapp.search.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.heckofanapp.search.PreviewDarkLight
import com.heckofanapp.search.Token
import com.heckofanapp.search.theme.Color

/**
 * [Spacer] component using [Token.SpacingHairline] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun SpacerHairline(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.SpacingHairline,
        ),
    )
}

/**
 * [Spacer] component using [Token.SpacingMedium] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun SpacerMedium(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.SpacingMedium,
        ),
    )
}

/**
 * [Spacer] component using [Token.SpacingLarge] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun SpacerLarge(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.SpacingLarge,
        ),
    )
}

/**
 * [Spacer] component using [Token.SpacingSmall] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun SpacerSmall(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.SpacingSmall,
        ),
    )
}

/**
 * [Spacer] component using [Token.SpacingXl] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun SpacerXl(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.SpacingXl,
        ),
    )
}

/**
 * [Spacer] component using [Token.SpacingXs] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun SpacerXs(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.SpacingXs,
        ),
    )
}

/**
 * [Spacer] component using [Token.Spacing2xl] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun Spacer2xl(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.Spacing2xl,
        ),
    )
}

/**
 * [Spacer] component using [Token.Spacing2xs] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun Spacer2xs(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.Spacing2xs,
        ),
    )
}

/**
 * [Spacer] component using [Token.Spacing3xl] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun Spacer3xl(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.Spacing3xl,
        ),
    )
}

/**
 * [Spacer] component using [Token.Spacing4xl] as size.
 *
 * @param modifier optional [Modifier] to apply to [Spacer]
 */
@Composable
fun Spacer4xl(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(
            size = Token.Spacing4xl,
        ),
    )
}

@PreviewDarkLight
@Composable
fun PreviewSpacer() {
    val modifier = Modifier.border(
        color = Color.backgroundActivated,
        width = Token.SpacingHairline,
    )

    Spacer4xl(modifier)
    Spacer3xl(modifier)
    Spacer2xl(modifier)
    SpacerLarge(modifier)
    SpacerMedium(modifier)
    SpacerSmall(modifier)
    SpacerXs(modifier)
    Spacer2xs(modifier)
    SpacerHairline(modifier)
}
