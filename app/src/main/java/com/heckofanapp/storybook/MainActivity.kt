package com.heckofanapp.storybook

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.heckofanapp.search.PreviewDarkLightPhoneLandscape
import com.heckofanapp.search.PreviewDarkLightPhonePortrait
import com.heckofanapp.storybook.extension.integerResource
import com.heckofanapp.storybook.theme.StorybookTheme

class MainActivity : ComponentActivity() {
    val viewModel: ViewModel by viewModels {
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StorybookTheme {
                NavigationHost(
                    navHostController = rememberNavController(),
                    startDestination = NavigationItem.Search.route.name,
                )
            }
        }

        findViewById<View>(android.R.id.content).let { content ->
            content.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        return if (viewModel.atEndSplash) {
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        } else {
                            false
                        }
                    }
                }
            )
        }

        Handler(Looper.getMainLooper()).postDelayed(
            {
                viewModel.atEndSplash = true
            },
            resources.integerResource(id = R.integer.animation_splash_duration_total).toLong()
        )
    }
}

@PreviewDarkLightPhonePortrait
@PreviewDarkLightPhoneLandscape
@Composable
fun PreviewMain() {
    StorybookTheme {
        LayoutSearch(
            title = NavigationRoute.Search.name,
        )
    }
}
