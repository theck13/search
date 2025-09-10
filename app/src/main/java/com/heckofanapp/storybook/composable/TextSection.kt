package com.heckofanapp.storybook.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.heckofanapp.search.PreviewDarkLight
import com.heckofanapp.search.component.SpacerXs
import com.heckofanapp.search.component.Typography
import com.heckofanapp.search.theme.Color
import com.heckofanapp.storybook.theme.StorybookTheme
import androidx.compose.ui.graphics.Color as ComposeColor

@Composable
fun TextSection(
    modifier: Modifier = Modifier,
    colorText: ComposeColor = Color.textIconNeutral,
    textSubtitle: String = "",
    textTitle: String = "",
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        if (textTitle.isNotEmpty()) {
            Text(
                modifier = modifier,
                color = colorText,
                style = Typography.HeadingLarge.Default,
                text = textTitle,
            )
        }

        if (textTitle.isNotEmpty() && textSubtitle.isNotEmpty()) {
            SpacerXs()
        }

        if (textSubtitle.isNotEmpty()) {
            Text(
                modifier = modifier,
                color = colorText,
                style = Typography.BodyMedium.Default,
                text = textSubtitle,
            )
        }
    }
}

@PreviewDarkLight
@Composable
fun PreviewTextSection() {
    StorybookTheme {
        TextSection(
            textSubtitle = "Use the example below to interact with the component and see the changes live.",
            textTitle = "Examples",
        )
    }
}
