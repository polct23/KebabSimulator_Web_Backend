package edu.upc.dsa;

import edu.upc.dsa.models.Enemy;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EnemyListImpl implements EnemyList {
    private static EnemyListImpl instance;
    protected List<Enemy> enemies;
    final static Logger logger = Logger.getLogger(EnemyListImpl.class);

    private EnemyListImpl(){this.enemies = new ArrayList<>();}
    public static EnemyListImpl getInstance(){
        if(instance == null) instance = new EnemyListImpl();
        return instance;
    }

    public int size() {
        int ret = this.enemies.size();
        logger.info("size " + ret);
        return ret;
    }
    public Enemy addEnemy(Enemy enemy) {
        logger.info("addEnemy " + enemy);
        this.enemies.add(enemy);
        logger.info("new enemy added");
        return enemy;
    }
    public Enemy addEnemy(String enemyName, int meat, int speed, String description) {
        Enemy enemy = new Enemy(enemyName, meat, speed, description);
        logger.info("addEnemy " + enemy);
        this.enemies.add(enemy);
        logger.info("new enemy added");
        return enemy;
    }
    public Enemy getEnemy(String idEnemy) {
        logger.info("getEnemy(" + idEnemy + ")");
        for(Enemy e : this.enemies) {
            if(e.getIdEnemy().equals(idEnemy)) {
                logger.info("getEnemy(" + idEnemy + "): " + e);
                return e;
            }
        }
        logger.info("not found " + idEnemy);
        return null;
    }
    public List<Enemy> getEnemies() {
        Session session = null;
        try {
            session = FactorySession.openSession();
            this.enemies = session.findAll(Enemy.class);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return this.enemies;
    }

    @Override
    public void deleteEnemy(String idEnemy) {
        Enemy e = this.getEnemy(idEnemy);
        if(e == null) {
            logger.warn("not found " + idEnemy);
        }
        else {
            logger.info(e + " deleted ");
            this.enemies.remove(e);
        }
    }
}