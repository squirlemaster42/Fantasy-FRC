package com.vaadin.starter.beveragebuddy.backend;
import java.util.HashMap;
import java.util.Map;


public class TeamMap {

    private final Map <Integer, String> teams;

    public TeamMap(){
        teams = new HashMap<>(); }

    public void addTeam (final Integer k, final String s){
        teams.put(k,s); }

    public void removeTeam (final Integer k){
        teams.remove(k); }

}
