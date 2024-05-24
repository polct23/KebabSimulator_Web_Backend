package edu.upc.dsa;
import edu.upc.dsa.models.Weapon;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class WeaponListImpl implements WeaponList{
    private static WeaponListImpl instance;
    protected List<Weapon> weapons;
    final static Logger logger = Logger.getLogger(WeaponListImpl.class);

    private WeaponListImpl(){this.weapons = new ArrayList<>();}
    public static WeaponListImpl getInstance(){
        if(instance == null) instance = new WeaponListImpl();
        return instance;
    }

    public int size() {
        int ret = this.weapons.size();
        logger.info("size " + ret);
        return ret;
    }
    public Weapon addWeapon(Weapon weapon) {
        logger.info("addWeapon " + weapon);
        this.weapons.add(weapon);
        logger.info("new weapon added");
        return weapon;
    }
    public Weapon addWeapon(String idWeapon, String weaponName, String description, int damage, double price) {
        Weapon weapon = new Weapon(idWeapon, weaponName, description, damage, price);
        logger.info("addWeapon " + weapon);
        this.weapons.add(weapon);
        logger.info("new weapon added");
        return weapon;
    }
    public Weapon getWeapon(String idWeapon) {
        logger.info("getWeapon(" + idWeapon + ")");
        for(Weapon w : this.weapons) {
            if(w.getIdWeapon().equals(idWeapon)) {
                logger.info("getUser(" + idWeapon + "): " + w);
                return w;
            }
        }
        logger.info("not found " + idWeapon);
        return null;
    }
    public List<Weapon> getWeapons() {
        Session session = null;
        try {
            session = FactorySession.openSession();
            this.weapons = session.findAll(Weapon.class);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return this.weapons;
    }

    @Override
    public void deleteWeapon(String idWeapon) {
        Weapon w = this.getWeapon(idWeapon);
        if(w != null) {
            logger.warn("not found " +w);
        }
        else {
            logger.info(w + "deleted ");
            this.weapons.remove(w);
        }

    }
}
