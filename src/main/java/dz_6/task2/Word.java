package dz_6.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс Word
 * @author Александр Коваленко
 */
public class Word {
    /**
     *  ГСЧ
     */
    Random random = new Random();
    /**
     * Поле для хранения слова
     */
    private String word;
    /**
     * Статический лист для хранения букв английского алфавита
     */
    static List<Character> alphabet = new ArrayList<Character>();

    static {
        for (char i = 'a'; i <= 'z'; i++) {
            alphabet.add(i);
          //  System.out.println(i);
        }
    }

    public Word(String word) {
        this.word = word;
    }

    public Word() {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < random.nextInt(15)+1; i++) {
            builder.append(alphabet.get(random.nextInt(alphabet.size())));
        }
        word = builder.toString();
    }
    public Word(int length) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(alphabet.get(random.nextInt(alphabet.size())));
        }
        word = builder.toString();
    }

    public String getWord() {
        return word;
    }

    /**
     * Установка первой буквы слова заглавной
     */
    public void setCapital(){
        word = word.substring(0,1).toUpperCase()+word.substring(1);
    }

    @Override
    public String toString() {
        return word;
    }

    //    public String gen(int n2) {
//
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < n2; i++) {
//            builder.append(alphabet.get(random.nextInt(alphabet.size())));
//        }
//        return builder.toString();
//    }
}
