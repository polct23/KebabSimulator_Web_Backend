package edu.upc.dsa.models;

public class Weapon {
    private String idWeapon;
    private String weaponName;
    private String description;
    private int damage;
    private double price;

    //private int level - Por si queremos hacer nivelado de armas

    public Weapon(){};
    public Weapon(String idWeapon, String weaponName, String description, int damage, double price){
        this.idWeapon = idWeapon;
        this.weaponName = weaponName;
        this.description = description;
        this.damage = damage;
        this.price = price;
    }

    public String getIdWeapon() {
        return idWeapon;
    }

    public void setIdWeapon(String idWeapon) {
        this.idWeapon = idWeapon;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Weapon [idWeapon="+idWeapon+", weaponName=" + weaponName + ", description=" + description + ", damage=" + damage + ", price=" + price + "]";
    }
}
