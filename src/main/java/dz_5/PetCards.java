package dz_5;

import java.util.*;

/**
 * Класс картотека питомцев
 * @author Александр Коваленко
 */
public class PetCards {
    /**
     * Hashmap для хранения и быстрого доступа по UUID
     */
    private HashMap<UUID, Pet> map = new HashMap<UUID, Pet>();

    private HashMap<String, HashSet<UUID>> mapForQSearch = new HashMap<String, HashSet<UUID>>();

    /**
     * Метод добавления животного в базу
     * @param pet объект пет
     * @throws Exception если объект с таким UUID уже есть в базе
     */
    public void addPet(Pet pet) throws Exception {
        if (map.containsKey(pet.uuid)) {
            throw new Exception("Данное животное уже есть в базе!");
        }
        map.put(pet.uuid, pet);
        if (mapForQSearch.containsKey(pet.getName())) {
            mapForQSearch.get(pet.getName()).add(pet.uuid);
        } else {
            mapForQSearch.put(pet.getName(), new HashSet<UUID>(Collections.singletonList(pet.uuid)));
        }
    }

    /**
     * Получить ссылку на животное по UUID
     * @param uuid уникальный идентификатор животного
     * @return Pet
     */
    public Pet getPetByUUID(UUID uuid) {
        if (map.containsKey(uuid)) {
            return map.get(uuid);
        }
        return null;
    }

    /**
     * Получить ссылки на животное по кличке
     * @param name Имя
     * @return Set питомцев
     */
    public Set<Pet> getPetByName(String name) {
        Set<Pet> result = new HashSet<Pet>();
        if (mapForQSearch.containsKey(name)) {
            Set<UUID> petsUids = mapForQSearch.get(name);
            Iterator it = petsUids.iterator();
            while (it.hasNext()) {
                result.add(getPetByUUID((UUID) it.next()));
            }
            return result;
        }

        return null;
    }

    /**
     * Изменить имя питомца
     *
     * @param uuid id
     * @param name новое имя
     * @throws Exception Petы immutable, выбрасывается если не удается добавить нового Pet
     */
    public void changeName(UUID uuid, String name) throws Exception {
        if (map.containsKey(uuid)) {
            Pet oldPet = getPetByUUID(uuid);
            remove(uuid);
            Pet newPet = oldPet.withName(name);
            try {
                addPet(newPet);
            } catch (Exception e) {
                throw new Exception("Не удалось изменить имя питомца!");
            }


        }
    }

    /**
     * Удаление питомца из базы по id
     *
     * @param uuid id питомца
     */
    public void remove(UUID uuid) {
        if (map.containsKey(uuid)) {
            Set<UUID> set = mapForQSearch.get(getPetByUUID(uuid).getName());
            set.remove(uuid);
            map.remove(uuid);
        }
    }


    /**
     * Вывести всех питомцев отсортированных по имени
     */
    public void viewPetsSortedByName(){
        ArrayList<Pet> list = new ArrayList<Pet>();
        for (Map.Entry<UUID, Pet> entry: map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<Pet>() {
            public int compare(Pet o1, Pet o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("-------pets sorted by name--------");
        for (Pet pet: list)
            System.out.println(pet);
        System.out.println("----------------------------------");
    }
    /**
     * Вывести всех питомцев отсортированных по весу
     */
    public void viewPetsSortedByWeight(){
        ArrayList<Pet> list = new ArrayList<Pet>();
        for (Map.Entry<UUID, Pet> entry: map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<Pet>() {
            public int compare(Pet o1, Pet o2) {
                Double d1 = o1.getWeight();
                return d1.compareTo(o2.getWeight());
            }
        });

        System.out.println("-------pets sorted by weight--------");
        for (Pet pet: list)
            System.out.println(pet);
        System.out.println("-------------------------------------");
    }
    /**
     * Вывести всех питомцев отсортированных по хозяину
     */
    public void viewPetsSortedByOwner(){
        ArrayList<Pet> list = new ArrayList<Pet>();
        for (Map.Entry<UUID, Pet> entry: map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<Pet>() {
            public int compare(Pet o1, Pet o2) {
                return o1.getOwner().compareTo(o2.getOwner());
            }
        });

        System.out.println("-------pets sorted by owner--------");
        for (Pet pet: list)
            System.out.println(pet);
        System.out.println("-------------------------------------");
    }
}
