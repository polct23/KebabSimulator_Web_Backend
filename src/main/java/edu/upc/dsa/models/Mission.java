package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Mission {
    private String missionId;
    private int reward;
    private String description;

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
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
        this.missionId = RandomUtils.getId();
    }

    public Mission(int reward, String description) {
        this.missionId = RandomUtils.getId();
        this.reward = reward;
        this.description = description;
    }
}
