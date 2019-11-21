package dz_6.task2;

import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;

/**
 * Класс - абзац
 * @author Александр Коваленко
 */
public class Paragraph {
    Random random = new Random();
    /**
     * Список предложений
     */
    private ArrayList<Sentence> sentences = new ArrayList<>();

    public Paragraph(int probability, String[] vocabulary) {
        int n3 = random.nextInt(20)+1;
        for (int i = 0; i < n3; i++) {
            sentences.add(new Sentence(2,vocabulary));
        }
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }
}
