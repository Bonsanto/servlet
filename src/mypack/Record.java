package mypack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xBons_000 on 31-07-2014.
 */
public class Record {
    private Map<String, Var> key_value = new HashMap<String, Var>();
    private Map<String, Integer> key_index = new HashMap<String, Integer>();
    private Map<Integer, String> index_key = new HashMap<Integer, String>();
    private int currentIndex = 0;

    public int getNumberFields() {
        return currentIndex;
    }

    public Var getStruct(int index) throws NullPointerException {
        Var value = null;
        String key;

        if (index < currentIndex) {
            key = index_key.get(index);
            value = key_value.get(key);
        } else {
            System.out.println("Index does not exist");
        }
        return value;
    }

    public Var getStruct(String key) throws NullPointerException {
        Var value = null;

        if (key_value.containsKey(key)) {
            value = key_value.get(key);
        } else {
            System.out.println("Key does not exist");
        }
        return value;
    }

    public String getKey(int index) throws NullPointerException {
        String value = null;

        if (index < currentIndex) {
            value = index_key.get(index);
        } else {
            System.out.println("Key does not exist");
        }
        return value;
    }

    public int getIndex(String key) throws NullPointerException {
        Integer index = null;

        if (key_value.containsKey(key)) {
            index = key_index.get(key);
        } else {
            System.out.println("Index does not exist");

        }
        return index;
    }

    public void addField(String key, Var field) {
        if (!key_value.containsKey(key)) {
            key_value.put(key, field);
            key_index.put(key, currentIndex);
            index_key.put(currentIndex++, key);
        } else {
            System.out.println("Key already exist.");
        }
    }

    //TODO: En el object clss puedes colocarla por al execute method executemethod.
    public void addField(String key, String type, String value) {
        Var data = new Var();
        data.createVar(type, value);

        //If it doesn't have that key.
        if (!key_value.containsKey(key)) {
            key_value.put(key, data);
            key_index.put(key, currentIndex);
            index_key.put(currentIndex++, key);
        } else {
            System.out.println("Key already exist.");
        }
    }

    public void addField(String[] key, String[] type, String[] value) {

        if (key.length == type.length && type.length == value.length) {
            for (int i = 0; i < key.length; i++) {
                Var data = new Var();
                data.createVar(type[i], value[i]);

                if (!key_value.containsKey(key[i])) {
                    key_value.put(key[i], data);
                    key_index.put(key[i], currentIndex);
                    index_key.put(currentIndex++, key[i]);
                } else {
                    System.out.println("Key already exist.");
                    break;
                }
            }
        }
    }

    public void addField(String type, String value) {
        Var data = new Var();
        data.createVar(type, value);

        //If it doesn't have that index.
        if (!key_value.containsKey(String.valueOf(currentIndex))) {
            key_index.put(String.valueOf(currentIndex), currentIndex);
            index_key.put(currentIndex, String.valueOf(currentIndex));
            key_value.put(String.valueOf(currentIndex++), data);
        } else {
            System.out.println("Key already exist.");
        }
    }
}
