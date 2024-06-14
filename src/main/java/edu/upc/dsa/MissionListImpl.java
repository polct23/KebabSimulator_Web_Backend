package edu.upc.dsa;

import edu.upc.dsa.models.Mission;
import org.apache.log4j.Logger;

import java.util.List;

public class MissionListImpl implements MissionList {
    private static MissionListImpl instance;
    final static Logger logger = Logger.getLogger(MissionListImpl.class);

    private MissionListImpl(){}
    public static MissionListImpl getInstance(){
        if(instance == null) instance = new MissionListImpl();
        return instance;
    }

    public int size() {
        Session session = null;
        int ret = 0;
        try {
            session = FactorySession.openSession();
            ret = session.findAll(Mission.class).size();
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

    public Mission addMission(Mission mission) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(mission);
            logger.info("new mission added");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return mission;
    }

    public Mission getMission(String idMission) {
        Session session = null;
        Mission mission = null;
        try {
            session = FactorySession.openSession();
            mission = (Mission) session.get(Mission.class, "idMission", idMission);
            logger.info("getMission(" + idMission + "): " + mission);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return mission;
    }

    public List<Mission> getMissions() {
        Session session = null;
        List<Mission> missions = null;
        try {
            session = FactorySession.openSession();
            missions = session.findAll(Mission.class);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return missions;
    }

    public void deleteMission(String idMission) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Mission mission = (Mission) session.get(Mission.class, "idMission", idMission);
            if(mission != null) {
                session.delete(mission, "idMission");
                logger.info(mission + " deleted ");
            } else {
                logger.warn("not found " + idMission);
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