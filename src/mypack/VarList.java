package mypack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xBons_000 on 31-07-2014.
 */
public class VarList {
    private List<Var> values = new ArrayList<Var>();

    public int getLength() {
        return values.size();
    }

    public void addVar(Var data) {
        values.add(data);
    }

    //TODO: ADD VALIDATION.
    public void addVar(String type, String val) {
        Var data = new Var();
        data.createVar(type, val);
        values.add(data);
    }

    public void addVar(String type, String[] val) {
        for (String value : val) {
            Var data = new Var();
            data.createVar(type, value);
            values.add(data);
        }
    }

    public Var getStruct(int index) {
        Var ret;
        try {
            ret = values.get(index);
        } catch (Exception e) {
            ret = null;
            System.out.println("Index doesn't match.");
        }
        return ret;
    }
}
