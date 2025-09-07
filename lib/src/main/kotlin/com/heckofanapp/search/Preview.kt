package com.heckofanapp.search

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

object Device {
    object Phone {
        const val Landscape = "spec:width=891dp,height=411dp,dpi=420"
        const val Portrait = "spec:width=411dp,height=891dp,dpi=420"
    }

    object Tablet {
        const val Landscape = "spec:width=1280dp,height=800dp,dpi=420"
        const val Portrait = "spec:width=800dp,height=1280dp,dpi=420"
    }

    const val Undefined = "spec:width=1920dp,height=1920dp,dpi=420"
}

@Preview(
    backgroundColor = 0xf000,
    device = Device.Undefined,
    group = "Portrait",
    name = "Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    backgroundColor = 0xffff,
    device = Device.Undefined,
    group = "Portrait",
    name = "Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
annotation class PreviewDarkLight

@Preview(
    backgroundColor = 0xf000,
    device = Device.Phone.Landscape,
    group = "Phone",
    heightDp = 200,
    name = "Dark, Landscape, Height: 200dp",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    backgroundColor = 0xffff,
    device = Device.Phone.Landscape,
    group = "Phone",
    heightDp = 200,
    name = "Light, Landscape, Height: 200dp",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
annotation class PreviewDarkLightPhoneFloatingLandscape

@Preview(
    backgroundColor = 0xf000,
    device = Device.Phone.Portrait,
    group = "Phone",
    name = "Dark, Portrait, Width: 200dp",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 200,
)
@Preview(
    backgroundColor = 0xffff,
    device = Device.Phone.Portrait,
    group = "Phone",
    name = "Light, Portrait, Width: 200dp",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 200,
)
annotation class PreviewDarkLightPhoneFloatingPortrait

@Preview(
    backgroundColor = 0xf000,
    device = Device.Phone.Landscape,
    group = "Phone",
    name = "Dark, Landscape",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    backgroundColor = 0xffff,
    device = Device.Phone.Landscape,
    group = "Phone",
    name = "Light, Landscape",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
annotation class PreviewDarkLightPhoneLandscape

@Preview(
    backgroundColor = 0xf000,
    device = Device.Phone.Portrait,
    group = "Phone",
    name = "Dark, Portrait",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    backgroundColor = 0xffff,
    device = Device.Phone.Portrait,
    group = "Phone",
    name = "Light, Portrait",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
annotation class PreviewDarkLightPhonePortrait

@Preview(
    backgroundColor = 0xf000,
    device = Device.Tablet.Landscape,
    group = "Tablet",
    name = "Dark, Landscape",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    backgroundColor = 0xffff,
    device = Device.Tablet.Landscape,
    group = "Tablet",
    name = "Light, Landscape",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
annotation class PreviewDarkLightTabletLandscape

@Preview(
    backgroundColor = 0xf000,
    device = Device.Tablet.Portrait,
    group = "Tablet",
    name = "Dark, Portrait",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    backgroundColor = 0xffff,
    device = Device.Tablet.Portrait,
    group = "Tablet",
    name = "Light, Portrait",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
annotation class PreviewDarkLightTabletPortrait
