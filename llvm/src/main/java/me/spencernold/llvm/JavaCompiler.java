package me.spencernold.llvm;

import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.constants.Constant;
import me.spencernold.jasm.intermediary.constants.ReferenceConstant;
import me.spencernold.jasm.intermediary.pools.ConstPool;
import me.spencernold.llvm.exceptions.PreprocessException;
import me.spencernold.llvm.jar.ClassElement;
import me.spencernold.llvm.jar.DirectoryElement;
import me.spencernold.llvm.jar.JarElement;
import me.spencernold.llvm.jar.ManifestElement;

import java.util.HashMap;

public class JavaCompiler {

    private String mainClass = null;
    private final HashMap<String, ClassElement> loadedClassPath = new HashMap<>();
    private final HashMap<String, JClass> classesToCompile = new HashMap<>();

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
        JClass mainClass = loadedClassPath.get(this.mainClass).read();
        update(mainClass);
        System.out.println(classesToCompile.containsKey(this.mainClass));
    }

    private void update(JClass jclass) {
        ConstPool constPool = jclass.getConstPool();
        for (Constant constant : constPool) {
            if (constant.isClass()) {
                String name = constPool.getStringFromReferenceConstant(constant.getConstPoolIndex());
                if (classesToCompile.containsKey(name))
                    continue;
                if (!loadedClassPath.containsKey(name))
                    throw new PreprocessException("can not find %s loaded in the class path", name);
                JClass clazz = null;
                try {
                    clazz = loadedClassPath.get(name).read();
                } catch (Exception e) {
                    throw new PreprocessException(e);
                }
                update(clazz);
            }
        }
    }
}
