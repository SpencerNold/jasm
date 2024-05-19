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

tasks.named("run") {
    dependsOn(":llvm:llvm-binding:build")
}

application {
    mainClass.set("me.spencernold.llvm.Main")

    val sep = File.separator
    val buildDir = project(":llvm:llvm-binding").layout.buildDirectory.asFile.get().absolutePath

    applicationDefaultJvmArgs = listOf("-Djava.library.path=${buildDir}${sep}lib${sep}main${sep}debug")
}
