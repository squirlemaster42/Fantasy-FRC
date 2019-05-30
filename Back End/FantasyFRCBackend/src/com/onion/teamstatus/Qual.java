
package com.onion.teamstatus;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Qual {

    @SerializedName("num_teams")
    @Expose
    private int numTeams;
    @SerializedName("ranking")
    @Expose
    private Ranking ranking;
    @SerializedName("sort_order_info")
    @Expose
    private List<SortOrderInfo> sortOrderInfo = null;
    @SerializedName("status")
    @Expose
    private String status;

    public int getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public Qual withNumTeams(int numTeams) {
        this.numTeams = numTeams;
        return this;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public Qual withRanking(Ranking ranking) {
        this.ranking = ranking;
        return this;
    }

    public List<SortOrderInfo> getSortOrderInfo() {
        return sortOrderInfo;
    }

    public void setSortOrderInfo(List<SortOrderInfo> sortOrderInfo) {
        this.sortOrderInfo = sortOrderInfo;
    }

    public Qual withSortOrderInfo(List<SortOrderInfo> sortOrderInfo) {
        this.sortOrderInfo = sortOrderInfo;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Qual withStatus(String status) {
        this.status = status;
        return this;
    }

}
