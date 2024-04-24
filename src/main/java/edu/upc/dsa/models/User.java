package edu.upc.dsa.models;
import edu.upc.dsa.utils.RandomUtils;

public class User {
    private String idUser;
    private String userName;
    private String password;

    public User(){ this.idUser = RandomUtils.getId(); }
    public User(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
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
        return "Track [id="+idUser+", title=" + userName + ", singer=" + password +"]";
    }
}
