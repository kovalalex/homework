package dz_6.task1;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Задание 1. Написать программу, читающую текстовый файл. Программа должна составлять отсортированный по алфавиту список слов,
 * найденных в файле и сохранять его в файл-результат. Найденные слова не должны повторяться, регистр не должен учитываться.
 * Одно слово в разных падежах – это разные слова.
 * @author Александр Коваленко
 */
public class Main {
    public static void main(String[] args) {
        /**
         * set содержит слова, автоматически упорядочивая их в алфавитном порядке
         */
        Set<String> set = new TreeSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("TextFileFor_dz_6.txt"))) {
            reader.lines().forEach((s -> {
                for (String s1 : s.split("[\\p{Punct}\\s]+")) {
                    set.add(s1.toLowerCase());
                }
            }));
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла:"+e.getMessage());
        }
        //set.forEach(System.out::println);
        saveToFile(set);

    }

    /**
     * Метод записи Set<String> в файл, построчно
     * @param data Set<String>
     */
    public static void saveToFile(Set<String> data){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("result_dz_6_t1.txt"))){
            data.forEach(s -> {
                try {
                    writer.write(s+"\n");

                } catch (IOException e){
                    System.out.println("Ошибка записи в файл!:"+e.getMessage());
                }
            });
        } catch (IOException e){
            System.out.println("Ошибка записи в файл!:"+e.getMessage());
        }
    }
}
