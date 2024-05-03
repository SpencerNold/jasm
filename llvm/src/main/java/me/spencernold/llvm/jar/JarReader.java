package me.spencernold.llvm.jar;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarReader implements Closeable, Iterable<JarElement> {

    private final JarFile jar;
    private final Enumeration<JarEntry> entries;

    public JarReader(File file) throws IOException {
        this.jar = new JarFile(file);
        this.entries = jar.entries();
    }

    @Override
    public Iterator<JarElement> iterator() {
        return new Iterator<JarElement>() {

            private JarElement next;

            {
                next = update();
            }

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public JarElement next() {
                JarElement store = next;
                next = update();
                return store;
            }

            private JarElement update() {
                if (!entries.hasMoreElements())
                    return null;
                try {
                    return read(entries.nextElement());
                } catch (IOException e) {
                    return update();
                }
            }
        };
    }

    private JarElement read(JarEntry entry) throws IOException {
        if (entry.isDirectory())
            return new DirectoryElement(entry.getName());
        byte[] bytes = new byte[(int) entry.getSize()];
        InputStream input = jar.getInputStream(entry);
        int n = input.read(bytes, 0, bytes.length);
        if (n == -1)
            bytes = new byte[0];
        if (entry.getName().endsWith(".class"))
            return new ClassElement(entry.getName(), bytes);
        else if (entry.getName().endsWith(".MF"))
            return new ManifestElement(entry.getName(), bytes);
        return new ResourceElement(entry.getName(), bytes);
    }

    @Override
    public void close() throws IOException {
        jar.close();
    }
}
