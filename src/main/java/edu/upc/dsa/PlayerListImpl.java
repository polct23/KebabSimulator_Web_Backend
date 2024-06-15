package edu.upc.dsa;

import edu.upc.dsa.ExceptionMapper.WrongCredentialsException;
import edu.upc.dsa.models.Ability;
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

        logger.info("addUser " + player);
        int i = 0;
        for(Player p : this.players) {
            if(p.getUserName().equals(p.getUserName())) {
                i = 1;
                break;
            }
        }
        if(i == 0){
            Session session = null;
            try {
                session = FactorySession.openSession();
                session.save(player);

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            this.players.add(player);
            logger.info("new user added");

            return player;
        }
        else
            return null;
    }


    public Player getPlayer(String userName) {
        logger.info("getUser(" + userName + ")");
        Session session = null;

        try {
            session = FactorySession.openSession();
            Player player = (Player) session.get(Player.class, "userName", userName);
            logger.info("getUser(" + userName + "): " + player);
            return player;

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        logger.info("not found " + userName);
        return null;
    }
    public List<Player> getPlayers() {
        Session session = null;
        List<Player> players = null;
        try {
            session = FactorySession.openSession();
            players = session.findAll(Player.class);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return players;
    }

    public void deletePlayer(String idPlayer) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Player player = (Player) session.get(Player.class, "idPlayer", idPlayer);
            if(player != null) {
                session.delete(player, "idPlayer");
                logger.info(player + " deleted ");
            } else {
                logger.warn("not found " + idPlayer);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateStatements(int lastMission, int money, int currentLevel) {

    }

    public boolean authenticateUser(String userName, String password) throws WrongCredentialsException {
        boolean ans = false;
        Session session = null;

        try {
            session = FactorySession.openSession();
            Player player = (Player) session.get(Player.class, "userName", userName);
            if(player.getPassword().equals(password)) {
                ans = true;
            }
            else{
                throw new WrongCredentialsException();
            }
            logger.info("getPlayer(" + userName + "): " + player);
            return ans;

        } catch (Exception e){
            e.printStackTrace();
        }

        return ans;
    }
    @Override
    public Player updatePassword(String userName, String newPassword){
        Session session = null;
        Player player;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "userName", userName);
            if(player != null) {
                session.updateJugador("password", userName , newPassword);
                logger.info("password updated correctly");
                return player;
            } else {
                logger.warn("not found " + userName);
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public boolean buyAbility(String userName, String idAbility) {
        boolean ans = false;
        Session session = null;
        try {
            session = FactorySession.openSession();
            Player player = (Player) session.get(Player.class, "userName", userName);
            Ability ability = (Ability) session.get(Ability.class, "idAbility", idAbility);
            if (player != null && ability != null) {
                // Verificar si el jugador ya tiene la habilidad
                if (!player.getAbilities().containsKey(idAbility)) {
                    // Añadir la habilidad al jugador
                    player.addAbility(ability);
                    // Guardar los cambios en la base de datos
                    session.updateJugador("ability", userName , idAbility );
                    logger.info("Ability " + idAbility + " bought by player " + userName);
                    ans = true;
                } else {
                    logger.info("Player " + userName + " already has ability " + idAbility);
                }
            } else {
                logger.warn("Player or ability not found: " + userName + ", " + idAbility);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ans;
    }


}