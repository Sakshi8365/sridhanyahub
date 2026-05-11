# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontobfuscate

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable

# Firebase
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }

# Jetpack Compose
-keep class androidx.compose.** { *; }
-keep class androidx.lifecycle.** { *; }

# Glide
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep class * extends com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

# Google Generative AI
-keep class com.google.ai.client.generativeai.** { *; }

# Models
-keep class com.agriculture.siridhanya.data.model.** { *; }

# View Models
-keep class com.agriculture.siridhanya.viewmodel.** { *; }

# Repositories
-keep class com.agriculture.siridhanya.data.repository.** { *; }
