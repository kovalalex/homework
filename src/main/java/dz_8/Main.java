package dz_8;

public class Main {
    public static void main(String[] args) throws IllegalAccessException{
        Person person = new Person(25,"Василий", Person.Sex.MAN);
        Serializator serializator = new Serializator();
        serializator.serialize(person,"test.txt");
    }
}
