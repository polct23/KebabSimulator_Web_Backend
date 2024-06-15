package edu.upc.dsa.models;

public class PlayersAbility {
    String idPlayer;
    String idAbility;

    public PlayersAbility() {
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getIdAbility() {
        return idAbility;
    }

    public void setIdAbility(String idAbility) {
        this.idAbility = idAbility;
    }

    public PlayersAbility(String idPlayer, String idAbility) {
        this.idPlayer = idPlayer;
        this.idAbility = idAbility;
    }
}
