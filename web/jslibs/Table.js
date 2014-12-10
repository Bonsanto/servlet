/**
 * Created by xBons_000 on 13-08-2014.
 */
function Table() {
    var table = {};
    var data = [];
    var buttons = [];
    var bclicks = [];
    var title = [];
    var c = title.length; // columns
    var r = data.length; // rows counting the fields name;

    this.destroy = function () {
        document.body.removeChild(table);
        table = {};
        data = [];
        title = [];
        c = 0;
        r = 0;
    };

    this.getCart = function () {
        var cart = new ShoppingCart();
        cart.copyCart(data);
        return cart;
    };

    //The t should be a item
    this.format = function (t) {
        for(var f in t) {
            if (t.hasOwnProperty(f)) {
                title.push(f);
            }
            if (f == "quantity") {
                title.push("del");
                break;
            }
        }
        c = title.length;
    };


    this.fill = function (cart) {
        // In case of the title wasn't initialized.
        if(!(title.length > 0))
            this.format(cart.getItems()[0]);
        if(cart instanceof ShoppingCart){
            data = cart.getItems();
        }
        r = data.length;
        for(var i = 0; i < r; i++){
            buttons.push(document.createElement("button"));
            bclicks.push( function(i){
                if(table != null) {
                    for(var j = 1; j < r + 1; j++){
                        if (table.rows[j].id == i.currentTarget.id){
                        	table.deleteRow(j);
                        	data.splice(j - 1, 1);
                        	r = data.length;
                        	var origin = window.location.origin;
                        	var xhr = new XHR();
                        	var sessionCart = new RecordList();
                        	var items = data;
                            var fields = ["name","description","price","id","quantity"];
                            var types = ["string","string","double","int","int"];
                            var values = [];
                        	
                            for(var e in items){
                                if(items.hasOwnProperty(e)){
                                    var val = items[e];
                                    for(var i = 0; i < fields.length; i++){
                                    	values.push(val[fields[i]]);
                                    }
                                }
                            }
                             
                            if(values.length > 0){
                            	sessionCart.createVar("Session","setCart",types, data.length, fields, values);
                            } else {
                            	sessionCart = new Var();
                            	sessionCart.createVar("Session","restartCart","string", "none");      	
                            }
                            xhr.setURL(origin + "/SPPI");
                            xhr.setCallBackFun(function(){
                            	var result = JSON.parse(xhr.getResponse());
                            });
                            xhr.send(sessionCart.getVar());
                        }
                    }
                }
            });
        }
    };

    this.build = function () {
        var field;
        if(title.length > 0){
            table = document.createElement("table");
            table.setAttribute("cellSpacing", "0 px");
            for(var i = 0; i < r + 1; i++){ // +1  for the title
                table.insertRow(i);
                table.rows[i].id = i;
                for(var j = 0; j < c; j++){ //+1 for the delete button
                    table.rows[i].insertCell(j);
                    table.rows[i].cells[j].style.border = "1px solid black";
                    table.rows[i].cells[j].style.textAlign = "center";
                    if(i == 0){
                        var name = title[j];
                        if(name == "id")field = "# Serial";
                        else if(name == "description")field = "Description";
                        else if(name == "price")field = "Price";
                        else if(name == "name") field = "Name";
                        else if(name == "quantity") field = "Number";
                        else if(name == "del") field = "Remove";
                        else {
                            field = "ERROR";
                            window.alert("Field does not exist");
                        }
                        table.rows[i].cells[j].innerHTML = field;
                    } else {
                        field = title[j];
                        if (j < (c - 1)) {
                            table.rows[i].cells[j].innerHTML = data[i - 1][field];
                        } else {
                            /*CAREFULL WITH THE -1, DONT' FORGET YOU HAVE THE NAME */
                            buttons[i - 1].innerHTML = "Remove";
                            buttons[i - 1].id = i;
                            buttons[i - 1].addEventListener("click", bclicks[i - 1]);
                            table.rows[i].cells[j].appendChild(buttons[i - 1]);
                        }
                    }
                }
            }
        } else window.alert("Table data isn't filled");
    };

    this.show = function () {
	    	table.style.margin = "auto";
	        document.body.appendChild(table);
    };
}