package edu.upc.dsa;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;

import java.util.List;

public interface CentralService {
    public User addUserAndPlayer(User user);
    public boolean deleteUserAndPlayer(String username, String password);
    public List<User> getUserList();
    public List<Player> getPlayerList();
}
