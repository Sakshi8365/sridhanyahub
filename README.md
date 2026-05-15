# SiriDhanyaHub

> A comprehensive Android app + web demo project. This repository contains the Android app source, Gradle build files, sample data and static demo pages.

---

## Project Overview

- **Name:** SiriDhanyaHub
- **Type:** Android application (Kotlin) with static web demo pages
- **Build system:** Gradle (Kotlin DSL)
- **Location:** Root contains Gradle wrappers and `app/` module with Android sources

This README provides everything needed to build, run and contribute to the project.

---

## Purpose

This project demonstrates a small Android application backed by static demo pages and sample data. It aims to showcase basic mobile features (data display, Firebase integration hooks, and a simple UI) and to serve as a reference template for students or developers learning Android + Firebase workflows.


## Quick Start

Prerequisites:

- JDK 11 or newer installed and `JAVA_HOME` set.
- Android SDK and command-line tools (recommended via Android Studio).
- For Firebase features: a `google-services.json` placed inside `app/` (already present for reference).

Recommended: open the project in Android Studio for the best developer experience.

Build the debug APK (Windows):

```powershell
.\gradlew.bat assembleDebug
```

Build & install on a connected device (Windows):

```powershell
.\gradlew.bat installDebug
```

On macOS / Linux use the wrapper without `.bat`:

```bash
./gradlew assembleDebug
./gradlew installDebug
```

Open in Android Studio:

1. Launch Android Studio.
2. Choose "Open" and select the repository root folder (SiriDhanyaHub).
3. Let Android Studio sync Gradle and install required SDK components.

---

## Project Structure

- `app/` — Android module (source code, resources, manifest, `google-services.json`).
- `build.gradle.kts` — Root Gradle build configuration.
- `gradle/` — Gradle wrapper files.
- `demo.html`, `index.html`, `setup-firebase.html` — Static demo / helper pages.
- `sample-data.json` — Example data used by demos or tests.

---

## Features

- Android demo app with Kotlin sources and resource examples.
- Firebase-ready configuration (replace `app/google-services.json` with your project file).
- Static demo pages (`index.html`, `demo.html`) that present sample data and usage examples.
- Gradle Kotlin DSL build files and a ready-to-open Android Studio project.

## Demo

You can preview the project's static demos in the repository:

- Demo page: https://github.com/Sakshi8365/sridhanyahub/blob/main/demo.html
- Main demo/index: https://github.com/Sakshi8365/sridhanyahub/blob/main/index.html

To view the HTML demo locally, open the `index.html` or `demo.html` file in a browser.


## Firebase

- Firebase configuration file `google-services.json` should be placed inside `app/` for build-time injection of Firebase IDs and settings.
- The repository contains a `app/google-services.json` example; replace it with your project-specific file when deploying.
- If you modify Firebase services, verify the `app/build.gradle.kts` keeps the `com.google.gms.google-services` plugin and dependencies intact.

---

## Running and Debugging

- Use Android Studio to run the app on an emulator or physical device.
- To run from command line:

```bash
./gradlew installDebug
adb logcat -s "SiriDhanyaHub"  # or use Android Studio Logcat
```

- Use `sample-data.json` for manual testing of static demo pages in the browser.

---

## Tests

- Unit / instrumentation tests (if present) can be run with Gradle tasks:

```bash
./gradlew test
./gradlew connectedAndroidTest
```

Add tests in `app/src/test/` and `app/src/androidTest/` as needed.

---

## Troubleshooting

- Gradle sync failures:
  - Ensure the correct JDK version is used by Android Studio (`File → Project Structure → SDK Location`).
  - Delete `.gradle/` and `app/build/` to force a clean rebuild: `./gradlew clean`.
- Missing Android SDK:
  - Install via Android Studio SDK Manager or set `ANDROID_SDK_ROOT` to your SDK path.
- Firebase build problems:
  - Ensure `google-services.json` is valid and placed in `app/`.

---

## Contribution

Please follow these guidelines:

- Open an issue for new features or bugs.
- Create a branch per feature/bugfix: `git checkout -b feat/your-feature`.
- Keep commits focused and include a descriptive message.
- When ready, open a pull request describing the change and any testing steps.

If you want to add coding standards, linters, or CI, open an issue so maintainers can discuss preferred tools.

---

## Deployment Checklist

- Replace `app/google-services.json` with production Firebase config.
- Verify ProGuard / R8 rules in `app/proguard-rules.pro` for release builds.
- Build a release APK / AAB:

```bash
./gradlew assembleRelease
```

- Test the signed release on devices before publishing.

---

## Useful Commands

- Clean build: `./gradlew clean`
- Assemble debug: `./gradlew assembleDebug`
- Install debug: `./gradlew installDebug`
- Run unit tests: `./gradlew test`

On Windows replace `./gradlew` with `gradlew.bat` or `.
\gradlew.bat` when using PowerShell.

---

## Notes & TODOs

- Add a `LICENSE` file if you want to set an open-source license (MIT, Apache 2.0, etc.).
- Consider adding CI (GitHub Actions) to run builds and tests on PRs.

---

## Maintainers / Contact

If you need help, open an issue in this repository or contact the maintainers listed in `PROJECT_REPORT.md`.

----
