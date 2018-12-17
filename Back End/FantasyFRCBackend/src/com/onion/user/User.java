package com.onion.user;

import com.onion.game.Draft;

import java.util.HashMap;
import java.util.Map;

public class User {

    //TODO Add login

    private final String name;
    private final Password password;
    private final Map<Integer, Draft> drafts;
    

    public User(final String name, final Password password){
        this.name = name;
        this.password = password;
        this.drafts = new HashMap<>();
    }

    public void addTeam(){

    }

    public void updateTeamList(){

    }

    public void getTeamList(){

    }
}
