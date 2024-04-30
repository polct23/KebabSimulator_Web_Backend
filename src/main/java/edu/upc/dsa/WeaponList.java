package edu.upc.dsa;
import edu.upc.dsa.models.Weapon;
import java.util.List;

public interface WeaponList {
    Weapon addWeapon(String idWeapon, String weaponName, String description, int damage, double price);
    Weapon addWeapon(Weapon weapon);
    Weapon getWeapon(String idWeapon);
    List<Weapon> getWeapons();
    void deleteWeapon(String idWeapon);

    int size();
}
