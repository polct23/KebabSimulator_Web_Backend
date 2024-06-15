package edu.upc.dsa;
import edu.upc.dsa.ExceptionMapper.WrongCredentialsException;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;

import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.HashMap;

public interface PlayerList {
    public Player addPlayer(Player player);
    public Player getPlayer(String idPlayer);
    public List<Player> getPlayers();
    public void deletePlayer(String idPlayer);
    public void updateStatements(int lastMission, int currentPoints, int money);
    boolean authenticateUser(String userName, String password) throws WrongCredentialsException;
    Player updatePassword(Player p, String newPassword);

    int size();
}

