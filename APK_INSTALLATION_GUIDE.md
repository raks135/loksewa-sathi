# APK Download and Installation Guide

## Issue: "Package appears to be invalid"

When you download APK from GitHub Artifacts, it comes as a **ZIP file**, not directly as an APK.

## Solution:

### Step 1: Extract the ZIP file
1. Download the artifact from GitHub Actions (e.g., `app-debug.zip`)
2. **Extract/Unzip** the file
3. Inside you'll find the actual `app-debug.apk` file

### Step 2: Install the APK
1. Transfer the extracted APK to your Android device
2. Enable "Install from Unknown Sources" in Settings
3. Tap the APK file to install

## Alternative: Build APK Locally

If you want to build the APK directly on your computer:

### Using Gradle (Recommended)

```bash
# Navigate to project directory
cd c:\loksewa-sathi

# Build debug APK
.\gradlew.bat assembleDebug

# APK location:
# app\build\outputs\apk\debug\app-debug.apk
```

### Using Android Studio

1. Open project in Android Studio
2. Click **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
3. Wait for build to complete
4. Click **locate** to find the APK

## APK Locations

- **Debug APK:** `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK:** `app/build/outputs/apk/release/app-release-unsigned.apk`

## Installing on Android Device

### Method 1: USB Transfer
1. Connect device via USB
2. Copy APK to device
3. Open file manager on device
4. Tap APK to install

### Method 2: ADB Install
```bash
adb install app\build\outputs\apk\debug\app-debug.apk
```

### Method 3: Email/Cloud
1. Email APK to yourself
2. Open email on Android device
3. Download and install

## Troubleshooting

**"App not installed"**
- Enable "Install from Unknown Sources"
- Check if app is already installed (uninstall first)
- Ensure APK is not corrupted

**"Parse error"**
- Make sure you extracted the ZIP file
- Verify APK file size is reasonable (should be ~5-10 MB)
- Re-download if file seems corrupted

**"Incompatible architecture"**
- App requires Android 7.0 (API 24) or higher
- Check your device's Android version
