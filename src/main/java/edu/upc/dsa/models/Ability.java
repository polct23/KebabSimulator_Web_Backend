package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Ability {
    private String idAbility;
    private String abilityName;
    private String description;
    private int value;
    private double price;
    private String imageURL;

    //private int level - Por si queremos hacer nivelado de armas

    public Ability(){}

    public Ability(String abilityName, String description, int value, double price, String imageURL){
        this.idAbility = RandomUtils.getId();
        this.abilityName = abilityName;
        this.description = description;
        this.value = value;
        this.price = price;
        this.imageURL = imageURL;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getImageURL() {return imageURL;}

    public void setImageURL(String imageURL) {this.imageURL = imageURL;}

    @Override
    public String toString() {
        return "Ability [idAbility="+ idAbility +", abilityName=" + abilityName + ", description=" + description + ", value=" + value + ", price=" + price + ", imageURL=" + imageURL + "]";
    }
}
