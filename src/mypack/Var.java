package mypack;

/**
 * Created by xBons_000 on 31-07-2014.
 */
public class Var {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void createVar(String t, String v) {
        type = t;
        value = v;
    }

    public void createVar(Var val) {
        type = val.getType();
        value = val.getValue();
    }

    public String getStruct() {
        return value;
    }

    public byte asByte() {
        return Byte.parseByte(value);
    }

    public boolean asBoolean() {
        return Boolean.parseBoolean(value);
    }

    public char asChar() {
        return value.charAt(0);
    }

    public short asShort() {
        return Short.parseShort(value);
    }

    public int asInteger() {
        return Integer.parseInt(value);
    }

    public long asLong() {
        return Long.parseLong(value);
    }

    public float asFloat() {
        return Float.parseFloat(value);
    }

    public double asDouble() {
        return Double.parseDouble(value);
    }

    public String asString() {
        return value;
    }
}
