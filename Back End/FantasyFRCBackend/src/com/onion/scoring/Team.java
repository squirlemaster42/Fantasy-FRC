package com.onion.scoring;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Team implements Serializable {

    private final String number;
    private int score; //TODO Expand into score categories
    int lastMatchScored = -1;

    final ArrayList<String> matchList;

    public Team(final String number){
        this.number = number;
        this.score = 0;
        matchList = new ArrayList<>();
    }

    protected Team(final String number, final int score){
        this.number = number;
        this.score = score;
        matchList = new ArrayList<>();
    }

    public String getNumber(){
        return number;
    }

    void setScore(final int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public String getTBARequestID(){
        return "frc" + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return number.equals(team.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
