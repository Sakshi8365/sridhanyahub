# Siri-Dhanya Hub - Quick Start Guide

## 5-Minute Setup

### 1. Install Android Studio

- Download from [developer.android.com](https://developer.android.com/studio)
- Install and complete initial setup

### 2. Clone Project

```bash
git clone https://github.com/yourusername/SiriDhanyaHub.git
cd SiriDhanyaHub
```

### 3. Open in Android Studio

- File → Open → Select SiriDhanyaHub folder
- Wait for Gradle sync to complete

### 4. Add Firebase Config

- Replace `google-services.json` with your Firebase config
- Or set up Firebase project: https://console.firebase.google.com

### 5. Run on Emulator

- Click "Run" button (▶) or press Shift+F10
- Select Android Emulator
- App launches with mock data

### 6. Test Features

- **Mandi Watch**: Swipe right or tap "Mandi" tab - view live prices
- **Recipe Lab**: Tap "Recipes" tab - search and save recipes
- **Health Benefits**: Tap "Health" tab - read nutritional info
- **Direct Buy**: Tap "Buy" tab - connect with farmers

## Common Issues & Fixes

### Issue: Gradle Sync Failed

**Solution**:

```bash
./gradlew clean
./gradlew build
```

### Issue: Google Play Services Error

**Solution**:

- File → Project Structure → SDK Manager
- Download latest Google Play Services
- Update build.gradle.kts

### Issue: App Crashes on Launch

**Solution**:

- Check Firebase connection in google-services.json
- Verify internet connectivity
- Check Logcat for errors: View → Tool Windows → Logcat

### Issue: Images Not Loading

**Solution**:

- Ensure image URLs are valid
- Check internet permissions in AndroidManifest.xml
- Verify Glide dependency is installed

## Firebase Test Data

To populate with test data:

1. Go to Firebase Console
2. Select your project
3. Go to Realtime Database
4. Click "Import JSON"
5. Use data from `sample-data.json`

## Development Tips

### Adding New Recipe

```kotlin
// In RecipeRepository.kt
suspend fun addRecipe(recipe: Recipe) {
    val id = recipesRef.push().key ?: return
    recipesRef.child(id).setValue(recipe.copy(id = id))
}
```

### Adding New City/Millet Type

1. Add to filter chips in screens
2. Add sample prices to Firebase
3. Update mock data in MockData.kt

### Customizing Colors

- Edit `res/values/colors.xml`
- Colors referenced throughout app will auto-update

### Building for Release

```bash
# Generate signed APK/Bundle
./gradlew bundleRelease -Pandroid.injected.signing.store.file=path/to/keystore \
  -Pandroid.injected.signing.store.password=password \
  -Pandroid.injected.signing.key.alias=alias \
  -Pandroid.injected.signing.key.password=keypassword
```

## Documentation Links

- [Android Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Firebase Realtime Database](https://firebase.google.com/docs/database)
- [Kotlin Language](https://kotlinlang.org/docs/home.html)
- [Material Design 3](https://m3.material.io/)

## Support

Issues? Check:

1. README.md - Full documentation
2. GitHub Issues - Known problems
3. Firebase Console - Data connectivity
4. Android Studio Logcat - Error messages

---

**Happy Coding! 🌾**
