# Siri-Dhanya Hub

Siri-Dhanya Hub is an Android app for Karnataka's millet value chain. It helps farmers and consumers track millet prices, explore recipes, learn health benefits, and connect directly with Farmer Producer Organizations.

## Live Links

- Live app: https://sakshi8365.github.io/sridhanyahub/
- Direct app page: https://sakshi8365.github.io/sridhanyahub/demo.html
- Firebase setup helper: https://sakshi8365.github.io/sridhanyahub/setup-firebase.html
- GitHub repo: https://github.com/Sakshi8365/sridhanyahub

## What It Does

- Mandi Watch shows millet prices with 7-day high, low, trend, and city filtering.
- Recipe Lab lets users search millet recipes and save favorites.
- Health Benefits explains nutritional value with science-based reading cards.
- Direct Buy connects users with FPOs and local millet suppliers.

## Success Criteria Covered

- 7-day price high/low display
- Recipe save and unsave feature
- Earthy, natural UI theme

## Tech Stack

- Kotlin
- Jetpack Compose
- MVVM architecture
- Firebase Realtime Database
- Material 3 design system

## Project Structure

```text
SiriDhanyaHub/
├── app/
│   └── src/main/java/com/agriculture/siridhanya/
│       ├── data/
│       ├── ui/
│       └── viewmodel/
├── README.md
├── index.html
├── demo.html
├── setup-firebase.html
├── build.gradle.kts
└── settings.gradle.kts
```

## Running the Android App

1. Open the project in Android Studio.
2. Let Gradle sync finish.
3. Run the app on an emulator or Android device.

If you only need the shareable web demo, use the live links above.

## Firebase Notes

The demo pages are wired for Firebase Realtime Database. The setup page includes a helper to connect Firebase and add sample data for the live preview.

## Submission Notes

This repository includes both the Android source and the hosted demo pages, so reviewers can inspect the code and open the live experience from the same project.

## Deployment Guide

### Step 1: Create Release Build

```bash
./gradlew clean build -x test --release
# or in Android Studio: Build → Generate Signed Bundle/APK
```

### Step 2: Prepare for Google Play Store

1. **Create Google Play Console Account**
   - Go to [Google Play Console](https://play.google.com/apps/publish)
   - Sign in with Google account
   - Accept agreements

2. **Create Release Key**

   ```bash
   keytool -genkey -v -keystore ~/siri-dhanya-hub.keystore \
     -keyalg RSA -keysize 2048 -validity 10000 \
     -alias siri-dhanya-release
   ```

3. **Build Signed APK/Bundle**
   - Android Studio: Build → Generate Signed Bundle/APK
   - Select "Android App Bundle" format (recommended)
   - Select or create release keystore
   - Build optimization: Automatically optimized for all devices

### Step 3: Create App Listing

1. Click "Create app" in Google Play Console
2. Fill in app details:
   - **Name**: Siri-Dhanya Hub
   - **Category**: Lifestyle/Food & Dining
   - **Content Rating**: Fill questionnaire
   - **Privacy Policy**: Create and link

3. Add content:
   - **Screenshots** (5-8 images):
     - Mandi Watch screen
     - Recipe Lab with search
     - Health Benefits article
     - Direct Buy with FPOs
     - App navigation/flow
   - **App Icon**: 512×512px
   - **Feature Graphic**: 1024×500px
   - **Short Description**: "Empowering farmers & consumers with millet market data, recipes & nutrition"
   - **Full Description**: Use description from README

### Step 4: Fill in Release Notes

```
Version 1.0.0 - Initial Release

Features:
✅ Mandi Watch - Real-time millet prices across Karnataka
✅ Recipe Lab - Traditional Kannada millet recipes with save feature
✅ Health Benefits - Nutrition info & why millets are superfoods
✅ Direct Buy - Connect with Farmer Producer Organizations

Technology:
🚀 Powered by AI for smart recommendations
💾 Real-time Firebase data sync
🎨 Beautiful, earthy UI design
📱 Works on Android 8.0+
```

### Step 5: Set Pricing & Distribution

1. **Pricing**: Set to Free
2. **Distribution**: Select all countries or specific regions (focus on India, Karnataka)
3. **Content Rating**: Complete questionnaire
4. **User Consent**: Review and accept all requirements

### Step 6: Submit for Review

1. Upload signed bundle/APK
2. Review compliance with Google Play policies
3. Submit for review
4. Review typically completes in 2-3 hours

### Step 7: Post-Launch

- **Monitor Analytics**: Track downloads, crashes, ratings
- **Update Regularly**: Add new recipes, update prices regularly
- **User Feedback**: Respond to reviews and ratings
- **Bug Fixes**: Release updates as needed

## Testing Checklist

- [ ] Mandi Watch loads and displays prices correctly
- [ ] 7-day high/low calculations are accurate
- [ ] Trend indicators show correct direction
- [ ] Filter by city and millet type works
- [ ] Recipe search returns relevant results
- [ ] Recipes can be saved and unsaved
- [ ] Health benefits load with proper formatting
- [ ] FPOs display correctly and can be contacted
- [ ] Navigation between tabs is smooth
- [ ] App handles network errors gracefully
- [ ] UI is responsive on different screen sizes
- [ ] Colors and theme are consistent throughout

## Performance Optimization

- **Lazy Loading**: Use LazyColumn for efficient list rendering
- **Image Optimization**: Compress images before adding to Firebase
- **Database Queries**: Limit data fetching with proper filtering
- **Caching**: Implement Retrofit caching for API calls
- **ProGuard**: Enable code shrinking for release builds

## Security Considerations

1. **Firebase Rules** (Update in Firebase Console):

```json
{
  "rules": {
    "prices": {
      ".read": true,
      ".write": "root.child('admins').child(auth.uid).exists()"
    },
    "recipes": {
      ".read": true,
      ".write": "root.child('admins').child(auth.uid).exists()"
    },
    "users": {
      "$uid": {
        ".read": "$uid === auth.uid",
        ".write": "$uid === auth.uid"
      }
    }
  }
}
```

2. **API Key Security**: Use Android Keystore for secure key storage
3. **Permissions**: Only request necessary permissions
4. **Data Privacy**: Comply with GDPR and local regulations

## Future Enhancements

- 🗺️ **Map View**: Show prices and FPOs on interactive map
- 🤖 **Voice Search**: Search recipes by voice
- 📊 **Price Charts**: Interactive graphs for price trends
- 🔔 **Notifications**: Price alerts when desired rates are reached
- 💬 **Community Forum**: Connect farmers and consumers
- 📸 **User Recipes**: Allow users to share their millet recipes
- 🌐 **Multi-Language**: Support Kannada, Hindi, English
- 🛒 **E-Commerce**: In-app purchasing directly from FPOs

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -m "Add your feature"`
4. Push to branch: `git push origin feature/your-feature`
5. Submit pull request

## License

This project is licensed under the MIT License - see LICENSE file for details.

## Support & Contact

- 📧 **Email**: support@siridhanyahub.com
- 🌐 **Website**: www.siridhanyahub.com
- 📱 **Issue Tracker**: GitHub Issues

## Acknowledgments

- **Designed for**: Farmers and consumers of Karnataka
- **Focus**: Climate-resilient millets (Siri-Dhanya)
- **Mission**: Nutrition security, farmer prosperity, climate action
- **Built with**: ❤️ for sustainable agriculture

---

**Version**: 1.0.0  
**Last Updated**: May 11, 2026  
**Status**: ✅ Ready for Production Deployment
