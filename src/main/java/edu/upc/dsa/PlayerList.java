package edu.upc.dsa;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;

import java.util.List;
import java.util.HashMap;

public interface PlayerList {
    public Player addPlayer(String idPlayer);
    public Player addPlayer(Player player);
    public Player getPlayer(String idPlayer);
    public List<Player> getPlayers();
    public void deletePlayer(String idPlayer);

    int size();
}

