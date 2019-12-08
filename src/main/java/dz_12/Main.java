package dz_12;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Задание 12, утечки памяти в Java
 *
 * @author Коваленко Александр
 */
public class Main {
    private static final int LOOP_COUNT = 100_000_000;

    public static void main(String[] args) throws InterruptedException, Exception {
        //javaHeapOut();
        metaspaceOut();
    }

    /**
     * Переполнение metaspace
     * @author Коваленко Александр
     */
    public static void metaspaceOut() throws Exception {
        List<ClassA> list = new ArrayList<>();
        Handler handler = new Handler();

        for (int i = 0; i < LOOP_COUNT; i++) {
            String fictiousClassloaderJAR = "file:" + i;
            URL[] fictiousClassloaderURL = new URL[]{new URL(fictiousClassloaderJAR)};
            URLClassLoader newClassLoader = new URLClassLoader(fictiousClassloaderURL);
            ClassA p = (ClassA) Proxy.newProxyInstance(newClassLoader, new Class[]{ClassA.class}, handler);
            list.add(p);
            Thread.sleep(25);
            System.out.println(i);
        }
    }

    /**
     * метод переполнения Heap
     *
     * @throws InterruptedException
     */
    public static void javaHeapOut() throws InterruptedException {
        List<String> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < LOOP_COUNT; i++) {
            String str = "" + random.nextInt();
            list.add(str);
            if (i % 10 == 0) {
                Thread.sleep(1);
                list.remove(0);
            }
        }
        System.out.println(list.size());
    }
}
