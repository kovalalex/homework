package dz_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("dz_9.SomeClass".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass starts work: " + name);
        if ("dz_9.SomeClass".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("E:\\java tools\\projects\\homework\\src\\main\\java\\dz_9\\SomeClass.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}