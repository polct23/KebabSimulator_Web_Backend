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
    public Ability addAbility(Ability ability) {
        logger.info("addAbility " + ability);
        this.abilities.add(ability);
        logger.info("new ability added");
        return ability;
    }
    public Ability addAbility(String abilityName, String description, int damage, double price) {
        Ability ability = new Ability(abilityName, description, damage, price);
        logger.info("addAbility " + ability);
        this.abilities.add(ability);
        logger.info("new ability added");
        return ability;
    }
    public Ability getAbility(String idAbility) {
        logger.info("getAbility(" + idAbility + ")");
        for(Ability w : this.abilities) {
            if(w.getIdAbility().equals(idAbility)) {
                logger.info("getUser(" + idAbility + "): " + w);
                return w;
            }
        }
        logger.info("not found " + idAbility);
        return null;
    }
    public List<Ability> getAbilities() {
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

    public void deleteAbility(String idAbility) {
        Ability w = this.getAbility(idAbility);
        if(w == null) {
            logger.warn("not found " + idAbility);
        }
        else {
            logger.info(w + " deleted ");
            this.abilities.remove(w);
        }
    }
}
