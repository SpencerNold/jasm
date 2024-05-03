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
    implementation("net.java.dev.jna:jna:5.14.0")
    implementation(project(":interpreter"))
}

application {
    mainClass.set("me.spencernold.llvm.Main")
    applicationDefaultJvmArgs = listOf("-Djna.library.path=" + System.getProperty("llvm.library.path"))
}
