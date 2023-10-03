group = "dev.tcheng"
version = "0.0.1"

plugins {
    id("kotlin-conventions")
    id("detekt-conventions")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion: String by project

    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
}

application {
    mainClass.set("MainKt")
}
