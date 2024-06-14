package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;

public class User {
    private String idUser;
    private String userName;
    private String password;
    private String email;

    public User(){ this.idUser = RandomUtils.getId(); }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String userName, String password, String email) {
        this();
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id="+idUser+", userName=" + userName + ", password=" + password + ", email=" + email + "]";
    }
}
