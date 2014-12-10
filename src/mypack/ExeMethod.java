package mypack;

import java.lang.reflect.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xBons_000 on 07-08-2014.
 */
@SuppressWarnings("unchecked")
public class ExeMethod {
    @SuppressWarnings("rawtypes")
    public static String exe(HttpServletRequest request, String className, String methodName, Object parameter) {
        Class c;
        //Field f;
        Method m;
        Object o;
        String r = "{\"error\":\"Error executing the method\"}";

        try {
            c = Class.forName("mypack." + className);

            o = c.newInstance();
            //todo remover es parte del negocio...
            if (o instanceof Session || o instanceof Product || o instanceof ChatSession) {
                m = c.getMethod(methodName, HttpServletRequest.class, Object.class);
                r = (String) m.invoke(o, request, parameter);
            } else {
                m = c.getMethod(methodName, Object.class);
                r = (String) m.invoke(o, parameter);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Class has not been found. " + ex);
        } catch (NoSuchMethodException ex) {
            System.out.println("Method has not been found. " + ex);
        } catch (InstantiationException ex) {
            System.out.println("Class could not be instantiated. " + ex);
        } catch (IllegalAccessException ex) {
            System.out.println("Illegal Access Exception. " + ex);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return r;
    }
}
