plugins {
    application
    java
}

repositories {
    mavenCentral()
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(8)
}

dependencies {
    implementation(project(":interpreter"))
    implementation("net.java.dev.jna:jna-platform:5.14.0")
}

application {
    mainClass.set("me.spencernold.llvm.Main")
    applicationDefaultJvmArgs = listOf("-Djna.library.path=" + System.getProperty("jna.library.path"))
}
