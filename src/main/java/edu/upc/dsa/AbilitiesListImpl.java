package edu.upc.dsa;

import edu.upc.dsa.models.Ability;
import org.apache.log4j.Logger;

import java.util.List;

public class AbilitiesListImpl implements AbilitiesList {
    private static AbilitiesListImpl instance;
    final static Logger logger = Logger.getLogger(AbilitiesListImpl.class);

    private AbilitiesListImpl(){}
    public static AbilitiesListImpl getInstance(){
        if(instance == null) instance = new AbilitiesListImpl();
        return instance;
    }

    public int size() {
        Session session = null;
        int ret = 0;
        try {
            session = FactorySession.openSession();
            ret = session.findAll(Ability.class).size();
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

    public Ability addAbility(Ability ability) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(ability);
            logger.info("new ability added");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ability;
    }

    public Ability getAbility(String idAbility) {
        Session session = null;
        Ability ability = null;
        try {
            session = FactorySession.openSession();
            ability = (Ability) session.get(Ability.class, "idAbility", idAbility);
            logger.info("getAbility(" + idAbility + "): " + ability);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ability;
    }

    public List<Ability> getAbilities() {
        Session session = null;
        List<Ability> abilities = null;
        try {
            session = FactorySession.openSession();
            abilities = session.findAll(Ability.class);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return abilities;
    }

    public void deleteAbility(String idAbility) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Ability ability = (Ability) session.get(Ability.class, "idAbility", idAbility);
            if(ability != null) {
                session.delete(ability, "idAbility");
                logger.info(ability + " deleted ");
            } else {
                logger.warn("not found " + idAbility);
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