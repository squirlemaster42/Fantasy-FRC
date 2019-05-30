
package com.onion.teamstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("alliance")
    @Expose
    private Alliance alliance;
    @SerializedName("alliance_status_str")
    @Expose
    private String allianceStatusStr;
    @SerializedName("last_match_key")
    @Expose
    private String lastMatchKey;
    @SerializedName("next_match_key")
    @Expose
    private String nextMatchKey;
    @SerializedName("overall_status_str")
    @Expose
    private String overallStatusStr;
    @SerializedName("playoff")
    @Expose
    private Playoff playoff;
    @SerializedName("playoff_status_str")
    @Expose
    private String playoffStatusStr;
    @SerializedName("qual")
    @Expose
    private Qual qual;

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public Event withAlliance(Alliance alliance) {
        this.alliance = alliance;
        return this;
    }

    public String getAllianceStatusStr() {
        return allianceStatusStr;
    }

    public void setAllianceStatusStr(String allianceStatusStr) {
        this.allianceStatusStr = allianceStatusStr;
    }

    public Event withAllianceStatusStr(String allianceStatusStr) {
        this.allianceStatusStr = allianceStatusStr;
        return this;
    }

    public String getLastMatchKey() {
        return lastMatchKey;
    }

    public void setLastMatchKey(String lastMatchKey) {
        this.lastMatchKey = lastMatchKey;
    }

    public Event withLastMatchKey(String lastMatchKey) {
        this.lastMatchKey = lastMatchKey;
        return this;
    }

    public Object getNextMatchKey() {
        return nextMatchKey;
    }

    public void setNextMatchKey(String nextMatchKey) {
        this.nextMatchKey = nextMatchKey;
    }

    public Event withNextMatchKey(String nextMatchKey) {
        this.nextMatchKey = nextMatchKey;
        return this;
    }

    public String getOverallStatusStr() {
        return overallStatusStr;
    }

    public void setOverallStatusStr(String overallStatusStr) {
        this.overallStatusStr = overallStatusStr;
    }

    public Event withOverallStatusStr(String overallStatusStr) {
        this.overallStatusStr = overallStatusStr;
        return this;
    }

    public Playoff getPlayoff() {
        return playoff;
    }

    public void setPlayoff(Playoff playoff) {
        this.playoff = playoff;
    }

    public Event withPlayoff(Playoff playoff) {
        this.playoff = playoff;
        return this;
    }

    public String getPlayoffStatusStr() {
        return playoffStatusStr;
    }

    public void setPlayoffStatusStr(String playoffStatusStr) {
        this.playoffStatusStr = playoffStatusStr;
    }

    public Event withPlayoffStatusStr(String playoffStatusStr) {
        this.playoffStatusStr = playoffStatusStr;
        return this;
    }

    public Qual getQual() {
        return qual;
    }

    public void setQual(Qual qual) {
        this.qual = qual;
    }

    public Event withQual(Qual qual) {
        this.qual = qual;
        return this;
    }

}
