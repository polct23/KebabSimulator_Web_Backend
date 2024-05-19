package edu.upc.dsa;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FactorySession {

    public static Session openSession() throws SQLException {
        Connection conn = getConnection();
        Session session = new SessionImpl(conn);
        return session;
    }



    public static Connection getConnection()  throws SQLException {
        String db = DBUtils.getDb();
        String host = DBUtils.getDbHost();
        String port = DBUtils.getDbPort();
        String user = DBUtils.getDbUser();
        String pass = DBUtils.getDbPasswd();


        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+
                    db+"?user="+user+"&password="+pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static Session openSession(String url, String user, String password) {
        return null;
    }
}
