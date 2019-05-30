
package com.onion.teamstatus;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranking {

    @SerializedName("dq")
    @Expose
    private int dq;
    @SerializedName("matches_played")
    @Expose
    private int matchesPlayed;
    @SerializedName("qual_average")
    @Expose
    private Object qualAverage;
    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("record")
    @Expose
    private Record record;
    @SerializedName("sort_orders")
    @Expose
    private List<Double> sortOrders = null;
    @SerializedName("team_key")
    @Expose
    private String teamKey;

    public int getDq() {
        return dq;
    }

    public void setDq(int dq) {
        this.dq = dq;
    }

    public Ranking withDq(int dq) {
        this.dq = dq;
        return this;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Ranking withMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
        return this;
    }

    public Object getQualAverage() {
        return qualAverage;
    }

    public void setQualAverage(Object qualAverage) {
        this.qualAverage = qualAverage;
    }

    public Ranking withQualAverage(Object qualAverage) {
        this.qualAverage = qualAverage;
        return this;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Ranking withRank(int rank) {
        this.rank = rank;
        return this;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Ranking withRecord(Record record) {
        this.record = record;
        return this;
    }

    public List<Double> getSortOrders() {
        return sortOrders;
    }

    public void setSortOrders(List<Double> sortOrders) {
        this.sortOrders = sortOrders;
    }

    public Ranking withSortOrders(List<Double> sortOrders) {
        this.sortOrders = sortOrders;
        return this;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public Ranking withTeamKey(String teamKey) {
        this.teamKey = teamKey;
        return this;
    }

}
