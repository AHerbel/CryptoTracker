import groovy.lang.Closure
import org.gradle.api.tasks.testing.logging.TestLogEvent

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kover)
}

kover {
    reports {
        filters {
            excludes {
                androidGeneratedClasses()
                classes(
                    "*_Provide*",
                    "*_Factory",
                    "*_HiltModules*"
                )
                packages(
                    "dagger.hilt.internal.aggregatedroot.codegen",
                    "hilt_aggregated_deps"
                )
            }
        }
    }
}

android {
    namespace = "com.aherbel.cryptotracker"
    compileSdk = 34

    defaultConfig {
        testInstrumentationRunnerArguments += mapOf("clearPackageData" to "true")
        applicationId = "com.aherbel.cryptotracker"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.aherbel.cryptotracker.infra.HiltTestApplicationTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
            animationsDisabled = true

            all { test ->
                test.jvmArgs("--noverify")
                test.afterSuite(KotlinClosure<TestDescriptor, TestResult, Unit> { desc, result ->
                    if (!desc.isRootSuite()) return@KotlinClosure

                    println(
                        "Test Results: ${result.resultType} " +
                                "(${result.failedTestCount} failures, " +
                                "${result.skippedTestCount} skipped)"
                    )
                    println("Tests Count: ${result.testCount}")
                    println(result.calculateTestSuiteTime())
                })
                test.testLogging {
                    events(TestLogEvent.FAILED, TestLogEvent.SKIPPED)
                }
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
            resValue("string", "clear_text_config", "false")
        }
        debug {
            resValue("string", "clear_text_config", "true")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

class KotlinClosure<in P1, in P2, R>(val block: (P1, P2) -> R) : Closure<R>(null) {
    @Suppress("unused")
    fun doCall(p1: P1, p2: P2): R = block(p1, p2)
}

fun TestResult.calculateTestSuiteTime(): String {
    val maxTime = endTime - startTime
    val minutes = TimeUnit.MILLISECONDS.toMinutes(maxTime)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(maxTime) % 60
    val milliseconds = maxTime % 1000
    var message = "Total time for suite: "
    if (minutes > 0) {
        message += "${minutes}m "
    }
    if (seconds > 0) {
        message += "${seconds}s "
    }
    if (milliseconds > 0) {
        message += "${milliseconds}ms"
    }
    return message
}

fun TestDescriptor.isRootSuite(): Boolean = parent == null

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(platform(libs.retrofit.bom))
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logginginterceptor)
    implementation(libs.icu4j)

    testImplementation(libs.junit)
    testImplementation(libs.junitparams)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.strikt)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.okhttp.mockwebserver)
    androidTestImplementation(libs.okhttp.tls)

    androidTestUtil(libs.androidx.test.orchestrator)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}