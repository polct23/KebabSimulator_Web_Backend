package edu.upc.dsa.models;

public class Ability {
    private String idAbility;
    private String abilityName;
    private String description;
    private int damage;
    private double price;

    //private int level - Por si queremos hacer nivelado de armas

    public Ability(){}

    public Ability(String idAbility, String abilityName, String description, int damage, double price){
        this.idAbility = idAbility;
        this.abilityName = abilityName;
        this.description = description;
        this.damage = damage;
        this.price = price;
    }

    public String getIdAbility() {
        return idAbility;
    }

    public void setIdAbility(String idAbility) {
        this.idAbility = idAbility;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
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
        return "Ability [idAbility="+ idAbility +", abilityName=" + abilityName + ", description=" + description + ", damage=" + damage + ", price=" + price + "]";
    }
}
