package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.HashMap;

public class Player{
    private String idPlayer;
    private String userName;
    private String password;
    private String email;
    private int currentLevel;
    private int currentMission;
    private double money;
    //private HashMap<String, Mision> misionesHashMap;


    public Player(String userName, String password, String email, int currentLevel, int currentMission, double money) {
        this.idPlayer = RandomUtils.getId();
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.currentLevel = currentLevel;
        this.currentMission = currentMission;
        this.money = money;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentMission() {
        return currentMission;
    }

    public void setCurrentMission(int currentMission) {
        this.currentMission = currentMission;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Player(String userName, String password, String email) {
        this.idPlayer = RandomUtils.getId();
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Player() {
        this.idPlayer = RandomUtils.getId();
    }
}
