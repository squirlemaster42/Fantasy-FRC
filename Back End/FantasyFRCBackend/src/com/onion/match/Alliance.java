package com.onion.match;

public class Alliance {

    String[] dq_team_keys;
    int score;
    String[] surrogate_team_keys;
    String[] team_keys;

    public String[] getDq_team_keys() {
        return dq_team_keys;
    }

    public int getScore() {
        return score;
    }

    public String[] getSurrogate_team_keys() {
        return surrogate_team_keys;
    }

    public String[] getTeam_keys() {
        return team_keys;
    }

    @Override
    public String toString() {
        return "" + team_keys[0] + ":" + team_keys[1] + ":" + team_keys[2] + ":" + score;
    }
}
