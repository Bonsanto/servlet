/**
 * Created by xBons_000 on 16-08-2014.
 */
function NumberInput() {
    var number = {};
    var maxValue = 0;
    var minValue = 0;

    this.getNum = function () {
        return parseInt(number.value);
    };

    this.getMax = function () {
        return maxValue;
    };

    this.getMin = function () {
        return minValue;
    };

    this.setValue = function(val){
        number.value = val;
    };

    this.setMax = function (max) {
        number.max = max;
        maxValue = max;
    };

    //It wants a combo...
    this.setLimits = function (combo) {
        var item = combo.selectedItem();
        maxValue = item.quantity;
    };

    this.generateNumberInput = function (def) {
        number = document.createElement("input");
        number.setAttribute("type", "number");
        number.min = minValue;
        number.max = maxValue;
        number.value = 0;
        number.style.textAlign = "center";

        number.oninput = function () {
            number.value = (number.value) ? number.value : number.min;
            number.value = (number.value > maxValue) ? maxValue : number.value;
        };
    };

    this.showNumber = function (div) {
        try {
            div.appendChild(number);           
        }catch (ex){
            console.log(ex);
        }
    };
}