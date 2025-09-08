package com.heckofanapp.storybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.heckofanapp.search.PreviewDarkLightPhoneLandscape
import com.heckofanapp.search.PreviewDarkLightPhonePortrait
import com.heckofanapp.storybook.theme.StorybookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StorybookTheme {
            }
        }
    }
}

@PreviewDarkLightPhonePortrait
@PreviewDarkLightPhoneLandscape
@Composable
fun PreviewMain() {
    StorybookTheme {
    }
}
