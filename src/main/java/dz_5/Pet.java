package dz_5;


import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;

import java.util.UUID;

/**
 *
 * Класс Pet
 * @author Коваленко Александр
 */
@Value
@AllArgsConstructor
public class Pet {
    public final UUID uuid;
    /**
     * Кличка
     */
    @With
    private final String name;
    /**
     * Вес
     */
    @With
    private double weight;
    /**
     * Хозяин
     */
    @With
    private Person owner;

    public Pet(String name, double weight, Person owner) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.weight = weight;
        this.owner = owner;
    }


    @Override
    public String toString() {
        return "Pet{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", owner=" + owner +
                '}';
    }



}
