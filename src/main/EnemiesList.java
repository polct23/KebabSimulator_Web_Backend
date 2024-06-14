package edu.upc.dsa;
import edu.upc.dsa.models.Ability;

import java.util.List;

public interface AbilitiesList {
    Ability addWeapon(String idWeapon, String weaponName, String description, int damage, double price);
    Ability addWeapon(Ability ability);
    Ability getWeapon(String idWeapon);
    List<Ability> getWeapons();
    void deleteWeapon(String idWeapon);

    int size();
}
