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
}

application {
    mainClass.set("me.spencernold.llvm.Main")
    applicationDefaultJvmArgs = listOf("-Djava.library.path=" + System.getProperty("jni.library.path"))
}
