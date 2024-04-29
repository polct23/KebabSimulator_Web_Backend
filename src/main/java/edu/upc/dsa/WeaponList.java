package edu.upc.dsa;
import edu.upc.dsa.models.Weapon;
import java.util.List;

public interface WeaponList {
    public Weapon addWeapon(String idWeapon, String weaponName, String description, int damage, double price);
    public Weapon addWeapon(Weapon weapon);
    public Weapon getWeapon(String idWeapon);
    public List<Weapon> getWeapons();
    public void deleteWeapon(String idWeapon);

    public int size();
}
