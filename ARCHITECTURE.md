# XO Game - Architecture & Flow

## Application Flow

```
┌─────────────────────────────┐
│      Launch App             │
│   (MainActivity)            │
│                             │
│  ┌─────────────────────┐   │
│  │   XO Game Title     │   │
│  └─────────────────────┘   │
│                             │
│  ┌─────────────────────┐   │
│  │  Play vs AI         │◄──┼── Click
│  └─────────────────────┘   │
│                             │
│  ┌─────────────────────┐   │
│  │  Local Multiplayer  │◄──┼── Click
│  └─────────────────────┘   │
└─────────────────────────────┘
         │         │
         │         │
         ▼         ▼
┌─────────────────────────────┐
│      Game Screen            │
│    (GameActivity)           │
│                             │
│  ┌─────────────────────┐   │
│  │  Player X's Turn    │   │
│  └─────────────────────┘   │
│                             │
│  ┌───────┬───────┬───────┐ │
│  │   X   │   O   │       │ │
│  ├───────┼───────┼───────┤ │
│  │       │   X   │   O   │ │
│  ├───────┼───────┼───────┤ │
│  │   O   │       │   X   │ │
│  └───────┴───────┴───────┘ │
│                             │
│  ┌───────────┬───────────┐ │
│  │Play Again │Main Menu  │ │
│  └───────────┴───────────┘ │
└─────────────────────────────┘
```

## Class Architecture

```
┌───────────────────────────────────────────┐
│          AppCompatActivity                │
│         (Android Framework)               │
└───────────────┬───────────────────────────┘
                │
       ┌────────┴────────┐
       │                 │
┌──────▼──────┐  ┌───────▼────────┐
│MainActivity │  │ GameActivity   │
│             │  │                │
│- btnPlayVsAI│  │- board[9]      │
│- btnPlayLocal  │- buttons[9]    │
│             │  │- isPlayerXTurn │
│Methods:     │  │- vsAI          │
│+ onCreate() │  │- gameOver      │
│+ startGame()│  │                │
└─────────────┘  │Methods:        │
                 │+ onCreate()    │
                 │+ onCellClicked()
                 │+ makeMove()    │
                 │+ checkWinner() │
                 │+ aiMove()      │
                 │+ minimax()     │
                 │+ resetGame()   │
                 └────────────────┘
```

## AI Decision Tree (Minimax)

```
                Current Board State
                        │
        ┌───────────────┼───────────────┐
        │               │               │
    Move 1          Move 2          Move 3
        │               │               │
    ┌───┴───┐       ┌───┴───┐       ┌───┴───┐
    │       │       │       │       │       │
  Min     Min     Min     Min     Min     Min
  │       │       │       │       │       │
Score   Score   Score   Score   Score   Score
  -10     0      +10     -10     0      +10
        │               │               │
        └───────────────┼───────────────┘
                        │
                  Choose Best Move
                  (Maximum Score)
```

## Game State Flow

```
┌─────────┐
│  Start  │
└────┬────┘
     │
     ▼
┌─────────────┐
│ Player X    │◄───────┐
│   Turn      │        │
└────┬────────┘        │
     │                 │
     ▼                 │
┌─────────────┐        │
│ Make Move   │        │
└────┬────────┘        │
     │                 │
     ▼                 │
┌─────────────┐        │
│ Check Win?  │        │
└────┬────────┘        │
     │                 │
  ┌──┴──┐              │
  │     │              │
 Yes    No             │
  │     │              │
  │     ▼              │
  │ ┌────────────┐    │
  │ │ Board Full?│    │
  │ └──┬─────────┘    │
  │    │              │
  │ ┌──┴──┐           │
  │ │     │           │
  │Yes    No          │
  │ │     │           │
  │ │     ▼           │
  │ │ ┌──────────┐   │
  │ │ │Switch    │   │
  │ │ │Player    │───┘
  │ │ └──────────┘
  │ │
  │ ▼
  │┌──────────┐
  ││  Draw    │
  │└────┬─────┘
  │     │
  ▼     ▼
┌─────────────┐
│  Game Over  │
│ Show Result │
└─────────────┘
```

## Win Detection Patterns

```
Rows:          Columns:        Diagonals:
0 1 2          0 3 6           0 4 8
3 4 5          1 4 7           2 4 6
6 7 8          2 5 8

Grid Layout:
┌───┬───┬───┐
│ 0 │ 1 │ 2 │
├───┼───┼───┤
│ 3 │ 4 │ 5 │
├───┼───┼───┤
│ 6 │ 7 │ 8 │
└───┴───┴───┘
```

## Resource Hierarchy

```
app/src/main/res/
├── drawable/
│   ├── button_bg.xml      (Button background shape)
│   ├── cell_bg.xml        (Game cell background)
│   ├── ic_launcher_background.xml
│   └── ic_launcher_foreground.xml
├── layout/
│   ├── activity_main.xml  (Menu layout)
│   └── activity_game.xml  (Game board layout)
├── mipmap-anydpi-v26/
│   └── ic_launcher.xml    (App icon)
└── values/
    ├── colors.xml         (Color definitions)
    ├── strings.xml        (Text resources)
    └── styles.xml         (UI styles)
```

## Build System

```
Gradle Build Flow:
┌──────────────┐
│settings.gradle
│(Project Def) │
└───────┬──────┘
        │
        ▼
┌──────────────┐
│build.gradle  │
│(Root Level)  │
│- Repositories│
│- Dependencies│
└───────┬──────┘
        │
        ▼
┌──────────────┐
│app/          │
│build.gradle  │
│- SDK config  │
│- Dependencies│
│- Build types │
└───────┬──────┘
        │
        ▼
┌──────────────┐
│Compile       │
│Java + XML    │
└───────┬──────┘
        │
        ▼
┌──────────────┐
│Package APK   │
└──────────────┘
```

## Key Technologies

```
┌─────────────────────────────────┐
│     Android Framework           │
│  (API Level 21 - 33)            │
├─────────────────────────────────┤
│  - AppCompatActivity            │
│  - Material Design Components   │
│  - ConstraintLayout             │
└─────────────────────────────────┘
         │         │         │
         │         │         │
┌────────▼────┐ ┌──▼──────┐ ┌▼──────────┐
│   Java 8    │ │  Gradle │ │   XML     │
│   Code      │ │   7.5   │ │ Resources │
└─────────────┘ └─────────┘ └───────────┘
```

## Dependencies

```
androidx.appcompat:appcompat:1.6.1
    │
    ├─> Backward compatibility
    └─> AppCompatActivity

com.google.android.material:material:1.9.0
    │
    ├─> Material Design Components
    ├─> Buttons, TextViews
    └─> Color theming

androidx.constraintlayout:constraintlayout:2.1.4
    │
    ├─> Flexible layouts
    └─> GridLayout support
```
