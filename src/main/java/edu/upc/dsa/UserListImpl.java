package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class UserListImpl implements UserList {
    private static UserListImpl instance;
    protected List<User> users;
    final static Logger logger = Logger.getLogger(UserListImpl.class);

    private UserListImpl() {this.users = new LinkedList<>();}

    public static UserListImpl getInstance() {
        if (instance == null) instance = new UserListImpl();
        return instance;
    }
    public int size() {
        int ret = this.users.size();

        logger.info("size " + ret);
        return ret;
    }

    public User addUser(User user) {
        logger.info("addUser " + user);
        this.users.add(user);
        logger.info("new user added");
        return user;
    }
    public User addUser(String userName, String password) {return this.addUser(new User(userName, password));}
    public User getUser(String id) {
        logger.info("getUser(" + id + ")");

        for(User user : this.users) {
            if(user.getIdUser().equals(id)) {
                logger.info("getUser(" + id + "): " + user);
                return user;
            }
        }
        logger.info("not found " + id);
        return null;
    }
    public List<User> getUsers() {return this.users;}

    @Override
    public void deleteUser(String id) {
        User u = this.getUser(id);
        if(u != null) {
            logger.warn("not found " + u);
        }
        else logger.info(u + "deleted ");
        this.users.remove(u);
    }

    @Override
    public User updatePassword(User user, String newPassword){
        if(user != null) {
            logger.info(user + "received");

            for(User us : this.users) {
                if(us.getIdUser().equals(user.getIdUser())) {
                    us.setPassword(newPassword);
                }
                logger.info(user + "updated");
            }
        }
        else logger.warn("not found" + user);
        return user;
    }
}
