package edu.upc.eetac.dsa.db;

import edu.upc.eetac.dsa.db.orm.Session;
import edu.upc.eetac.dsa.db.orm.SessionImpl;
import edu.upc.eetac.dsa.db.orm.model.User;

import java.sql.*;
import java.util.List;

import static edu.upc.eetac.dsa.db.DBUtils.getConnection;

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


        User u = new User("111","Bruno", "1234");
        Connection conn = getConnection();
        Session s = new SessionImpl(conn);
        s.save(u);

        User u2 = (User) s.get(User.class, "idUser", "12f");
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
