package com.onion.parser;

import com.onion.event.Team;
import com.onion.utils.Constants;
import com.onion.match.Match;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    //Takes a list that in input as a string and parses it to an ArrayList
    public static ArrayList<String> listParser(String TBAString){
        ArrayList<String> outputList;

        //Removes [] in the front and back of the string
        TBAString = TBAString.replaceAll("\\[", "");
        TBAString = TBAString.replaceAll("\\]", "");

        //Splits the string at ","
        outputList = new ArrayList<>(Arrays.asList(TBAString.split(",")));

        //Removes new lines
        for (int i = 0; i < outputList.size(); i++) {
            outputList.set(i, outputList.get(i).replaceAll("\"", "").trim());
        }

        return outputList;
    }

    //Parses JSon match data
    public static ArrayList<Match> parseMatches(String JsonData) {
        Match[] matches = Constants.getInstance().getGson().fromJson(JsonData, Match[].class);
        return new ArrayList<Match>(Arrays.asList(matches));
    }

    public static Match parseMatch(String JsonData) {
        Match match = Constants.getInstance().getGson().fromJson(JsonData, Match.class);
        return match;
    }

    //Parses JSon match status
    public static Team parseTeamStatus(String JsonData){
        Team team = Constants.getInstance().getGson().fromJson(JsonData, Team.class);
        return team;
    }
}
