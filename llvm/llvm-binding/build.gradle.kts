import org.gradle.internal.jvm.Jvm
import java.util.Scanner

plugins {
    `cpp-library`
}

library {
    var includes = exec("llvm-config --includedir")
    binaries.configureEach {
        val task = compileTask.get()
        val sep = File.separator
        val jniIncludes = "${Jvm.current().javaHome}${sep}include"
        task.includes.from(jniIncludes, includes)
        val os = targetPlatform.targetMachine.operatingSystemFamily
        if (os.isMacOs)
            task.includes.from("${jniIncludes}${sep}darwin")
        else if (os.isLinux)
            task.includes.from("${jniIncludes}${sep}linux")
        else if (os.isWindows)
            task.includes.from("${jniIncludes}${sep}win32")
        val srcTree = fileTree("src${sep}main${sep}cpp")
        srcTree.include("**/*.cpp", "**/*.hpp")
        task.source.from(srcTree)
    }
}

tasks.withType<LinkSharedLibrary>().configureEach {
    val libdir = exec("llvm-config --libdir")
    val libs = exec("llvm-config --libs")
    linkerArgs.addAll("-L${libdir}", libs)
}

private fun exec(cmd: String): String {
    val process = Runtime.getRuntime().exec(cmd)
    val lines = mutableListOf<String>()
    val scanner = Scanner(process.inputStream)
    while (scanner.hasNextLine())
        lines.add(scanner.nextLine())
    return lines.joinToString("\n") { it }
}