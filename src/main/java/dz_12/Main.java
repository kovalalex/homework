package dz_12;

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
    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws InterruptedException, Exception {
        //javaHeapOut();
        metaspaceOut();
    }

    /**
     * Переполнение metaspace
     * Основано на https://plumbr.io/outofmemoryerror/metaspace
     * при -XX:MaxMetaspaceSize=20m загружено ~ 10200 классов на моей машине
     */
    public static void metaspaceOut() throws Exception {
        for (int i = 0; i < LOOP_COUNT; i++) {
            Class c = cp.makeClass("ru.innopolis.dz_12" + i).toClass();
            System.out.println(i);
            Thread.sleep(10);

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
