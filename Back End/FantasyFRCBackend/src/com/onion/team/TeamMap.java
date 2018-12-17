package com.onion.team;

import java.util.HashMap;
import java.util.Map;

public class TeamMap {
    private static TeamMap ourInstance = new TeamMap();

    public static TeamMap getInstance() {
        return ourInstance;
    }

    private final Map<String, Team> teamMap;

    private TeamMap() {
        teamMap = new HashMap<>();
    }

    private void put(String tbaID, Team team){
        teamMap.put(tbaID, team);
    }

    private Team get(String tbaID){
        return teamMap.get(tbaID);
    }
}
