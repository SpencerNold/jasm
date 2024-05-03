package me.spencernold.llvm.jar;

public class ResourceElement implements JarElement {

    protected final String name;
    protected final byte[] body;

    public ResourceElement(String name, byte[] body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public byte[] getBody() {
        return body;
    }
}
