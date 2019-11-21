package dz_6.task2;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    static Random random = new Random();

    public static void main(String[] args) {

        String[] words = {"first", "second"};
        getFiles("E:\\java tools\\projects\\homework\\", 5, 5, words, 2);

    }

    public static void getFiles(String path, int n, int size, String[] words, int probability) {

        for (int i = 0; i < n; i++) {
            TextFile txt = new TextFile(size, words, probability);
            txt.toFile(path + i + ".txt");
        }


    }

}
