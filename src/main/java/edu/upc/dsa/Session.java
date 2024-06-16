package edu.upc.dsa;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);                                           // Crud
    void close();
    Object get(Class theClass, String column, Object ID) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException;                                 // cRud

    // Nuevo método get con dos columnas
    Object get(Class theClass, String column1, Object value1, String column2, Object value2) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException;

    public void updateJugador(String columna, String user, Object value) throws SQLIntegrityConstraintViolationException;// crUd
    void delete(Object object, String columna);                                         // cruD
    List<Object> findAll(Class theClass);                               // cR

    public List<Object> findPlayerAbilities(String playerId, Class theClass)  throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException;

    List<Object> query(String query, Class theClass, HashMap params);
}
