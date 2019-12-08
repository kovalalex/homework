package dz_8;


import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс сериализатор/десериализатор
 */
public class JsonSerializator {

    /**
     * Метод десериализации объекта из файла
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Object deserialize(String fileName) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fileReader);
        String data = reader.readLine();
        data = delFirstLastChar(data);

        List<String> params = Arrays.asList(data.split(","));

        Map<String, String> pairs = new HashMap<>();
        for (String param : params) {
            String arrayPair[] = param.split(":");
            String key = delFirstLastChar(arrayPair[0]);
            String value = delFirstLastChar(arrayPair[1]);
            pairs.put(key, value);
        }

        Object object = null;
        String objType = pairs.get("OBJECT");
        try {
            Class cl = Class.forName(objType);
            object = cl.newInstance();
            Field[] fields = cl.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);

                String type = f.getType().getName();
                String strValue = pairs.get(f.getName());
                switch (type) {
                    case "int":
                        f.set(object, Integer.parseInt(strValue));
                        break;
                    case "float":
                        f.set(object, Float.parseFloat(strValue));
                        break;
                    default:
                        f.set(object, Class.forName(type).cast(strValue));

                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     * Обрезка первого и последнего символа в строке
     *
     * @param data
     * @return
     */
    public String delFirstLastChar(String data) {
        if (data.length() > 2) {
            data = data.substring(1);
            data = data.substring(0, data.length() - 1);
        }
        return data;
    }

    /**
     * Метод сериализации в файл
     *
     * @param fileName имя файла
     * @param obj      объект для сериализации
     * @throws IllegalAccessException
     */
    public void serialize(String fileName, Object obj) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Class cl = obj.getClass();
        String typeObj = cl.getTypeName();

        List<Field> fields = Arrays.asList(cl.getDeclaredFields());
        sb.append("{").append(makePair("OBJECT", typeObj)).append(",");


        Iterator<Field> it = fields.iterator();
        while (it.hasNext()) {
            Field field = it.next();
            field.setAccessible(true);
            String name = field.getName();
            String value = String.valueOf(field.get(obj));
            sb.append(makePair(name, value));
            if (it.hasNext()) sb.append(",");
            else sb.append("}");
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(sb.toString());
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Строка с key и value вида: "key":"value"
     *
     * @param key   String
     * @param value String
     * @return String
     */
    public String makePair(String key, String value) {
        return "\"" + key + "\":\"" + value + "\"";
    }

}
