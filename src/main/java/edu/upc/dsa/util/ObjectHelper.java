package edu.upc.dsa.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;

    }
    public static void setter(Object object, String property, Object value) {
        String propToUppercase = property.substring(0, 1).toUpperCase() + property.substring(1);
        String setterName = "set" + propToUppercase;

        try {
            Method m;
            if (value instanceof Integer) {
                m = object.getClass().getMethod(setterName, int.class);
            } else if (value instanceof Double) {
                m = object.getClass().getMethod(setterName, double.class);
            } else {
                m = object.getClass().getMethod(setterName, value.getClass());
            }

            m.invoke(object, value);
        } catch (NoSuchMethodException e) {
            System.out.println("No such method: " + setterName);
        } catch (IllegalAccessException e) {
            System.out.println("Illegal access: " + setterName);
        } catch (InvocationTargetException e) {
            System.out.println("Invocation target exception: " + setterName);
        }
    }

   /* public static void setter(Object object, String property, Object value) {
        // Method // invoke
        // property="name";
        // value ="toni"
        Employee e =
                e.setName("toni")


    }

    */


    public static Object getter(Object object, String property) throws NoSuchFieldException, IllegalAccessException {
        // Method // invoke
        // Method // invoke
        // Method // invoke
        String propToUppercase = property.substring(0, 1).toUpperCase() + property.substring(1);
        String getterName = "get" + propToUppercase;
        try {
            Method m = object.getClass().getDeclaredMethod(getterName);
            Object o = m.invoke(object);
            return o;

        }catch (NoSuchMethodException e){
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }




      //  Object valor = object.getClass().getDeclaredField(property).get(object);

       // return valor;
    }
}
