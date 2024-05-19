package me.spencernold.llvm;

import me.spencernold.jasm.ClassReader;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.logger.Logger;
import me.spencernold.jasm.logger.SystemLogger;
import me.spencernold.jasm.options.OptionContext;
import me.spencernold.jasm.options.OptionParser;
import me.spencernold.jasm.options.impl.FileOptionType;
import me.spencernold.llvm.binding.LLVM;
import me.spencernold.llvm.binding.Module;
import me.spencernold.llvm.jar.ClassElement;
import me.spencernold.llvm.jar.JarElement;
import me.spencernold.llvm.jar.JarReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {

    private static final Logger LOGGER = SystemLogger.getInstance();

    public static void main(String[] args) throws IOException {
        // THE PLAN!
        // load a bunch of jars to "classpath" (not the real classpath, a fake one)
        // from those, load all classes into a map, and find manifest files, reading them
        // depending on the manifests, compile the classes differently
        // build only necessary classes, etc. (based on constants in the const pools)
        // generate llvm ir from the JClass instances

        ClassReader reader = new ClassReader(new File("/Users/spencernold/Test.class"));
        JClass jclass = reader.read();

        try (ClassObjectCompiler compiler = new ClassObjectCompiler(jclass)) {
            compiler.build();
        }

        /*
        OptionParser parser = new OptionParser(args);
        parser.register("jvm", FileOptionType.class);
        OptionContext context = parser.parse();
        String jvmPath = context.hasOption("jvm") ? context.getAsFile("jvm").getAbsolutePath() : System.getProperty("java.home");
        if (jvmPath == null) {
            LOGGER.error("No valid JVM found, please use the --jvm argument or add a JAVA_HOME variable");
            return;
        }
        File jvm = new File(jvmPath, "lib");
        if (!jvm.exists()) {
            LOGGER.error("The JVM present is not valid: %s", jvmPath);
            return;
        }
        File rt = new File(jvm, "rt.jar");
        if (!rt.exists()) {
            LOGGER.error("The JVM present is not valid: %s", jvmPath);
            return;
        }

        File[] files = context.getArgumentsAsFiles();
        JavaCompiler compiler = new JavaCompiler();
        JarReader runtimeJarReader = new JarReader(rt);

        for (JarElement element : runtimeJarReader)
            compiler.update(element);
        for (File file : files) {
            String name = file.getName();
            if (name.endsWith(".jar")) {
                JarReader reader =  new JarReader(file);
                for (JarElement element : reader)
                    compiler.update(element);
                reader.close();
            } else if (name.endsWith(".class")) {
                byte[] bytes = Files.readAllBytes(file.toPath());
                ClassElement element = new ClassElement(file.getName(), bytes);
                compiler.update(element);
            } else {
                LOGGER.warn("Unknown classpath element: %s SKIPPING!", file.getName());
            }
        }
        runtimeJarReader.close();
        // compiler.compile();
         */
    }
}
