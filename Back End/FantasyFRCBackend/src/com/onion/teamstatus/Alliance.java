
package com.onion.teamstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alliance {

    @SerializedName("backup")
    @Expose
    private Object backup;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("pick")
    @Expose
    private int pick;

    public Object getBackup() {
        return backup;
    }

    public void setBackup(Object backup) {
        this.backup = backup;
    }

    public Alliance withBackup(Object backup) {
        this.backup = backup;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Alliance withName(String name) {
        this.name = name;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Alliance withNumber(int number) {
        this.number = number;
        return this;
    }

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }

    public Alliance withPick(int pick) {
        this.pick = pick;
        return this;
    }

}
