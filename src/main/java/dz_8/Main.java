package dz_8;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person(25, "Василий", 80000F);
        JsonSerializator jsonSerializator = new JsonSerializator();
        jsonSerializator.serialize("test.txt", person);
        try {
            Person clone = (Person) jsonSerializator.deserialize("test.txt");
            System.out.println("Десериализованный объект:");
            System.out.println(clone);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
