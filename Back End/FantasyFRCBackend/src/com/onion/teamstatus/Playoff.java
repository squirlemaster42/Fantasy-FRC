
package com.onion.teamstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playoff {

    @SerializedName("current_level_record")
    @Expose
    private CurrentLevelRecord currentLevelRecord;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("playoff_average")
    @Expose
    private Object playoffAverage;
    @SerializedName("record")
    @Expose
    private Record record;
    @SerializedName("status")
    @Expose
    private String status;

    public CurrentLevelRecord getCurrentLevelRecord() {
        return currentLevelRecord;
    }

    public void setCurrentLevelRecord(CurrentLevelRecord currentLevelRecord) {
        this.currentLevelRecord = currentLevelRecord;
    }

    public Playoff withCurrentLevelRecord(CurrentLevelRecord currentLevelRecord) {
        this.currentLevelRecord = currentLevelRecord;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Playoff withLevel(String level) {
        this.level = level;
        return this;
    }

    public Object getPlayoffAverage() {
        return playoffAverage;
    }

    public void setPlayoffAverage(Object playoffAverage) {
        this.playoffAverage = playoffAverage;
    }

    public Playoff withPlayoffAverage(Object playoffAverage) {
        this.playoffAverage = playoffAverage;
        return this;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Playoff withRecord(Record record) {
        this.record = record;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Playoff withStatus(String status) {
        this.status = status;
        return this;
    }

}
