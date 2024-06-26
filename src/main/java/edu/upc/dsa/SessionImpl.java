package edu.upc.dsa;


import edu.upc.dsa.models.Ability;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        if(entity.getClass() == Player.class){
            Player u = new Player();
            u.setUserName(((Player) entity).getUserName());
            u.setPassword(((Player) entity).getPassword());
            u.setEmail(((Player) entity).getEmail());
            entity = u;
        }
        // INSERT INTO Partida () ()
        String insertQuery = QueryHelper.createQueryINSERT(entity);
        // INSERT INTO User (ID, lastName, firstName, address, city) VALUES (0, ?, ?, ?,?)


        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 1;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();

        } catch (SQLException | NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() {

    }

    @Override
    public Object get(Class theClass, String column, Object entity) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        String selectQuery = QueryHelper.createQuerySELECT(theClass, column);
        PreparedStatement pstm = null;
        pstm = conn.prepareStatement(selectQuery);
        pstm.setObject(1, entity);
        ResultSet rs = pstm.executeQuery();

        Object o = theClass.newInstance();

        if (!rs.next()) {
            // No records found
            o = null;
        } else{
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();

            do {
                for (int i = 1; i <= numberOfColumns; i++) {
                    String columnName = rsmd.getColumnName(i);
                    ObjectHelper.setter(o, columnName, rs.getObject(i));
                }
            } while (rs.next());
        }

        return o;
    }
    @Override
    public Object get(Class theClass, String column1, Object value1, String column2, Object value2) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        String selectQuery = QueryHelper.createQuerySELECTWithTwoColumns(theClass, column1, column2);
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, value1);
            pstm.setObject(2, value2);
            rs = pstm.executeQuery();

            Object result = null;

            if (rs.next()) {
                result = theClass.newInstance();
                ResultSetMetaData rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    String columnName = rsmd.getColumnName(i);
                    ObjectHelper.setter(result, columnName, rs.getObject(i));
                }
            }

            return result;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        }
    }

    public void updateJugador(String columna, String user, Object value) throws SQLIntegrityConstraintViolationException {
        String updateQuery = QueryHelper.createQueryUPDATEPlayer(columna);

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(updateQuery);
            pstm.setObject(1, value);
            pstm.setObject(2, user);

            pstm.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            // Re-throws the exception to propagate it to the caller
            throw e;
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle SQLException appropriately
        } finally {
            // Close PreparedStatement in finally block
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Or handle SQLException appropriately
                }
            }
        }
    }


    public void delete(Object entity, String columna) {
        String deleteQuery = QueryHelper.createQueryDELETE(entity.getClass(), columna);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setObject(1, ObjectHelper.getter(entity, columna));
            pstm.executeQuery();
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<Object> findAll(Class theClass) {
        String query = QueryHelper.createQuerySELECTall(theClass);
        PreparedStatement pstm = null;
        ResultSet rs;
        List<Object> list = new LinkedList<>();
        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
            rs = pstm.getResultSet();

            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();

            while (rs.next()){
                Object o = theClass.newInstance();
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Object> findPlayerAbilities(String playerId, Class theClass) {
        String query = QueryHelper.createQueryForPlayerAbilities();
        PreparedStatement pstm = null;
        ResultSet rs;
        List<Object> list = new LinkedList<>();
        try {
            pstm = conn.prepareStatement(query);
            pstm.setString(1, playerId);
            rs = pstm.executeQuery();

            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();

            while (rs.next()) {
                Object o = theClass.newInstance();
                for (int j = 1; j <= numberOfColumns; j++) {
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                list.add(o);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
