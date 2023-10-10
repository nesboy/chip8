group = "dev.tcheng"
version = "0.0.1"

plugins {
    id("dev.tcheng.conventions-kotlin.kotlin") version "0.0.1"
    id("dev.tcheng.conventions-kotlin.detekt") version "0.0.1"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    val kotlinVersion: String by project

    compileOnly("dev.tcheng.conventions-kotlin:plugin:0.0.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
}

application {
    mainClass.set("MainKt")
}
