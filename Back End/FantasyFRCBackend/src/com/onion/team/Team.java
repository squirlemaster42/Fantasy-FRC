package com.onion.team;

import java.util.HashMap;
import java.util.Map;

public class Team {

    //TODO Add more methods

    private final String tbaID;
    private final Map<String, Integer> eventMap; //Event ID, Score

    public Team(int teamNum){
        tbaID = "frc" + teamNum;
        eventMap = new HashMap<>();

    }

    public void addScore(String eventID, int score){
        eventMap.put(eventID, score);
    }

    public int getScore(String eventID){
        return eventMap.get(eventID);
    }

    public void getMatchList(){

    }

    public String getTBARequestID(){
        return tbaID;
    }
}
