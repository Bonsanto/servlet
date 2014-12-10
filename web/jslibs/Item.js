/**
 * Created by xBons_000 on 13-08-2014.
 */
function Item(){
    this.name = "";
    this.description = "";
    this.price = 0.0;
    this.id = null;
    this.quantity = 0;

    // RECEIVES A PRODUCT WITH THE FORMAT.
    this.setFields = function (e) {
        //if it comes frm db.
        if(e.hasOwnProperty("na_product")) {
            this.name = e["na_product"];
            this.description = e["de_product"];
            this.price = e["pr_product"];
            this.id = e["id_product"];
            this.quantity = e["qu_product"];
        //If it comes as an item.
        } else if(e.hasOwnProperty("name")){
            this.name = e.name;
            this.description = e.description;
            this.price = e.price;
            this.id = e.id;
            this.quantity = e.quantity;
        } else {
            console.log("Format not valid");
        }
    };

    this.add = function (q) {
        this.quantity += q;
    };

    this.substract = function (q) {
        this.quantity -= q;
    };
}