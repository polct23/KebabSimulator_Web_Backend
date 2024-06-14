package edu.upc.dsa;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);                                           // Crud
    void close();
    Object get(Class theClass, String column, Object ID) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException;                                 // cRud

    void update(Object object);                                         // crUd
    void delete(Object object, String columna);                                         // cruD
    List<Object> findAll(Class theClass);                               // cR
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
}
