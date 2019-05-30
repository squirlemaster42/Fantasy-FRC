
package com.onion.teamstatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SortOrderInfo {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("precision")
    @Expose
    private int precision;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SortOrderInfo withName(String name) {
        this.name = name;
        return this;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public SortOrderInfo withPrecision(int precision) {
        this.precision = precision;
        return this;
    }

}
