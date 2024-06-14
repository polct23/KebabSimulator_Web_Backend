package edu.upc.dsa.models;

import java.util.HashMap;

public class Player{
    private String idPlayer;
    private int meat;
    private int money;
    private HashMap<String, Ability> abilityHashMap;
    //private HashMap<String, Mision> misionesHashMap;
    public Player() {
        this.abilityHashMap = new HashMap<>();
    }
    public Player(String idPlayer) {
        this.idPlayer = idPlayer;
        this.meat = 0;
        this.money = 0;
    }

    public int getMeat() {
        return meat;
    }

    public void setMeat(int meat) {
        this.meat = meat;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public HashMap<String, Ability> getAbilityHashMap() {
        return abilityHashMap;
    }

    public void setAbilityHashMap(HashMap<String, Ability> abilityHashMap) {
        this.abilityHashMap = abilityHashMap;
    }

    public void buyWeapon(Weapon weapon){
        this.weaponHashMap.put(weapon.getIdWeapon(), weapon);
    }
    public String toString(){return "Player [playerId="+idPlayer+", meat="+meat+", money="+money+"]";}
}
