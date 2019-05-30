package com.onion.states;

import java.io.Serializable;

public class Teamdata implements Serializable {
    private String player;
    private int number;
    private int points;

    public Teamdata(){
        player = "name";
        number = 0;
        points = 0;
    }

    public Teamdata(String player, int number,int points){
        this.player = player;
        this.number = number;
        this.points = points;
    }

    public String getPlayer(){
        return player;
    }

    public int getNumber(){
        return number;
    }

    public int getPoints(){
        return points;
    }

}


