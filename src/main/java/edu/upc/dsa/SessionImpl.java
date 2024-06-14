package edu.upc.dsa;

import edu.upc.dsa.models.User;
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

        if(entity.getClass() == User.class){
            User u = new User();
            u.setUserName(((User) entity).getUserName());
            u.setPassword(((User) entity).getPassword());
            u.setEmail(((User) entity).getEmail());
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
/*
    public Object get(Class theClass, int ID) {

        String sql = QueryHelper.createQuerySELECT(theClass);

        Object o = theClass.newInstance();


        ResultSet res = null;

        ResultSetMetaData rsmd = res.getMetaData();

        int numColumns = rsmd.getColumnCount();
        int i=0;

        while (i<numColumns) {
            String key = rsmd.getColumnName(i);
            String value = res.getObject(i);

            ObjectHelper.setter(o, key, value);

        }


        return null;
    }
    */

    public void update(Object object) {

    }

    public void delete(Object entity) {
        String deleteQuery = QueryHelper.createQueryDELETE(entity.getClass(), "id");
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setObject(1, ObjectHelper.getter(entity, "id"));
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

    public List<Object> findAll(Class theClass, HashMap params) {
     /*   String theQuery = QueryHelper.createSelectFindAll(theClass, params);
        PreparedStatement pstm = null;
        pstm = conn.prepareStatement(theQuery);

        int i=1;
        for (Object value : params.values()) {
            pstm.setObject(i++, value );
        }
        //ResultSet rs = pstm.executeQuery();




        return result;
*/
     return null;
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
