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
        int i = 0;
        for(User u : this.users) {
            if(u.getUserName().equals(user.getUserName())) {
                i = 1;
                break;
            }
        }
        if(i == 0){
            Session session = null;
            try {
                 session = FactorySession.openSession();
                 session.save(user);

            } catch (Exception e){
                  e.printStackTrace();
            }
            this.users.add(user);
            logger.info("new user added");

            return user;
        }
        else
            return null;
    }
    public User addUser(String userName, String password, String email) {
        return this.addUser(new User(userName, password, email));
    }
    public User getUser(String userName) {
        logger.info("getUser(" + userName + ")");
        Session session = null;

        try {
            session = FactorySession.openSession();
            User user = (User) session.get(User.class, "userName", userName);
            logger.info("getUser(" + userName + "): " + user);
            return user;

        } catch (Exception e){
            e.printStackTrace();
        }

        /*for(User user : this.users) {
            if(user.getUserName().equals(userName)) {
                logger.info("getUser(" + userName + "): " + user);
                return user;
            }
        }*/
        logger.info("not found " + userName);
        return null;
    }
    public List<User> getUsers() {return this.users;}

    @Override
    public void deleteUser(String id) {
        User u = this.getUser(id);
        if(u != null) {
            logger.warn("not found " + u);
        }
        else{
            logger.info(u + "deleted ");
            this.users.remove(u);
        }

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
    @Override
    public boolean authenticateUser(String userName, String password) {
        boolean ans = false;
        Session session = null;

        try {
            session = FactorySession.openSession();
            User user = (User) session.get(User.class, "userName", userName);
            if(user.getPassword().equals(password)) {
                ans = true;
            }
            logger.info("getUser(" + userName + "): " + user);
            return ans;

        } catch (Exception e){
            e.printStackTrace();
        }
        /*for(User u : this.users) {
            if(u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                ans = true;
                break;
            }
        }*/
        return ans;
    }
}
