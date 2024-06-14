package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Enemy {
    private String enemyId;
    private String name;
    private int meat;
    private int speed;

    public Enemy() {
        this.enemyId = RandomUtils.getId();
    }

    private String description;

    public String getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(String enemyId) {
        this.enemyId = enemyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMeat() {
        return meat;
    }

    public void setMeat(int meat) {
        this.meat = meat;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enemy(String name, int meat, int speed, String description) {
        this.enemyId = RandomUtils.getId();
        this.name = name;
        this.meat = meat;
        this.speed = speed;
        this.description = description;
    }
}
