package mypack;

/**
 * Created by xBons_000 on 06-08-2014.
 */
public class Item {
    public String name;
    public String description;
    public double price;
    public int id;
    public int quantity;

    public Item(String name, String description, double price, int id, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
    }

    public void add(int quantity) {
        this.quantity += quantity;
    }

    public void substract(int quanity) {
        this.quantity -= quantity;
    }
}
