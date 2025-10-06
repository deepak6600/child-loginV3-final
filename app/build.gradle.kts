plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.safe.setting.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.safe.setting.app"
        minSdk = 23
        targetSdk = 36
        versionCode = 6
        versionName = "6.60006"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    // 'packagingOptions' has been renamed to 'packaging' in newer AGP versions.
    packaging {
        // RxJava 3 doesn't need this exclusion anymore, but keeping it in case of other conflicts.
        resources.excludes.add("META-INF/rxjava.properties")
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

// Set the JVM toolchain for Kotlin, which is the modern way to set the JVM target.
kotlin {
    jvmToolchain(17)
}

dependencies {
    // Core Android libraries
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("androidx.work:work-runtime-ktx:2.10.5")

    // Android Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:34.3.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.firebase:firebase-analytics")



//    val supabaseVersion = "3.0.0" // माइग्रेशन गाइड के अनुसार संस्करण 3.0.0
    implementation(platform("io.github.jan-tennert.supabase:bom:3.2.4"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:storage-kt")
    implementation("io.github.jan-tennert.supabase:auth-kt") // got rue-kt की जगह auth-kt
    implementation("io.github.jan-tennert.supabase:realtime-kt") // Realtime के लिए जोड़ा गया

    implementation("io.ktor:ktor-client-okhttp:3.3.0")


    // लॉजिक: minSdk 26 से कम होने पर Java 8+ APIs को सपोर्ट करने के लिए desugaring आवश्यक है।
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")



    // Glide Image Loading
    implementation("com.github.bumptech.glide:glide:5.0.5")
    ksp("com.github.bumptech.glide:compiler:5.0.5")


    // Dagger 2
    implementation("com.google.dagger:dagger:2.57.2")
    ksp("com.google.dagger:dagger-compiler:2.57.2")

    // RxJava3 & RxBinding
    implementation("com.jakewharton.rxbinding4:rxbinding:4.0.0")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
}

//Universal Developers Private Limited