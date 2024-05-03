import org.gradle.internal.jvm.Jvm

plugins {
    `cpp-library`
}

library {
    binaries.configureEach {
        val includes = "${Jvm.current().javaHome}${File.separator}include"
        val task = compileTask.get()
        task.includes.from(includes)
        val os = targetPlatform.targetMachine.operatingSystemFamily
        if (os.isMacOs) {
            task.includes.from("${includes}${File.separator}darwin")
        } else if (os.isLinux) {
            task.includes.from("${includes}${File.separator}linux")
        } else if (os.isWindows) {
            task.includes.from("${includes}${File.separator}win32")
        }
        task.source.from("src/main/c/llvm.c")
    }
}