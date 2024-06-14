package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.Iterator;

public class CentralServiceImpl implements CentralService {
    private static CentralServiceImpl instance;
    protected List<User> ul;
    protected List<Player> pl;
    final static Logger logger = Logger.getLogger(CentralServiceImpl.class);

    private CentralServiceImpl() {
        this.ul = new ArrayList<>();
        this.pl = new ArrayList<>();
    }
    public static CentralServiceImpl getInstance() {
        if (instance == null) {
            instance = new CentralServiceImpl();
        }
        return instance;
    }

    public User addUserAndPlayer(User user) {
        logger.info("addUserAndPlayer " + user);
        String userId = user.getIdUser();
        int i = 0;
        int y = 0;
        for(User u : this.ul) {
            if (u.getIdUser().equals(userId)) {
                i = 1;
                break;
            }
        }
        for(Player p : this.pl) {
            if (p.getIdPlayer().equals(userId)) {
                y = 1;
                break;
            }
        }
        if(i==0 && y==0) {
            this.ul.add(user);
            Player newPlayer = new Player(userId);
            this.pl.add(newPlayer);
            logger.info("addUserAndPlayer " + user + " " + newPlayer);
            return user;
        }
        else return null;
    }

    public User addUserAndPlayer(String userName, String password, String email) {
        logger.info("addUserAndPlayer " + userName + " " + password + " " + email);
        User newUser = new User(userName, password, email);
        Player newPlayer = new Player(newUser.getIdUser());
        this.pl.add(newPlayer);
        this.ul.add(newUser);
        return newUser;
    }
    public List<User> getUserList() {
        return this.ul;
    }
    public List<Player> getPlayerList() {
        return this.pl;
    }
    public boolean deleteUserAndPlayer(String username, String password) {
        logger.info("deleteUserAndPlayer " + username + " " + password);

        Iterator<User> userIterator = ul.iterator();
        Iterator<Player> playerIterator = pl.iterator();

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                userIterator.remove(); // Remove user from user list

                while (playerIterator.hasNext()) {
                    Player player = playerIterator.next();
                    if (player.getIdPlayer().equals(user.getIdUser())) {
                        playerIterator.remove(); // Remove corresponding player from player list
                        logger.info("User and player deleted: " + username);
                        return true; // Successfully deleted user and player
                    }
                }
            }
        }

        logger.info("User not found or incorrect password: " + username);
        return false; // User not found or password incorrect
    }
}
