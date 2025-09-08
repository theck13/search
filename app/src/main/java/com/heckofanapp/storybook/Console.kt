package com.heckofanapp.storybook

import com.heckofanapp.search.component.PillColor

enum class Console(
    val color: PillColor,
    val text: String,
) {
    Atari(
        color = PillColor.Neutral,
        text = "Atari",
    ),
    GameBoy(
        color = PillColor.Neutral,
        text = "Game Boy",
    ),
    Nintendo(
        color = PillColor.Critical,
        text = "Nintendo",
    ),
    Nintendo64(
        color = PillColor.Neutral,
        text = "Nintendo 64",
    ),
    PlayStation(
        color = PillColor.Attentional,
        text = "PlayStation",
    ),
    Xbox360(
        color = PillColor.Successful,
        text = "Xbox 360",
    ),
    Windows(
        color = PillColor.Informational,
        text = "Windows",
    ),
}
