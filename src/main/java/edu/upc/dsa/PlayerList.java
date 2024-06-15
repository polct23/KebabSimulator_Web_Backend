package edu.upc.dsa;
import edu.upc.dsa.ExceptionMapper.WrongCredentialsException;
import edu.upc.dsa.models.Player;

import java.util.List;

public interface PlayerList {
    public Player addPlayer(Player player);
    public Player getPlayer(String idPlayer);
    public List<Player> getPlayers();
    public void deletePlayer(String idPlayer);
    public void updateStatements(int lastMission, int currentPoints, int money);
    boolean authenticateUser(String userName, String password) throws WrongCredentialsException;

    int size();

    Player updatePassword(String idPlayer, String newPassword);
}

