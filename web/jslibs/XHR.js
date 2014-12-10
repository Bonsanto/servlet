/**
 * Created by Alberto Bonsanto on 03/07/2014.
 */
const errors = {"100" : "100 - Continue",
    "101" : "101 - Switching Protocols",
    "102" : "102 - Processing",
    "200" : "200 - OK",
    "201" : "201 - Created",
    "202" : "202 - Accepted",
    "203" : "203 - Non-Authoritative Information",
    "204" : "204 - No Content",
    "205" : "205 - Reset Content",
    "206" : "206 - Partial Content",
    "207" : "207 - Multi-Status",
    "208" : "208 - Already Reported",
    "209" : "209 - IM Used",
    "300" : "300 - Multiple Choices",
    "301" : "301 - Moved permanently",
    "302" : "302 - Found",
    "303" : "303 - See Other",
    "304" : "304 - Not Modified",
    "305" : "305 - Use Proxy",
    "306" : "306 - Switch Proxy",
    "307" : "307 - Temporary Redirect",
    "308" : "308 - Permanent Redirect",
    "400" : "400 - Bad Request",
    "401" : "401 - Unauthorized",
    "402" : "402 - Payment Required",
    "403" : "403 - Forbidden",
    "404" : "404 - Not found",
    "405" : "405 - Method Not Allowed",
    "406" : "406 - Not Acceptable",
    "407" : "407 - Proxy Authentication Required",
    "408" : "408 - Request Timeout",
    "409" : "409 - Conflict",
    "410" : "410 - Gone",
    "411" : "411 - Length Required",
    "412" : "412 - Precondition Failed",
    "413" : "413 - Request Entity Too Large",
    "414" : "414 - Request-URI Too Long",
    "415" : "415 - Unsupported Media Type",
    "416" : "416 - Request Range Not Satisfiable",
    "417" : "417 - Expectation Failed",
    "418" : "418 - I'm a teapot",
    "419" : "419 - Authentication Timeout",
    "420" : "420 - Method Failure",
    "422" : "422 - Unprocessable Entity",
    "423" : "423 - Locked",
    "424" : "424 - Failed Dependency",
    "426" : "426 - Upgrade Required",
    "428" : "428 - Precondition Required",
    "429" : "429 - Too Many Requests",
    "431" : "431 - Request Header Fields Too Large",
    "440" : "440 - Login Timeout (Microsoft)",
    "444" : "444 - No Response (Nginx)",
    "449" : "449 - Retry with (Microsoft)",
    "450" : "450 - Blocked by Windows Parental Controls (Microsoft)",
    "451" : "451 - Unavailable For Legal Reasons (Internet draft)",
    "494" : "494 - Request Header Too Large (Nginx)",
    "495" : "495 - Cert Error (Nginx)",
    "496" : "496 - No Cert (Nginx)",
    "497" : "497 - HTTP to HTTPS (Nginx)",
    "498" : "498 - Token expired/invalid",
    "499" : "499 - Client Closed Request (Nginx)",
    "500" : "500 - Internal Server Error",
    "501" : "501 - Not Implemented",
    "502" : "502 - Bad Gateway",
    "503" : "503 - Service Unavailable",
    "504" : "504 - Gateway Timeout",
    "505" : "505 - HTTP VersionNot Supported",
    "506" : "506 - Variant Also Negotiates",
    "507" : "507 - Insufficient Storage",
    "508" : "508 - Loop Detected",
    "509" : "509 - Bandwidth Limit Exceeded",
    "510" : "510 - Not Extended",
    "511" : "511 - Network Authentication Required",
    "520" : "520 - Origin Error",
    "521" : "521 - Web server is down",
    "522" : "522 - Connection timed out",
    "523" : "523 - Proxy Declined Request",
    "524" : "524 - A timeout occurred",
    "598" : "598 - Network read timeout error",
    "599" : "599 -Network connect timeout error" };

XHR = function () {
    var extFun = new Function();
    var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    var mode = true;//true if async, false if sync.
    var url = "";
    var method = "post";

    this.setMode = function (m){
        mode = m;
    };

    this.setURL = function (u) {
        url = u;
    };

    this.setMethod = function (m) {
        method = m;
    };
    
    var callBackFunction = function(){ 
        try{
            if(xhr.readyState == 4) {
              if(xhr.status == 200){
                extFun();
              }
              else {
                  alert(errors[xhr.status]);
              }
            }
        } catch(e){
            alert("Error en el servidor");
        }
    };

    this.send = function (val) {
        xhr.onreadystatechange = callBackFunction;
        xhr.open(method, url, mode);
        xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        xhr.send(val);
    };

    this.setCallBackFun = function (f) {
        extFun = f;
    };

    this.getResponse = function () {
        return xhr.responseText;
    };
};