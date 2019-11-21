package dz_5;

import java.util.Set;

public class Main {
    public static void main(String[] args) {


        Person person = new Person(35, "Олег", Person.Sex.MAN);
        Person person2 = new Person(20, "Илья", Person.Sex.MAN);
        Person person3 = new Person(20, "Наташа", Person.Sex.WOMAN);
        Pet pet = new Pet("Васька", 5, person);
        Pet pet2 = new Pet("Мурка", 3, person2);
        Pet pet3 = new Pet("Мурзик", 7, person3);
        Pet pet4 = new Pet("Мурзик", 2, person);
        PetCards petCards = new PetCards();

        try {
            petCards.addPet(pet);
            petCards.addPet(pet2);
            petCards.addPet(pet3);
            petCards.addPet(pet4);
        }
        catch (Exception e){
            System.out.println("Ошибка! "+e.getMessage());
        }

        System.out.println("Поиск по кличке \"Мурзик\":");
        Set<Pet> findResults = petCards.getPetByName("Мурзик");
        for (Pet elem : findResults) {
            System.out.println(elem.toString());
        }


        petCards.viewPetsSortedByName();
        System.out.println("Поменяем имя у " + pet4 + " на Бублика");
        try {
            petCards.changeName(pet4.uuid, "Бублик");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        petCards.viewPetsSortedByWeight();
        petCards.viewPetsSortedByOwner();


    }
}