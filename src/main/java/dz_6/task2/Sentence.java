package dz_6.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс - предложение
 * @author Александр Коваленко
 */
public class Sentence {
    Random random = new Random();
    /*
    Список слов
     */
    List<Word> words = new ArrayList<>();

    /**
     * Вероятность наличия слова в предложении
     * от 1 до 10^9
     */
    int probability;

    public Sentence(int probability, String[] vocabulary) {
        Float realProb = 1F/probability;
        realProb = realProb * 1e+9F;
        this.probability = Math.round(realProb);

        int n2 = random.nextInt(15)+1; //кол-во слов
        for (int i = 0; i < n2 ; i++) {
            words.add(new Word());
        }

        boolean needVocabulary = random.nextInt(1000000000)>this.probability;
        if (needVocabulary)
            words.set(0,new Word(vocabulary[random.nextInt(vocabulary.length)]));
        words.get(0).setCapital();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            builder.append(words.get(i));
            if (i==words.size()-1)
                break;
            if (random.nextInt(100) < 70)
                   builder.append(" ");
            else
                    builder.append(", ");

        }
        builder.append(".");
        return builder.toString();
    }
}
