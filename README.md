# XO Game (Tic-Tac-Toe)

An Android Java implementation of the classic XO (Tic-Tac-Toe) game with a clean UI, offline AI opponent, and local multiplayer mode.

## Features

- **Clean Material Design UI**: Simple and intuitive 3x3 grid interface
- **Offline AI Mode**: Play against an intelligent AI opponent using the Minimax algorithm
- **Local Multiplayer**: Two players can play on the same device
- **Smart AI**: Unbeatable AI that uses optimal strategy
- **Visual Feedback**: Highlighted winning cells and clear game status
- **Game Controls**: Reset game and return to main menu options

## Requirements

- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 33 (Android 13)
- **Build Tools**: Gradle 7.5
- **Android Gradle Plugin**: 7.4.2

## Project Structure

```
Xom/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/xo/game/
│   │       │   ├── MainActivity.java      # Main menu activity
│   │       │   └── GameActivity.java      # Game logic and AI
│   │       ├── res/
│   │       │   ├── layout/                # UI layouts
│   │       │   ├── values/                # Strings, colors, styles
│   │       │   ├── drawable/              # UI drawables
│   │       │   └── mipmap-*/              # App icons
│   │       └── AndroidManifest.xml
│   └── build.gradle                       # App-level build config
├── build.gradle                           # Project-level build config
├── settings.gradle                        # Project settings
└── gradle/                                # Gradle wrapper files
```

## Building the Project

### Prerequisites

1. Install [Android Studio](https://developer.android.com/studio)
2. Install Android SDK (API Level 21 or higher)
3. Java Development Kit (JDK 8 or higher)

### Build Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/jxjxjhdzmbos-bot/Xom.git
   cd Xom
   ```

2. Open the project in Android Studio:
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. Build the project:
   ```bash
   ./gradlew build
   ```

4. Run on an emulator or device:
   - Connect an Android device or start an emulator
   - Click "Run" in Android Studio or use:
   ```bash
   ./gradlew installDebug
   ```

## How to Play

1. **Launch the app** and choose your game mode:
   - **Play vs AI**: Challenge the computer opponent
   - **Local Multiplayer**: Play with a friend on the same device

2. **Game Rules**:
   - Players take turns placing X or O on the 3x3 grid
   - First player to get 3 in a row (horizontal, vertical, or diagonal) wins
   - If all cells are filled with no winner, the game is a draw

3. **Game Controls**:
   - Tap any empty cell to make your move
   - Click "Play Again" to restart with the same mode
   - Click "Main Menu" to return to mode selection

## Technical Implementation

### AI Algorithm

The AI opponent uses the **Minimax algorithm**, a decision-making algorithm that:
- Explores all possible game states
- Evaluates each position's value
- Chooses the optimal move to maximize AI's winning chances
- Provides an unbeatable challenge for players

### Key Components

- **MainActivity.java**: Entry point with game mode selection
- **GameActivity.java**: Core game logic including:
  - Board state management
  - Win/draw detection
  - Turn switching
  - AI move calculation
  - UI updates

### Dependencies

- `androidx.appcompat:appcompat:1.6.1` - Android compatibility library
- `com.google.android.material:material:1.9.0` - Material Design components
- `androidx.constraintlayout:constraintlayout:2.1.4` - Layout system

## Game Screenshots

The game features:
- A welcoming main menu with two large buttons
- A 3x3 grid game board with clear X and O markers
- Color-coded players (Pink for X, Cyan for O)
- Highlighted winning cells when a player wins
- Status text showing current turn or game result

## License

This project is open source and available for educational purposes.

## Author

Created as a demonstration of Android development best practices.