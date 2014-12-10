package mypack;

/**
 * Created by xBons_000 on 12-07-2014.
 */
public class JSONVar extends JSON {
    private String name;

    public JSONVar(String name, JSON json) {
        super(json);
        this.setName(name);
    }

    public JSONVar(String name) {
        this.setName(name);
    }

    public void setName(String n) {
        name = "var " + n + " = ";
    }

    @Override
    public void build() {
        json = json.substring(0, json.length() - 2) + "}";
        json = (name == null) ? ("var json = " + json) : (name + json);
    }
}
