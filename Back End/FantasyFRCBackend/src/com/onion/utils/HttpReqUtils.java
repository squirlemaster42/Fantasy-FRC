package com.onion.utils;

import com.onion.team.Team;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpReqUtils {

    //Makes HTTP request to TBA
    public static Object makeRequest(final String reqURL) throws IOException {
        //System.out.println(reqURL);

        //Creates request with given URL and adds header with TBAAuthKey
        Request request = new Request.Builder()
                .url(reqURL)
                .addHeader("X-TBA-Auth-Key", Constants.getInstance().getTBAAuthKey())
                .build();

        Response response = Constants.getInstance().getClient().newCall(request).execute();
        return response.body().string();
    }

    //Used to make the request URL
    //Gets match list for a given team and event
    public static String makeMatchListReq(final Team team, final String eventID){
        return "https://www.thebluealliance.com/api/v3/team/" + team.getTBARequestID() + "/event/" + eventID + "/matches";
    }

    public static String makeMatchListReq(final String eventID){
        return "https://www.thebluealliance.com/api/v3/event/" + eventID + "/matches/keys";
    }

    public static String makeTeamMatchListReq(final Team team, final int year){
        return "https://www.thebluealliance.com/api/v3/team/" + team.getTBARequestID() + "/matches/" + year + "/keys";
    }

    public static String makeYearEventListReq(final int year){
        return "https://www.thebluealliance.com/api/v3/events/" + year + "/keys";
    }

    //Used to make the request URL
    //Gets event list for a given team
    public static String makeEventListReq(final Team team){
        return "https://www.thebluealliance.com/api/v3/team/" + team.getTBARequestID() + "/events/2018/keys";
    }

    //Used to make the request URL
    //Gets match for given team
    public static String makeMatchReq(final String matchID){
        return "https://www.thebluealliance.com/api/v3/match/" + matchID;
    }

    //Used to make the request URL
    //Gets match list for team at given event
    public static String makeMatchListRequest(final Team team, final String eventID){
        return "https://www.thebluealliance.com/api/v3/team/" + team.getTBARequestID() + "/event/" + eventID + "/matches/keys";
    }

    //Used to make the request URL
    //Gets team status at given event
    public static String makeTeamEventStatusRequest(final Team team, final String eventID){
        return "https://www.thebluealliance.com/api/v3/team/" + team.getTBARequestID() + "/event/" + eventID + "/status";
    }
}
