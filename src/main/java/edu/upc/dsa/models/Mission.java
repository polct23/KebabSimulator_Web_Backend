package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Mission {
    private String idMission;
    private int reward;
    private String description;

    public String getIdMission() {
        return idMission;
    }

    public void setIdMission(String idMission) {
        this.idMission = idMission;
    }

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

    public Mission() {
        this.idMission = RandomUtils.getId();
    }

    public Mission(int reward, String description) {
        this.idMission = RandomUtils.getId();
        this.reward = reward;
        this.description = description;
    }
}
