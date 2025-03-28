plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") // Maps API
    id("kotlin-kapt") // Room Delete?
}

android {
    namespace = "com.fbada.lgbthistoryapproom"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fbada.lgbthistoryapproom"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = "17"
        }
        buildFeatures {
            viewBinding = true
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }

            dependencies {
                implementation("androidx.core:core-ktx:1.9.0")
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
                implementation("androidx.activity:activity-compose:1.8.0")
                implementation(platform("androidx.compose:compose-bom:2023.03.00"))
                implementation("androidx.compose.ui:ui")
                implementation("androidx.compose.ui:ui-graphics")
                implementation("androidx.compose.ui:ui-tooling-preview")
                implementation("androidx.compose.material3:material3")
                implementation("androidx.compose.material:material-icons-extended:1.5.4")
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("com.google.android.material:material:1.11.0")
                implementation("androidx.constraintlayout:constraintlayout:2.1.4")
                testImplementation("junit:junit:4.13.2")
                androidTestImplementation("androidx.test.ext:junit:1.1.5")
                androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
                androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
                androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.2")
                androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
                androidTestImplementation("androidx.test:rules:1.5.0")
                debugImplementation("androidx.compose.ui:ui-tooling")
                debugImplementation("androidx.compose.ui:ui-test-manifest")
                // Navigation
                implementation("androidx.navigation:navigation-compose:2.7.5")
                // Room
                val room_version = "2.6.0"
                implementation("androidx.room:room-runtime:$room_version")
                kapt("androidx.room:room-compiler:$room_version")
                implementation("androidx.room:room-ktx:$room_version")
                testImplementation("androidx.room:room-testing:$room_version")
                implementation("androidx.room:room-paging:$room_version")
                androidTestImplementation("com.google.truth:truth:1.1.3")
                // Maps
                implementation("com.google.maps.android:maps-compose:4.3.0")
                implementation("com.google.android.gms:play-services-maps:18.2.0")
                implementation("com.google.android.gms:play-services-location:21.1.0")

            }
        }
    }
}