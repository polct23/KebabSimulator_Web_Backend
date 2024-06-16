package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Mission {

    private int reward;
    private String description;




    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Mission() {}

    public Mission( int reward, String description) {
        this.reward = reward;
        this.description = description;
    }
}
