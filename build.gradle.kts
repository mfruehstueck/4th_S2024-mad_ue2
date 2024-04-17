// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // commentet for exercise 5
    //id("com.android.application") version "8.2.2" apply false //original 8.3.0
    //id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    // exercise 5
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}