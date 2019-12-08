package dz_8;

/**
 * Класс Person
 *
 * @author Александр Коваленко
 */
public class Person {

    /**
     * Поле содержащее возраст
     */

    private int age;
    /**
     * Поле содержащее имя
     */
    private String name;
    /**
     * скрытое приватное поле
     */
    private final String hide = "скрытая информация";
    /**
     * Поле содержащее зарплату
     */
    public float salary;

    public Person() {
    }

    public Person(int age, String name, float salary) {
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHide() {
        return hide;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hide='" + hide + '\'' +
                '}';
    }
}
