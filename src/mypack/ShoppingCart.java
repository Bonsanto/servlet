package mypack;

import java.util.ArrayList;

/**
 * Created by xBons_000 on 06-08-2014.
 */
public class ShoppingCart {
    private ArrayList<Item> cart = new ArrayList<Item>();

    public ShoppingCart(ArrayList<Item> cart) {
        this.cart = cart;
    }

    public ShoppingCart() {
        cart = new ArrayList<Item>();
    }

    //TODO: DO THIS.
    public String getCart() {
        int length = cart.size();
        JSON item;
        JSON[] c = new JSON[length];

        if (length > 0) {
            for (int i = 0; i < length; i++) {
                item = new JSON();
                item.addAttribute("name", cart.get(i).name);
                item.addAttribute("description", cart.get(i).description);
                item.addAttribute("price", cart.get(i).price);
                item.addAttribute("id", cart.get(i).id);
                item.addAttribute("quantity", cart.get(i).quantity);
                item.build();
                c[i] = item;
            }
        }
        item = new JSON();
        item.addAttribute("cart", c);
        item.build();
        return item.getJson();
    }

    //FOr a record.
    public Item recordToItem(Record r) {
        Item it;
        String na, de;
        double pr;
        int id, qu;

        na = r.getStruct("name").asString();
        de = r.getStruct("description").asString();
        pr = r.getStruct("price").asDouble();
        id = r.getStruct("id").asInteger();
        qu = r.getStruct("quantity").asInteger();
        it = new Item(na, de, pr, id, qu);

        return it;
    }


    public void setCart(Object c) {
        RecordList rl = (RecordList) c;
        cart = new ArrayList<Item>();
        Item it;

        if (c != null) {
            for (int i = 0; i < rl.getLength(); i++) {
                it = recordToItem(rl.getStruct(i));
                cart.add(it);
            }
        }
    }

    public void addItem(String itemName, String itemDescription, double price, int id, int quantity) {
        Item item = new Item(itemName, itemDescription, price, id, quantity);
        addItem(item);
    }

    public void addItem(Item item) {
        boolean inList = false;

        for (Item product : cart) {
            if (product.id == item.id) {
                inList = true;
                product.quantity = item.quantity;
                break;
            }
        }
        if (!inList) cart.add(item);
    }

    public void substract(Item item) { //Not used
        for (Item product : cart) {
            if (product.id == item.id && product.quantity > item.quantity) {
                product.quantity -= item.quantity; //CAREFULL WITHT HE MINUS.
                break;
            }
        }
    }

    public void substract(String itemName, String itemDescription, double price, int id, int quantity) {
        Item item = new Item(itemName, itemDescription, price, id, quantity);
        substract(item);
    }

    public void removeItem(Item item) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).id == item.id) {
                cart.remove(i);
                break;
            }
        }
    }

    public void removeItem(String itemName, String itemDescription, double price, int id, int quantity) {
        Item item = new Item(itemName, itemDescription, price, id, quantity);
        removeItem(item);
    }
}
