plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // AÑADE ESTO para aplicar el plugin
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.p_final_componentes"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.p_final_componentes"
        minSdk = 35
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        // Se recomienda Java 1.8 o 11
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    // --- Configuración de Compose ---
    buildFeatures {
        // Habilita el soporte para Compose
        compose = true
    }
    composeOptions {
        // DEBES definir la versión de la extensión del compilador Kotlin
        kotlinCompilerExtensionVersion = "1.5.1" // Usa la versión compatible con tu versión de Kotlin
    }
    // ---------------------------------
}

dependencies {
    // --- Dependencias de Android Estándar ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    // ----------------------------------------


    // --- Dependencias de Jetpack Compose ---
    // Importa el BOM (Bill of Materials) para gestionar las versiones de Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00") // Usa una versión estable más reciente
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Necesario para integrar Compose en Activity (como con ComposeView.setContent)
    implementation("androidx.activity:activity-compose")

    // Componentes base de Compose (UI, Foundation, Material 3)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")

    // Herramientas de desarrollo
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Pruebas de Compose
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // ----------------------------------------


    // --- Dependencias de Pruebas Estándar ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // ----------------------------------------
}