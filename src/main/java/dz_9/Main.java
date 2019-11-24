package dz_9;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //System.out.println("тест!");
        String pathToSomeClass = "E:\\java tools\\projects\\homework\\src\\main\\java\\dz_9\\SomeClass.java";
        List<String> methodLines = readFromConsole();
        methodLines.forEach(System.out::println);
        writeFile(pathToSomeClass, methodLines);
        compileClass(pathToSomeClass);
        try {
            ClassLoader cl = new MyClassLoader();
            Class<?> workerClass = cl.loadClass("dz_9.SomeClass");
            Worker worker = (Worker) workerClass.newInstance();
            worker.doWork();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        }


    }

    private static List<String> readFromConsole() {
        List<String> methodLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s;
            while (!((s = reader.readLine()).equals(""))) {
                methodLines.add(s);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении строк!" + e.getMessage());
        }
        return methodLines;
    }

    private static void writeFile(String path, List<String> method) {
        try (FileWriter writer = new FileWriter(path);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("package dz_9;\n");
            bw.write("public class SomeClass implements Worker{\n");
            bw.write("    @Override\n");
            bw.write("    public void doWork() {\n");

            for (String line : method) {
                bw.write(line + "\n");
            }

            bw.write("    }");
            bw.write("}");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл!" + e.getMessage());
        }
    }

    private static void compileClass(String path) {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        System.out.println(javaCompiler.run(null, null, null, path));
    }
}
