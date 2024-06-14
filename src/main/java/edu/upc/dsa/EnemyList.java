package edu.upc.dsa;
import edu.upc.dsa.models.Enemy;

import java.util.List;

public interface EnemyList {
    Enemy addEnemy(String enemyName, int meat, int speed, String description);
    Enemy addEnemy(Enemy ability);
    Enemy getEnemy(String idWeapon);
    List<Enemy> getEnemies();
    void deleteEnemy(String idWeapon);

    int size();
}
