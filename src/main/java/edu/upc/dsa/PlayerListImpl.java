package edu.upc.dsa;

import edu.upc.dsa.models.Player;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class PlayerListImpl implements PlayerList {
    private static PlayerListImpl instance;
    protected List<Player> players;
    final static Logger logger = Logger.getLogger(PlayerListImpl.class);
    private PlayerListImpl() {this.players = new ArrayList<>();}

    public static PlayerListImpl getInstance() {
        if(instance == null) instance = new PlayerListImpl();
        return instance;
    }
    public int size() {
        int ret = this.players.size();
        logger.info("size " + ret);
        return ret;
    }
    public Player addPlayer(Player player) {
        logger.info("addPlayer " + player);
        int i = 0;
        for(Player p : this.players) {
            if(p.getIdPlayer().equals(player.getIdPlayer())) {
                i=1;
                break;
            }
        }
        if(i==0) {
            this.players.add(player);
            logger.info("new player added");
            return player;
        }
        else
            return null;
    }
    public Player addPlayer(String idPlayer) {return this.addPlayer(new Player(idPlayer));}
    public Player getPlayer(String idPlayer) {
        logger.info("getPlayer " + idPlayer);
        for(Player p : this.players) {
            if(p.getIdPlayer().equals(idPlayer)) {
                logger.info("getPlayer(" + idPlayer + "): " + p);
                return p;
            }
        }
        logger.info("not found " + idPlayer);
        return null;
    }
    public List<Player> getPlayers() {return this.players;}

    public void deletePlayer(String idPlayer) {
        Player p = this.getPlayer(idPlayer);
        if(p != null) {
            logger.warn("not found " + p);
        }
        else{
            logger.info(p + "deleted");
            this.players.remove(p);
        }
    }
}