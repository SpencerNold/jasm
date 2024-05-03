package me.spencernold.llvm.jar;

public class DirectoryElement implements JarElement {

    private final String name;

    public DirectoryElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
