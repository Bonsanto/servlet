package mypack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session {

    public static String getCart(HttpServletRequest request, Object parameter) {
        String response;
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            response = "{}";
            session.setAttribute("cart", new ShoppingCart());
        } else {
            response = cart.getCart();
        }

        return response;
    }

    public static String setCart(HttpServletRequest request, Object parameter) {
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) cart = new ShoppingCart();

        cart.setCart(parameter);
        session.setAttribute("cart", cart);
        session.setMaxInactiveInterval(10);
        return cart.getCart();
    }

    public static String restartCart(HttpServletRequest request, Object parameter) {
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        cart.setCart(null);
        session.setAttribute("cart", cart);
        session.setMaxInactiveInterval(10);
        return cart.getCart();
    }
}
