package com.heckofanapp.storybook

enum class Game(
    val console: Console,
    val title: String,
) {
    Doom(
        console = Console.Windows,
        title = "Doom",
    ),
    DuckHunt(
        console = Console.Nintendo,
        title = "Duck Hunt",
    ),
    GodOfWar(
        console = Console.PlayStation,
        title = "God of War",
    ),
    GoldenEye007(
        console = Console.Nintendo64,
        title = "GoldenEye 007",
    ),
    GrandTheftAuto(
        console = Console.PlayStation,
        title = "Grand Theft Auto",
    ),
    HalfLife(
        console = Console.Windows,
        title = "Half-Life",
    ),
    Halo(
        console = Console.Xbox360,
        title = "Halo",
    ),
    Left4Dead(
        console = Console.Xbox360,
        title = "Left 4 Dead",
    ),
    MarioKart(
        console = Console.Nintendo64,
        title = "Mario Kart",
    ),
    Pong(
        console = Console.Atari,
        title = "Pong",
    ),
    RedDeadRedemption(
        console = Console.PlayStation,
        title = "Red Dead Redemption",
    ),
    SpaceInvaders(
        console = Console.Atari,
        title = "Space Invaders",
    ),
    SuperMarioBrothers(
        console = Console.Nintendo,
        title = "Super Mario Brothers",
    ),
    Tetris(
        console = Console.GameBoy,
        title = "Tetris",
    ),
    WorldOfWarcraft(
        console = Console.Windows,
        title = "World of Warcraft",
    ),
    Zelda(
        console = Console.Nintendo,
        title = "Zelda",
    ),
}

val games = listOf(
    Game.Doom,
    Game.DuckHunt,
    Game.GodOfWar,
    Game.GoldenEye007,
    Game.GrandTheftAuto,
    Game.HalfLife,
    Game.Halo,
    Game.Left4Dead,
    Game.MarioKart,
    Game.Pong,
    Game.RedDeadRedemption,
    Game.SpaceInvaders,
    Game.SuperMarioBrothers,
    Game.Tetris,
    Game.WorldOfWarcraft,
    Game.Zelda,
)
