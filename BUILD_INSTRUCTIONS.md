# How to Build and Run the App

## Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 8 or later
- Android SDK with API level 24 or higher

## Setup Instructions

### 1. Open Project in Android Studio
1. Launch Android Studio
2. Click "Open" and navigate to the `loksewa-sathi` directory
3. Wait for Gradle sync to complete

### 2. Sync Gradle
If Gradle doesn't sync automatically:
1. Click "File" → "Sync Project with Gradle Files"
2. Wait for dependencies to download

### 3. Build the Project

#### Using Android Studio:
1. Click "Build" → "Make Project" (or press Ctrl+F9)
2. Wait for build to complete

#### Using Command Line:
```bash
# On Windows
gradlew.bat assembleDebug

# On Mac/Linux
./gradlew assembleDebug
```

### 4. Run on Emulator

1. Create an Android Virtual Device (AVD):
   - Click "Tools" → "Device Manager"
   - Click "Create Device"
   - Select a phone (e.g., Pixel 6)
   - Select a system image (API 24 or higher)
   - Click "Finish"

2. Run the app:
   - Click the "Run" button (green play icon)
   - Select your emulator
   - Wait for app to launch

### 5. Run on Physical Device

1. Enable Developer Options on your Android device:
   - Go to Settings → About Phone
   - Tap "Build Number" 7 times

2. Enable USB Debugging:
   - Go to Settings → Developer Options
   - Enable "USB Debugging"

3. Connect device via USB

4. Run the app:
   - Click the "Run" button
   - Select your device
   - Click "OK"

## Generate APK

### Debug APK:
```bash
gradlew.bat assembleDebug
```
APK location: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK:
```bash
gradlew.bat assembleRelease
```
APK location: `app/build/outputs/apk/release/app-release-unsigned.apk`

## Troubleshooting

### Gradle Sync Failed
- Check internet connection
- Click "File" → "Invalidate Caches / Restart"
- Delete `.gradle` folder and sync again

### Build Errors
- Ensure JDK is properly configured
- Check Android SDK is installed
- Update Gradle if prompted

### App Crashes
- Check Logcat for error messages
- Ensure minimum SDK version is 24
- Verify JSON files are in correct location

## Adding More Questions

1. Navigate to `app/src/main/assets/questions/`
2. Edit the JSON file for the topic you want to update
3. Follow the JSON format shown in existing files
4. Rebuild the app

## Project Structure

```
loksewa-sathi/
├── app/
│   ├── src/main/
│   │   ├── java/com/loksewasathi/  # Kotlin source files
│   │   ├── assets/questions/        # JSON question files
│   │   └── res/                     # Resources (layouts, strings, etc.)
│   └── build.gradle.kts             # App-level Gradle config
├── gradle/                          # Gradle wrapper
├── build.gradle.kts                 # Project-level Gradle config
└── settings.gradle.kts              # Gradle settings
```
