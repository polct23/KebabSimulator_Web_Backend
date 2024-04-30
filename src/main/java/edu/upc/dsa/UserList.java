package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.util.List;

public interface UserList {
    User addUser(String userName, String password);
    User addUser(User user);
    User getUser(String userName); //Deberiamos darle como parametro la contraseña también?
    List<User> getUsers();
    void deleteUser(String userName);
    User updatePassword(User u, String newPassword);
    boolean authenticateUser(String userName, String password);

    int size();
}
