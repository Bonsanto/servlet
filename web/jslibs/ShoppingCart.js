/**
 * Created by xBons_000 on 13-08-2014.
 */
function ShoppingCart() {
    var cart = [];

    //Supossing that a list of JSONs comes.
    //We supose that the prev cart will be deleted.
    this.copyCart = function (proList) {

        if(proList.length > 0) {
            cart = [];
            for (var item in proList) {
                if (proList.hasOwnProperty(item)) {
                    var product = new Item();
                    product.setFields(proList[item]);
                    cart.push(product);
                } else console.log("Product List has not that item");
            }
        } else console.log("Product list is empty")
    };

   //product can be product or item (add will notice)
    this.addProduct = function (product) {
        var it = new Item();
        it.setFields(product);

        if(!this.isInside(it)){
            cart.push(it);
        } else {
        	this.update(it, true);
        }
        //	window.alert("The item is already inside, use update");
    };

    //inc = boolean, true for icnrease, false for decrease.
    //If you will update then it exist in the cart.
    this.update = function (product, mode) {
        
        if(this.isInside(product)) {
            for(var pro in cart){
                if (cart.hasOwnProperty(pro)) {
                    if(cart[pro].id == product.id){
                        if(mode) cart[pro].add(product.quantity);
                        else if(!mode) cart[pro].substract(product.quantity);
                        else console.log("inc wasn't boolean");
                    }
                }
            }
        } else window.alert("The item is not inside the cart, use add");
    };

    this.getItems = function () {
        if(cart.length > 0){
            return cart;
        } else console.log("The cart is empty");
        return null;
    };

    //Test if the product is inside
    this.isInside = function (p) {
        if(cart.length > 0) {
            for(var item in cart) {
                if (cart.hasOwnProperty(item)) {
                    if(cart[item].id == p.id){
                        return true;
                    }
                }
            }
        }
        return false;
    };
}