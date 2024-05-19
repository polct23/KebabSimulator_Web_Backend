package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static edu.upc.dsa.DBUtils.getConnection;

public class DBJDBC2 extends DBJDBC{


    public static void insert() throws SQLException{
        Connection connection = getConnection();

        // SQL INJECTION
        String theQuery = "INSERT INTO User (ID, lastName, firstName, address, city, password) VALUES (0, ?, ?, ?,?)";
        // log.debug

        PreparedStatement statement1  =  connection.prepareStatement(theQuery);
        statement1.setString(1, "McDonald's");
        statement1.setString(2, ";DROP TABLES;");
        statement1.setString(3, "PREPAREDLAddress");
        statement1.setString(4, "DJKASJK");

        // a = b / 0  - null.method();

        /// NULLPOINTER ??
        statement1.execute();
        /// NULLPOINTER ??


        connection.close();

    }



    public static void main(String[] args) throws Exception {
        //insert();
        //findAll();

        // ORM (Object Relation Mapping) --> DAO (Data Access Object)


        User u = new User("Lucas","1234","lucas@gmail.com" );
        Connection conn = getConnection();
        Session s = new SessionImpl(conn);
        //Session s = FactorySession.openSession();
        User u2 = (User) s.get(User.class, "idUser", "2222");
        System.out.println(u2.getUserName());

        List<User> lu = s.findAll(User.class);
        for (User user: lu) {
            System.out.println(user.getUserName());
        }


        /* u.userName("Juan");
        s.update(u); ====> "UPDATE xxx"

        s.save(new Object("Escudo")):; //"INSERT NTO Object
        s.save(new Mapa("Escudo")):;   // INSERT INTO Mapa

        */



    }

}
