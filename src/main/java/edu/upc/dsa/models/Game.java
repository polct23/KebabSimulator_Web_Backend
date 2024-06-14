package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.HashMap;

public class Game {
    private String idGame;
    private String playerId;
    private int levels;


    public Game() {
        this.idGame = RandomUtils.getId();
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public Game(String playerId, int levels) {
        this.idGame = RandomUtils.getId();
        this.playerId = playerId;
        this.levels = levels;
    }
}
