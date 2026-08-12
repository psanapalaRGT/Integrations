# NariMitra — Android app

Native Android wrapper for the **NariMitra** interactive prototype
("a friend to every woman" — 21 mobile screens in a single HTML file).

The prototype lives at `app/src/main/assets/index.html` and is rendered
full-screen in a WebView, served from a secure `https://appassets.androidapp.com`
origin via `WebViewAssetLoader`. It works fully offline; with a network
connection it additionally pulls the Google Fonts the design links
(Rozha One, Hanken Grotesk, DM Mono), otherwise system fonts are used.

## Install the APK on a phone

1. Download `apk/NariMitra-v1.0.0.apk` from this repo (or the
   `NariMitra-apk` artifact of the latest **Build NariMitra APK** run under
   the GitHub *Actions* tab).
2. Open the file on the phone. Android will ask you to allow installs
   from that source ("Install unknown apps") — allow it once.
3. Tap **Install**. Requires Android 8.0 (API 26) or newer.

Later builds install straight over the old one (same signature), keep the
`versionCode` increasing in `app/build.gradle.kts` when you ship changes.

## Build it yourself

Requirements: JDK 17+ and the Android SDK (platform 35, build-tools 35.0.0);
point `ANDROID_HOME` or `local.properties` (`sdk.dir=...`) at the SDK.

```bash
./gradlew assembleRelease
# → app/build/outputs/apk/release/app-release.apk
```

Every push touching `narimitra-android/` also builds the APK in GitHub
Actions (`.github/workflows/android-build.yml`).

## Updating the prototype

Replace `app/src/main/assets/index.html` with the new export, bump
`versionCode`/`versionName`, and rebuild — nothing else references the
file's contents.

## Signing note

`keystore/narimitra.jks` is a **prototype-only** keystore committed on
purpose so local and CI builds share one signature (passwords are in
`app/build.gradle.kts`). Anyone with repo access can sign as this app —
generate a private keystore before any real distribution.
