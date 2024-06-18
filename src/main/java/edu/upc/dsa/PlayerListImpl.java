package edu.upc.dsa;

import edu.upc.dsa.ExceptionMapper.AbilityAlreadyPurchasedException;
import edu.upc.dsa.ExceptionMapper.UserNotFoundException;
import edu.upc.dsa.ExceptionMapper.WrongCredentialsException;
import edu.upc.dsa.ExceptionMapper.NotEnoughMoneyException;
import edu.upc.dsa.models.Ability;
import edu.upc.dsa.models.Player;


import java.util.ArrayList;
import java.util.List;

import edu.upc.dsa.models.PlayersAbility;
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

    @Override
    public void buyAbilites(String userName, String idAbility) throws UserNotFoundException, AbilityAlreadyPurchasedException, NotEnoughMoneyException {
        Session session = null;
        Player player;
        Ability ability;
        try {
            session = FactorySession.openSession();
            // Obtener el jugador
            player = (Player) session.get(Player.class, "userName", userName);
            if (player != null) {
                // Verificar si el jugador ya tiene la habilidad
                PlayersAbility pa = (PlayersAbility) session.get(PlayersAbility.class, "idPlayer", player.getIdPlayer(), "idAbility", idAbility);
                if (pa != null) {
                    // Si el jugador ya tiene esta habilidad, lanzar excepción
                    throw new AbilityAlreadyPurchasedException();
                }

                // Obtener la habilidad por su ID
                ability = (Ability) session.get(Ability.class, "idAbility", idAbility);
                if (ability == null) {
                    throw new IllegalArgumentException("Ability not found with ID: " + idAbility);
                }
                if(player.getMoney() < ability.getPrice()) {
                    throw new NotEnoughMoneyException();
                }
                else {
                    player.setMoney(player.getMoney() - ability.getPrice());
                    session.updateJugador("money", player.getUserName(), player.getMoney());
                    // Crear la relación entre el jugador y la habilidad
                    pa = new PlayersAbility(player.getIdPlayer(), idAbility);
                    session.save(pa);
                    logger.info(userName + " new ability bought correctly.");
                }

            } else {
                logger.warn("User not found: " + userName);
                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException | AbilityAlreadyPurchasedException e) {
            throw e; // Re-lanzar las excepciones específicas
        } catch (NotEnoughMoneyException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }







    @Override
    public List<Ability> getPlayersAbility(String userName) throws UserNotFoundException {
        Session session = null;
        Player player;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "userName", userName);
            if(player != null) {
                List<Ability> la = session.findPlayerAbilities(player.getIdPlayer(), Ability.class);
                for(Ability a: la){
                    logger.info(userName + " ability: " + a.getAbilityName());
                }
                return la;
            } else {
                logger.warn("not found " + userName);
                throw new UserNotFoundException();
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
        Player player = null; // Inicializa player como null

        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "userName", userName);
            if (player != null) {
                logger.info("getUser(" + userName + "): " + player);
            } else {
                logger.info("Player not found for username: " + userName);
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("Error fetching player: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return player; // Devuelve player, pueda ser null o no
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
    public void updateStatements(String userName, int currentMission, double money, int currentLevel) throws UserNotFoundException{
        Session session = null;
        Player player;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "userName", userName);
            if(player != null) {
                session.updateJugador("currentMission", userName , currentMission);
                logger.info("current mission updated correctly");
                session.updateJugador("currentLevel", userName , currentLevel);
                logger.info("current level updated correctly");
                session.updateJugador("money", userName , money);
                logger.info("Money updated correctly");
            } else {
                logger.warn("not found " + userName);
                throw new UserNotFoundException();

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public boolean authenticateUser(String userName, String password) throws WrongCredentialsException {
        boolean ans = false;
        Session session = null;

        try {
            session = FactorySession.openSession();
            Player player = (Player) session.get(Player.class, "userName", userName);

            if (player != null && player.getPassword().equals(password)) {
                ans = true;
            } else {
                throw new WrongCredentialsException();
            }

            logger.info("getPlayer(" + userName + "): " + player);
        } catch (Exception e) {
            // Captura cualquier excepción y la convierte en WrongCredentialsException
            throw new WrongCredentialsException();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return ans;
    }

    @Override
    public Player updatePassword(String userName, String newPassword) {
        Session session = null;
        Player player = null; // Inicializamos player como null
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "userName", userName);
            logger.info("getPlayer(" + userName + "): " + player);
            if (player != null) {
                // Actualizamos la contraseña del jugador
                player.setPassword(newPassword);
                session.updateJugador("password", userName, newPassword); // Actualizamos el objeto en la sesión
                logger.info("Password updated successfully for user: " + userName);
            } else {
                logger.warn("User not found: " + userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return player; // Retornamos el jugador actualizado o null si no se encontró
    }


}