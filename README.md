# Loksewa Sathi - Android MCQ App

A beautiful Android mobile application for Loksewa (Public Service Commission) General Knowledge MCQ practice built with Kotlin and Jetpack Compose.

## Features

- 🎨 **Beautiful Material Design 3 UI** with vibrant gradients and smooth animations
- 📚 **12 Topic Categories** covering all Loksewa exam subjects
- 🎯 **Interactive Quiz Interface** with instant feedback
- 📊 **Progress Tracking** and detailed statistics
- 🌙 **Dark Mode** support
- 🔄 **Offline Access** with local JSON storage
- ✨ **Smooth Animations** and micro-interactions

## Topics Covered

1. **World Geography** (विश्व भूगोल) - Physical, Political, Economic Geography
2. **Nepal Geography** (नेपाल भूगोल) - Nepal's Physical & Political Geography
3. **Nepal History** (नेपालको इतिहास) - Ancient, Medieval, Modern History
4. **World History** (विश्व इतिहास) - Major World Historical Events
5. **General Science** (सामान्य विज्ञान) - Basic Science Concepts
6. **Biology** (जीवविज्ञान) - Life Science & Biology
7. **Physics** (भौतिकशास्त्र) - Physical Science & Mechanics
8. **Chemistry** (रसायनशास्त्र) - Chemical Science & Reactions
9. **Constitution** (संविधान) - Nepal Constitution & Laws
10. **Current Affairs** (समसामयिक घटनाक्रम) - National & International Events
11. **General Awareness** (सामान्य ज्ञान) - Literature, Arts, Culture
12. **Mental Ability** (मानसिक क्षमता) - IQ & Aptitude Tests

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Navigation**: Navigation Compose
- **Data Storage**: DataStore Preferences
- **JSON Parsing**: Gson
- **Material Design**: Material Design 3

## Project Structure

```
app/
├── src/main/
│   ├── java/com/loksewasathi/
│   │   ├── models/          # Data models
│   │   ├── repository/      # Data repositories
│   │   ├── ui/
│   │   │   ├── splash/      # Splash screen
│   │   │   ├── dashboard/   # Main dashboard
│   │   │   ├── quiz/        # Quiz interface
│   │   │   ├── result/      # Results screen
│   │   │   ├── review/      # Answer review
│   │   │   └── theme/       # App theme
│   │   ├── navigation/      # Navigation setup
│   │   ├── utils/           # Utilities
│   │   └── MainActivity.kt
│   ├── assets/questions/    # JSON question files
│   └── res/                 # Resources
```

## Building the App

### Prerequisites
- Android Studio Hedgehog or later
- JDK 8 or later
- Android SDK 24 or later

### Build Instructions

1. Open the project in Android Studio
2. Sync Gradle files
3. Build the project:
   ```bash
   ./gradlew assembleDebug
   ```
4. Run on emulator or device

### Generate APK
```bash
./gradlew assembleRelease
```

The APK will be generated in `app/build/outputs/apk/release/`

## Adding Questions

To add questions for a topic:

1. Create a JSON file in `app/src/main/assets/questions/`
2. Follow this format:
```json
{
    "meta": {
        "title": "Topic Name",
        "language": "ne",
        "source": "Source",
        "version": "1.0",
        "count": 20
    },
    "questions": [
        {
            "id": "unique_id",
            "topic": "Topic",
            "question": "Question text?",
            "options": {
                "A": "Option A",
                "B": "Option B",
                "C": "Option C",
                "D": "Option D"
            },
            "correctAnswer": "A"
        }
    ]
}
```

## Screenshots

The app features:
- Gradient splash screen with app branding
- Dashboard with topic cards in 2-column grid
- Quiz interface with color-coded answers
- Results screen with animated statistics
- Review screen showing all answers

## License

This project is created for educational purposes.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
