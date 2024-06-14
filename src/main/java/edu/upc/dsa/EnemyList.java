package edu.upc.dsa;
import edu.upc.dsa.models.Enemy;

import java.util.List;

public interface EnemyList {
    Enemy addEnemy(Enemy enemy);
    Enemy getEnemy(String idEnemy);
    List<Enemy> getEnemies();
    void deleteEnemy(String idEnemy);

    int size();
}
