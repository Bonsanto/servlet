import "dart:convert";
import "dart:html";
import "xhr.dart";


void main(){
  String url = window.location.origin;
  XHR xhr = new XHR(url + "/ppi/Sppi");  
  String message = "type=1&typevar=string&value=Alberto,Bonsanto,Pocaterra";
  xhr
    ..send(message)
    ..callBackFunc = (){
      window.alert(xhr.response);
      Map a = JSON.decode(xhr.response);
      window.alert(a.toString());
    };
}

