package edu.upc.dsa;
import edu.upc.dsa.ExceptionMapper.UserNotFoundException;
import edu.upc.dsa.ExceptionMapper.WrongCredentialsException;
import edu.upc.dsa.models.Ability;
import edu.upc.dsa.models.Player;

import java.util.List;

public interface PlayerList {
    public void buyAbilites(String userName, String idAbility) throws UserNotFoundException;
    List<Ability> getPlayersAbility(String userName) throws UserNotFoundException;
    public Player addPlayer(Player player);
    public Player getPlayer(String idPlayer);
    public List<Player> getPlayers();
    public void deletePlayer(String idPlayer);
    public void updateStatements(String userName, int lastMission, int currentPoints, int money) throws UserNotFoundException;
    boolean authenticateUser(String userName, String password) throws WrongCredentialsException;

    int size();

    Player updatePassword(String idPlayer, String newPassword);
}

