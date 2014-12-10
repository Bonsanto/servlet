/**
 * Created by xBons_000 on 16-08-2014.
 */
function Button() {
    var button = {};
    var content;

    this.fill = function (text) {
        content = text;
    };

    this.build = function () {
        button = document.createElement("button");
        button.innerHTML = content;
    };

    //The button needs to be isntantiated.
    this.onClick = function (fun) {
        button.onclick = fun;
    };

    this.show = function (div) {
        div.appendChild(button);
    };
}