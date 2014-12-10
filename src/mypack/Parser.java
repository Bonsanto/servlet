package mypack;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xBons_000 on 08-08-2014.
 */
public class Parser {

    //TODO: ADD validator.  
    public static String parse(HttpServletRequest request) throws NullPointerException {
        String response = "{\"error\":\"request couldn't be processed\"}";
        String object;
        String method;
        int type;


        object = request.getParameter("obj");
        method = request.getParameter("method");
        type = Integer.parseInt(request.getParameter("type"));

        if (type == 0) {
            Var variable = new Var();
            String value;
            String typevar;

            typevar = request.getParameter("typevar");
            value = request.getParameter("value");

            variable.createVar(typevar, value);
            response = ExeMethod.exe(request, object, method, variable);
        } else if (type == 1) {
            VarList variable = new VarList();
            String[] value;
            String typevar;

            typevar = request.getParameter("typevar");
            value = request.getParameter("value").split(",");

            variable.addVar(typevar, value);
            response = ExeMethod.exe(request, object, method, variable);
        } else if (type == 2) {
            Record variable = new Record();
            String[] typevar;
            String[] field;
            String[] value;

            typevar = request.getParameter("typevar").split(",");
            field = request.getParameter("name").split(",");
            value = request.getParameter("value").split(",");

            variable.addField(field, typevar, value);
            response = ExeMethod.exe(request, object, method, variable);
        } else if (type == 3) {
            RecordList variable = new RecordList();
            String[] typevar;
            String[] field;
            String[] value;
            int number;

            typevar = request.getParameter("typevar").split(",");
            field = request.getParameter("name").split(",");
            value = request.getParameter("value").split(",");
            number = Integer.parseInt(request.getParameter("number"));

            variable.addRecord(field, typevar, value, number);
            response = ExeMethod.exe(request, object, method, variable);
        }
        return response;
    }
}
