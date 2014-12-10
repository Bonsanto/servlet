/**
 * Created by xBons_000 on 16-07-2014.
 */

const sendtype = ["0", "1", "2", "3"]; //0 for simple variables, 1 for arrays, 2 for Jsons, 3 for array of JSons.
const varTypes = ["int","float","double","string"]; //Enum, object, char missing.
const and = "&";

var isObject = function (value) {
  return value instanceof Object;
};

var isInteger = function (value) {
    return value % 1 === 0;
};

var isArray = function (value) {
    return value instanceof Array;
};

var isValid = function (typeVar, value) {
    var valid = false;

    for(var i = 0; i < varTypes.length; i++){
        if(typeVar === varTypes[i])
            valid = true;
    }
    if(valid && !(value instanceof Array) )
        switch(typeof value){
            case "number":
                valid = (isInteger(value) == (typeVar == "int"));
                break;
            case "string":
                valid = (typeof value == "string");
                break;
            default :
                valid = false;
                break;
        }
    else if(valid && value instanceof Array)
        switch(typeof value[0]){
            case "number":
                valid = (isInteger(value[0]) == (typeVar == "int"));
                break;
            case "string":
                valid = (typeof value[0] == "string");
                break;
            default :
                valid = false;
                break;
        }
    else
        valid = false;
    return valid;
};

var sameType = function (value) {
    var validation = true;
    var type = typeof value[0];

    for(var i = 1; i < value.length; i++)
        if(typeof value[i] != type){
            validation = false;
            return validation;
        }
    return validation;
};

function Structure() {
    this.obj = "obj=";
    this.methodName = "method=";
    this.type = "type=";
    this.typevar = "typevar=";
    this.value = "value=";
}

Var.prototype = new Structure();
function Var() {

    this.createVar = function (obj,method,typevar, value) {
        if(typeof obj === "string") {
            if(typeof method === "string") {
                if (!(value instanceof Array)) {
                    if (isValid(typevar, value)) {
                        this.type += "0";
                        this.typevar += typevar;
                        this.value += value;
                        this.obj += obj;
                        this.methodName += method;
                    } else alert("Type inserted doesn't match with data");
                } else alert("Arrays can't be set in a var");
            } else alert("Method isn't an object");
        } else alert("Obj isn't an object");
    };

    this.getVar = function () {
        return (this.type + and + this.typevar + and + this.value + and + this.obj + and + this.methodName);
    };
}

VarList.prototype = new Structure();
function VarList() {

    this.createVar = function (obj, method, typevar, value) {
        if(typeof obj === "string") {
            if (typeof method === "string") {
                if (isArray(value)) {
                    if (sameType(value)) {
                        if (isValid(typevar, value)) {
                            this.type += "1";
                            this.typevar += typevar;
                            this.value += value.toString();
                            this.obj += obj;
                            this.methodName += method;
                        } else alert("Types don't match with varList.");
                    } else alert("Variables in the array aren't the same type");
                } else alert("Your variables aren't an array.");
            } else alert("Method isn't an object");
        } else alert("Obj isn't an object");
    };

    this.getVar = function () {
        return (this.type + and + this.typevar + and + this.value + and + this.obj + and + this.methodName);
    };
}

Record.prototype = new Structure();
function Record() {
    this.name = "name=";


    this.createVar = function (obj, method, typevar, value, name) { //type, typevar, value and
        var status = true;

        //TODO:FIX WHEN THE VALUE IS AN ARRAY luis = {"name":"luis","lastname":"finol","age":2,"score":[20,10,20,10]};
        if(typeof obj === "string") {
            if (typeof method === "string") {
                if (typevar.length === value.length) {
                    if (value.length === name.length) {
                        for (var i = 0; i < name.length; i++) {
                            if (status = !isValid(typevar[i], value[i])) {
                                alert("Type of var does not match with var type");
                                break;
                            } else if (i === (name.length - 1)) {
                                this.type += "2";
                                this.typevar += typevar.toString();
                                this.value += value.toString();
                                this.name += name.toString();
                                this.obj += obj;
                                this.methodName += method;
                            }
                        }
                    } else alert("Length of names is different");
                } else alert("Length of variables and types different.");
            } else alert("Method isn't an object");
        } else alert("Obj isn't an object");
    };

    this.getVar = function () {
        return (this.type + and + this.typevar + and + this.name + and + this.value + and + this.obj + and + this.methodName);
    };
}

RecordList.prototype = new Structure();
function RecordList() {
    this.prototype = new Structure();
    this.name = "name=";
    this.number = "number=";

    this.createVar = function (obj, method, typevar, number, name, value) { //type, typevar, value and
        var status = true;

        if(typeof obj === "string") {
            if (typeof method === "string") {
                if (number * typevar.length === value.length) {
                    if (value.length === number * name.length) {
                        this.type += "3";
                        this.typevar += typevar.toString();
                        this.number += number;
                        this.value += value.toString();
                        this.name += name.toString();
                        this.obj += obj;
                        this.methodName += method;
                    }
                            /*
                            if (status = !isValid(typevar[i % number], value[i])) {
                                alert("Type of var does not match with var type");
                                break;
                            } else if (i === (value.length - 1)) {
                                this.type += "3";
                                this.typevar += typevar.toString();
                                this.number += number;
                                this.value += value.toString();
                                this.name += name.toString();
                                this.obj += obj;
                                this.methodName += method;
                            }
                            */
                     else alert("Length of names is different");
                } else alert("Length of variables and types different");
            } else alert("Method isn't an object");
        } else alert("Obj isn't an object");
    };

    this.getVar = function () {
        return (this.type + and + this.typevar + and + this.number + and + this.value + and + this.name + and + this.obj + and + this.methodName);
    };
}

