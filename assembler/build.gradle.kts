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
	mainClass.set("me.spencernold.assembler.Main")
}
