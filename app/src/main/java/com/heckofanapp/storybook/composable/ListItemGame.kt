package com.heckofanapp.storybook.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.heckofanapp.search.theme.Color
import com.heckofanapp.search.PreviewDarkLightPhoneLandscape
import com.heckofanapp.search.PreviewDarkLightPhonePortrait
import com.heckofanapp.search.Token
import com.heckofanapp.search.component.Pill
import com.heckofanapp.search.component.PillEmphasis
import com.heckofanapp.search.component.SpacerMedium
import com.heckofanapp.search.component.Typography
import com.heckofanapp.storybook.Console
import com.heckofanapp.storybook.Constant
import com.heckofanapp.storybook.Game
import com.heckofanapp.storybook.R
import com.heckofanapp.storybook.games
import com.heckofanapp.storybook.theme.StorybookTheme

@Composable
fun ListItemGame(
    game: Game,
    onGameClicked: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = {
                    when (game) {
                        Game.Doom -> onGameClicked(R.string.toast_doom)
                        Game.GoldenEye007 -> onGameClicked(R.string.toast_goldeneye)
                        Game.SpaceInvaders -> onGameClicked(R.string.toast_spaceinvaders)
                        Game.SuperMarioBrothers -> onGameClicked(R.string.toast_supermariobrothers)
                        Game.DuckHunt,
                        Game.GodOfWar,
                        Game.GrandTheftAuto,
                        Game.HalfLife,
                        Game.Halo,
                        Game.Left4Dead,
                        Game.MarioKart,
                        Game.Pong,
                        Game.RedDeadRedemption,
                        Game.Tetris,
                        Game.WorldOfWarcraft,
                        Game.Zelda -> {}
                    }
                }
            )
            .fillMaxWidth()
            .height(
                height = Token.Spacing2xl,
            )
            .padding(
                end = Token.SpacingMedium,
                start = Token.SpacingMedium,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(
                fill = false,
                weight = Constant.WeightFull,
            ),
            color = Color.textIconNeutral,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.BodyLarge.Default,
            text = game.title,
        )

        SpacerMedium()

        Pill(
            color = game.console.color,
            emphasis =
                if (game.console == Console.Atari || game.console == Console.GameBoy) {
                    PillEmphasis.Weak
                } else {
                    PillEmphasis.Strong
                },
            text = game.console.text,
        )
    }
}

@PreviewDarkLightPhonePortrait
@PreviewDarkLightPhoneLandscape
@Composable
fun PreviewListItemGame() {
    StorybookTheme {
        LazyColumn {
            items(
                itemContent = { game: Game ->
                    ListItemGame(
                        game = game,
                        onGameClicked = {},
                    )
                },
                items = games,
            )
        }
    }
}
