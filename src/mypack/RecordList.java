package mypack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xBons_000 on 31-07-2014.
 */
public class RecordList {
    private List<Record> records = new ArrayList<Record>();

    public int getLength() {
        return records.size();
    }

    public void addRecord(String[] name, String[] type, String[] value, int number) {
        int fieldNumber = value.length / number;

        if (name.length == type.length && type.length == fieldNumber) {
            for (int i = 0; i < number; i++) {
                Record data = new Record();

                for (int j = 0; j < fieldNumber; j++) {
                    data.addField(name[j], type[j], value[fieldNumber * i + j]);
                }
                records.add(data);
            }
        } else {
            System.out.println("Number of elements does not match.");
        }
    }

    public void addRecord(Record data) {
        records.add(data);
    }

    public Record getStruct(int index) {
        Record ret;
        try {
            ret = records.get(index);
        } catch (Exception e) {
            ret = null;
            System.out.println("Index doesn't match.");
        }
        return ret;
    }

}
