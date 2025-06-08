plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    // Firebase Google Services
    id("com.google.gms.google-services")
}

android {
    namespace = "com.rocketquackit.meinebuchhaltung"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rocketquackit.meinebuchhaltung"
        minSdk = 30
        targetSdk = 35
        versionCode = 4
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    signingConfigs {
        create("release") {
            val storeFilePath = findProperty("KEYSTORE_FILE") as? String
            val storePassword = findProperty("KEYSTORE_PASSWORD") as? String
            val keyAlias = findProperty("KEY_ALIAS") as? String
            val keyPassword = findProperty("KEY_PASSWORD") as? String

            if (storeFilePath != null && storePassword != null && keyAlias != null && keyPassword != null) {
                storeFile = file(storeFilePath)
                this.storePassword = storePassword
                this.keyAlias = keyAlias
                this.keyPassword = keyPassword
            } else {
                println("WARNUNG: Keine Signatur. Release-Build wird ohne Signatur konfiguriert.")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Material 2 und Material 3
    implementation(libs.material)
    implementation(libs.material3)

    // Fastscoll
    implementation(libs.fastscroll)

    // Room Libraries
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.firebase.auth.ktx)
    ksp(libs.androidx.room.compiler)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Firebase BoM
    implementation(platform(libs.firebase.bom))
    // Firebase Auth
    implementation(libs.google.firebase.auth.ktx)
}

// KSP Schema Export für Room
ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

apply(plugin = "com.google.gms.google-services")