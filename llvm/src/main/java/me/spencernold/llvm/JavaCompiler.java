package me.spencernold.llvm;

import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.llvm.exceptions.PreprocessException;
import me.spencernold.llvm.jar.ClassElement;
import me.spencernold.llvm.jar.DirectoryElement;
import me.spencernold.llvm.jar.JarElement;
import me.spencernold.llvm.jar.ManifestElement;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

public class JavaCompiler implements Closeable {

    private String mainClass = null;
    private final HashMap<String, ClassElement> loadedClassPath = new HashMap<>();
    private final HashMap<String, ClassObjectCompiler> preprocessedClasses = new HashMap<>();

    public void update(JarElement element) {
        if (element instanceof DirectoryElement)
            return;
        if (element instanceof ManifestElement) {
            mainClass = ((ManifestElement) element).get("Main-Class");
        } else if (element instanceof ClassElement) {
            ClassElement e = (ClassElement) element;
            String name = e.getName();
            name = name.substring(0, name.length() - 6);
            loadedClassPath.put(name, e);
        }
    }

    public void compile() {
        mainClass = "Test";
        if (!loadedClassPath.containsKey(mainClass))
            throw new PreprocessException("missing main class: " + mainClass);
        process(mainClass);
        preprocessedClasses.values().forEach(ClassObjectCompiler::compile);
        preprocessedClasses.values().forEach(ClassObjectCompiler::build);
        // Link objects into executable
    }

    void process(String name) {
        JClass jclass = loadedClassPath.get(name).read();
        ClassObjectCompiler classObjectCompiler = new ClassObjectCompiler(this, jclass);
        classObjectCompiler.process();
        preprocessedClasses.put(name, classObjectCompiler);
    }

    @Override
    public void close() throws IOException {
        preprocessedClasses.values().forEach(ClassObjectCompiler::close);
    }
}
