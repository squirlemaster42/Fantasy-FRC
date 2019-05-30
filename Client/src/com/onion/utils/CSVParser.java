package com.onion.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CSVParser {

    //private final Game game;

    public CSVParser(final String filePath){
        parseCSV(loadCSV(filePath));
    }

    //Loads CSV at specified filePath
    private ArrayList<String> loadCSV(final String filePath){
        ArrayList<String> file = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            String line;
            while((line = reader.readLine()) != null){
                file.add(line);
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e){
            e.printStackTrace();
        }

        return file;
    }

    //Takes ArrayList where each index is a line in a CSV file
    //Returns an instance of Game with players and teams from the CSV file
    private String[][] parseCSV(final ArrayList<String> file){
        //Var init
        String[] parameters;
        Map<String, ArrayList<String>> playerTeamMap = new HashMap<>();
        String parameterString = file.get(0).replaceFirst(",", "").trim();

        //Parse players
        parameters = parameterString.split(",");

        //Parse teams
        String[][] teamList = new String[8][8];
        for(int i = 0; i < 8; i++){
            String lineToParse = file.get(i + 1).substring(2);
            teamList[i] = lineToParse.split(",");
        }

        //Add values to map
        int index = 0;
        for(String player : parameters){
            playerTeamMap.put(player, new ArrayList<>());
            String[] temp = new String[8];
            for(int i = 0; i < teamList[0].length; i++) {
                temp[i] = teamList[i][index];
            }
            playerTeamMap.get(player).addAll((Arrays.asList(temp)));
            index++;
        }

        //Output instance of Game

        //Adds players to the output instance

        return teamList;
    }
}
