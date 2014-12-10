/**
 * Created by xBons_000 on 14-08-2014.
 */
function Combo() {
    var combo = {};
    var elements = [];

    //combo needs to be instantiated.
    this.onChange = function (fu) {
            combo.onchange = fu;
    };

    /*No result set just array of items.*/
    this.fill = function (p) {
        if(p.length > 0) {
            for (var it in p) {
                if (p.hasOwnProperty(it)) {
                    elements.push(p[it]);
                }
            }
        } else window.alert("To fill the combo it must be an array");
    };

    this.build = function () {
        if(elements.length > 0){
            var option, node;
            combo = document.createElement("select");

            for(var e in elements){
                if(elements.hasOwnProperty(e)) {
                    option = document.createElement("option");
                    //TODO: Must have names, instead of description.
                    node = document.createTextNode(elements[e]["description"]);
                    option.pk = elements[e]["id"]; //USED TO IDENTIFY THE PRODUCT.
                    option.appendChild(node);
                    combo.appendChild(option);
                }
            }
        } else window.alert("Combo data was not loaded");

    };

    this.selectedItem = function () {
        return elements[combo.selectedIndex];
    };

    this.show = function (div) {
        div.appendChild(combo);
    };

    this.destroy = function () {
        combo = {};
        elements = [];
    };
}
