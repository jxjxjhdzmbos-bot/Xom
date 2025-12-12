# Build and Setup Instructions

## Prerequisites

To build this Android project, you need:

1. **Java Development Kit (JDK)**
   - JDK 8 or higher
   - Verify: `java -version`

2. **Android SDK**
   - Install via Android Studio or command-line tools
   - Minimum API Level: 21 (Android 5.0)
   - Recommended API Level: 33 (Android 13)

3. **Gradle**
   - Included via wrapper (gradlew)
   - Version: 7.5

## Quick Start

### Option 1: Using Android Studio (Recommended)

1. Open Android Studio
2. Select "Open an Existing Project"
3. Navigate to this directory
4. Wait for Gradle sync to complete
5. Click "Run" button or press Shift+F10

### Option 2: Command Line

```bash
# Build the project
./gradlew build

# Install on connected device/emulator
./gradlew installDebug

# Build APK for release
./gradlew assembleRelease
```

## Project Configuration

- **Application ID**: com.xo.game
- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 33 (Android 13)
- **Version**: 1.0 (Code: 1)

## Troubleshooting

### Gradle Sync Fails
- Ensure you have internet connectivity for dependency downloads
- Check that ANDROID_HOME environment variable is set
- Try: `./gradlew clean build --refresh-dependencies`

### SDK Not Found
- Install Android SDK via Android Studio SDK Manager
- Or set ANDROID_HOME environment variable:
  - Linux/Mac: `export ANDROID_HOME=~/Android/Sdk`
  - Windows: `set ANDROID_HOME=C:\Users\<username>\AppData\Local\Android\Sdk`

### Build Fails
- Check Java version: Should be JDK 8 or higher
- Ensure all SDK components are installed
- Clean build: `./gradlew clean`

## Running the App

### On Emulator
1. Create an AVD in Android Studio (Tools > AVD Manager)
2. Start the emulator
3. Run: `./gradlew installDebug`

### On Physical Device
1. Enable Developer Options on your device
2. Enable USB Debugging
3. Connect device via USB
4. Verify connection: `adb devices`
5. Run: `./gradlew installDebug`

## Output Files

After building, APK files will be located at:
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release-unsigned.apk`

## Testing

Currently, this project focuses on core functionality. To add tests:

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```
