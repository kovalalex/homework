package dz_9;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("SomeClass".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass starts work: " + name);
        if ("SomeClass".equals(name)) {
            try {
                try (FileWriter writer = new FileWriter(Paths.get("./SomeClass.java").toString());
                     BufferedWriter bw = new BufferedWriter(writer)) {
                    bw.write("package dz_9;");
                    bw.write("public class SomeClass implements{");
                    bw.write("    void doWork(){");

                    try(BufferedReader reader =new BufferedReader(new InputStreamReader(System.in))){
                        String s;
                        while (!((s = reader.readLine()).equals(""))){
                            bw.write(s);
                        }
                    }
                    catch (IOException e){

                    }

                    bw.write("}");
                    bw.write("}");

                } catch (IOException e) {
                    System.err.format("IOException: %s%n", e);
                }


                byte[] bytes = Files.readAllBytes(Paths.get("./KindMagic.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}