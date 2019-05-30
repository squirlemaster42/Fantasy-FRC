
package com.onion.teamstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("losses")
    @Expose
    private int losses;
    @SerializedName("ties")
    @Expose
    private int ties;
    @SerializedName("wins")
    @Expose
    private int wins;

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public Record withLosses(int losses) {
        this.losses = losses;
        return this;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public Record withTies(int ties) {
        this.ties = ties;
        return this;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Record withWins(int wins) {
        this.wins = wins;
        return this;
    }

}
