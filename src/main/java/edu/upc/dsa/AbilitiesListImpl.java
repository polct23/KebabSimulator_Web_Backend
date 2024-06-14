package edu.upc.dsa;
import edu.upc.dsa.models.Ability;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class AbilitiesListImpl implements AbilitiesList {
    private static AbilitiesListImpl instance;
    protected List<Ability> abilities;
    final static Logger logger = Logger.getLogger(AbilitiesListImpl.class);

    private AbilitiesListImpl(){this.abilities = new ArrayList<>();}
    public static AbilitiesListImpl getInstance(){
        if(instance == null) instance = new AbilitiesListImpl();
        return instance;
    }

    public int size() {
        int ret = this.abilities.size();
        logger.info("size " + ret);
        return ret;
    }
    public Ability addWeapon(Ability ability) {
        logger.info("addWeapon " + ability);
        this.abilities.add(ability);
        logger.info("new weapon added");
        return ability;
    }
    public Ability addWeapon(String idWeapon, String weaponName, String description, int damage, double price) {
        Ability ability = new Ability(idWeapon, weaponName, description, damage, price);
        logger.info("addWeapon " + ability);
        this.abilities.add(ability);
        logger.info("new weapon added");
        return ability;
    }
    public Ability getWeapon(String idWeapon) {
        logger.info("getWeapon(" + idWeapon + ")");
        for(Ability w : this.abilities) {
            if(w.getIdAbility().equals(idWeapon)) {
                logger.info("getUser(" + idWeapon + "): " + w);
                return w;
            }
        }
        logger.info("not found " + idWeapon);
        return null;
    }
    public List<Ability> getWeapons() {
        Session session = null;
        try {
            session = FactorySession.openSession();
            this.abilities = session.findAll(Ability.class);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return this.abilities;
    }

    @Override
    public void deleteWeapon(String idWeapon) {
        Ability w = this.getWeapon(idWeapon);
        if(w != null) {
            logger.warn("not found " +w);
        }
        else {
            logger.info(w + "deleted ");
            this.abilities.remove(w);
        }

    }
}
