plugins {
	idea
	eclipse
	java
}
repositories {
    mavenCentral()
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(8)
}