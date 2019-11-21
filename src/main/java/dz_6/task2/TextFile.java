package dz_6.task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс текстовый файл
 */
public class TextFile {
    /**
     * Список параграфов
     */
    ArrayList<Paragraph> paragraphs = new ArrayList<>();

    public TextFile(int size, String[] words, int probability) {
        for (int i = 0; i < size; i++) {
            paragraphs.add(new Paragraph(probability,words));
        }

    }

    /**
     * Метод записи в файл
     * @param pathAndFilename
     */
    public void toFile(String pathAndFilename){
        try (FileWriter writer = new FileWriter(pathAndFilename);
             BufferedWriter bw = new BufferedWriter(writer)) {

            for (Paragraph x: paragraphs) {
                ArrayList<Sentence> sentences = x.getSentences();
                for (Sentence s: sentences) {
                    bw.write(s.toString());
                }
                bw.write("\n\r");
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
