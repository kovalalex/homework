package dz_8;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Serializator  {
    private StringBuilder builder = new StringBuilder();

    Map includeFields = new HashMap();

    void serialize (Object object, String file) throws IllegalAccessException{

//        this.add('{');
//        this.string(rootName);
//        this.add(':');
//        this.jsonFields(target);
//        this.add('}');
//        return this.builder.toString();


        Field[] fields = object.getClass().getDeclaredFields();
        for (Field declaredField : fields) {
            System.out.print(
                    Modifier.toString(declaredField.getModifiers()) + " " +
                            declaredField.getType().getSimpleName() + " " +
                            declaredField.getName() + ": ");
            declaredField.setAccessible(true); // доступ к приватному полю
            System.out.println(declaredField.get(object));
        }
    }
}
