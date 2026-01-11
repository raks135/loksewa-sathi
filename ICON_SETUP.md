# App Icon Setup Instructions

Since we need to generate multiple icon sizes from logo.jpg, you have two options:

## Option 1: Use Android Studio (Recommended)
1. Open the project in Android Studio
2. Right-click on `app/src/main/res` folder
3. Select **New → Image Asset**
4. Choose **Launcher Icons (Adaptive and Legacy)**
5. Select **Image** as Icon Type
6. Browse and select `logo.jpg`
7. Click **Next** and **Finish**

This will automatically generate all required icon sizes.

## Option 2: Use Online Tool
1. Go to https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html
2. Upload `logo.jpg`
3. Download the generated icon pack
4. Extract and copy all folders to `app/src/main/res/`

## Option 3: Manual PowerShell Script (Below)

I'll create a script to copy the logo and update the manifest to use it.

## Current Setup
- Logo file: `logo.jpg` (33KB)
- Will be used for app launcher icon
- GitHub Actions workflow created for automatic builds
