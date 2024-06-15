package edu.upc.dsa.models;

public class TransferClass {
    private String userName;
    private String newPasword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewPasword() {
        return newPasword;
    }

    public void setNewPasword(String newPasword) {
        this.newPasword = newPasword;
    }

    public TransferClass() {
    }

    public TransferClass(String userName, String newPasword) {
        this.userName = userName;
        this.newPasword = newPasword;
    }
}
