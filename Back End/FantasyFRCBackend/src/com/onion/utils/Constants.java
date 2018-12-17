package com.onion.utils;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;

import java.io.File;
import java.util.ArrayList;

public class Constants {

    public static final int DRAFT_SIZE = 8;

    private static Constants instance;

    //Stores Blue Alliance Auth key
    private String TBAAuthKey;

    private final OkHttpClient client;
    private final Gson gson;

    //Stores valid event strings for championship fields
    private final ArrayList<String> champGameStrings;
    private File pathToCSV;
    private String year;

    //Stores scores for alliance selection
    private final int[][] allianceSelectionScoringGuide;

    public static Constants getInstance(){
        if(instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    private Constants(){
        //Adds event strings to champ event list
        this.champGameStrings = new ArrayList<>();
        champGameStrings.add("2018carv");
        champGameStrings.add("2018gal");
        champGameStrings.add("2018hop");
        champGameStrings.add("2018new");
        champGameStrings.add("2018roe");
        champGameStrings.add("2018tur");
        champGameStrings.add("2018arc");
        champGameStrings.add("2018cars");
        champGameStrings.add("2018cur");
        champGameStrings.add("2018dal");
        champGameStrings.add("2018dar");
        champGameStrings.add("2018tes");

        this.gson = new Gson();
        this.client = new OkHttpClient();

        //Makes alliance selection scoring guide
        allianceSelectionScoringGuide = new int[][]{
                {24, 20, 16, 12},
                {23, 19, 15, 11},
                {22, 18, 14, 10},
                {21, 17, 13, 9},
                {20, 16, 12, 8},
                {19, 15, 11, 7},
                {18, 14, 10, 6},
                {17, 13, 9, 5}
        };
    }

    public int[][] getAllianceSelectionScoringGuide(){
        return this.allianceSelectionScoringGuide;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Gson getGson() {
        return gson;
    }

    public ArrayList<String> getChampGameStrings(){
        return this.champGameStrings;
    }

    public String getTBAAuthKey(){
        return this.TBAAuthKey;
    }

    public File getPathToCSV(){
        return pathToCSV;
    }

    public void setPathToCSV(File file){
        this.pathToCSV = file;
    }

    public void setTBAAuthKey(String key){
        this.TBAAuthKey = key;
    }

    public void setYear(String year){
        this.year = year;
    }

    public String getYear(){
        return this.year;
    }

}
