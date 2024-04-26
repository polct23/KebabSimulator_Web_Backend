package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.util.List;

public interface UserList {
    public User addUser(String userName, String password);
    public User addUser(User user);
    public User getUser(String userName); //Deberiamos darle como parametro la contraseña también?
    public List<User> getUsers();
    public void deleteUser(String userName);
    public User updatePassword(User u, String newPassword);
    public boolean authenticateUser(String userName, String password);

    public int size();
}
