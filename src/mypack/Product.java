package mypack;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;

public class Product {
    private String sgbd = "postgresql";
    private String ip = "localhost";
    private String port = "5432";
    private String dbName = "productos";
    private String userName = "postgres";
    private String key = "masterkey";

    /*Get part of the products.*/
    public String get(HttpServletRequest request, Object argument) {
        DBConnection db = new DBConnection();
        String query = ((Var) argument).asString();
        String fields = "*";
        String conditions = null;
        JSON json = new JSON();

        db.connect(sgbd, ip, port, dbName, userName, key);


        if (query.equals("All")) {
            conditions = "";
        } else if (query.equals("available") || query.equals("AVAILABLE") || query.equals("Available")) {
            conditions = " where qu_product > 0 ORDER BY id_product";
        }
        try {
            json = db.getAsJSON("select" + fields + "from product" + conditions, dbName);
        } catch (Exception ex) {
            System.out.println("Query bad formed");
        }
        System.out.println(json.getJson());
        return json.getJson();
    }

    public String buy(HttpServletRequest request, Object argument) {
        ShoppingCart boughtItems = new ShoppingCart();
        JSON response = new JSON();
        RecordList rl = (RecordList) argument;
        ResultSet rs;
        boolean status = true;
        int number;

        response.addAttribute("bought", true);
        response.build();

        DBConnection db = new DBConnection();
        String field = "qu_product";


        db.connect(sgbd, ip, port, dbName, userName, key);
        for (int i = 0; i < rl.getLength(); i++) {
            try {
                String id = rl.getStruct(i).getStruct("id").asString();
                int itemQuantity = rl.getStruct(i).getStruct("quantity").asInteger();
                System.out.println("select " + field + " from product where id_product = " + id);
                rs = db.open("select " + field + " from product where id_product =" + id);
                rs.last();
                number = rs.getInt(1);
                if (number >= itemQuantity) {
                    number -= itemQuantity;
                    System.out.println("update product set qu_product = " + number + " where id_product = " + id);
                    db.exec("update product set qu_product = " + number + " where id_product = " + id);
                } else {
                    response = new JSON();
                    status = status && false;
                    response.addAttribute("bought", status);
                    response.build();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Session.setCart(request, null);
        System.out.println(response.getJson());
        return response.getJson();
    }

}
