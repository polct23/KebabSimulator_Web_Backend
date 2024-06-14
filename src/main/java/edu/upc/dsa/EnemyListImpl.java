package edu.upc.dsa;

import edu.upc.dsa.models.Enemy;
import org.apache.log4j.Logger;

import java.util.List;

public class EnemyListImpl implements EnemyList {
    private static EnemyListImpl instance;
    final static Logger logger = Logger.getLogger(EnemyListImpl.class);

    private EnemyListImpl(){}
    public static EnemyListImpl getInstance(){
        if(instance == null) instance = new EnemyListImpl();
        return instance;
    }

    public int size() {
        Session session = null;
        int ret = 0;
        try {
            session = FactorySession.openSession();
            ret = session.findAll(Enemy.class).size();
            logger.info("size " + ret);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ret;
    }

    public Enemy addEnemy(Enemy enemy) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(enemy);
            logger.info("new enemy added");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return enemy;
    }

    public Enemy getEnemy(String idEnemy) {
        Session session = null;
        Enemy enemy = null;
        try {
            session = FactorySession.openSession();
            enemy = (Enemy) session.get(Enemy.class, "idEnemy", idEnemy);
            logger.info("getEnemy(" + idEnemy + "): " + enemy);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return enemy;
    }

    public List<Enemy> getEnemies() {
        Session session = null;
        List<Enemy> enemies = null;
        try {
            session = FactorySession.openSession();
            enemies = session.findAll(Enemy.class);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return enemies;
    }

    public void deleteEnemy(String idEnemy) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Enemy enemy = (Enemy) session.get(Enemy.class, "idEnemy", idEnemy);
            if(enemy != null) {
                session.delete(enemy, "idEnemy");
                logger.info(enemy + " deleted ");
            } else {
                logger.warn("not found " + idEnemy);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}