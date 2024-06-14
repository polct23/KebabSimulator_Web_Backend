package edu.upc.dsa;
import edu.upc.dsa.models.Mission;

import java.util.List;

public interface MissionList {
    Mission addMission(Mission mission);
    Mission getMission(String idMission);
    List<Mission> getMissions();
    void deleteMission(String idMission);

    int size();
}
