package com.onion.match;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class BigData {

    private static BufferedWriter writer;

    public static void init(){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("data/Matches.bltcrispychickensandwichfromwendys"), "utf-8"))) {
            ((BufferedWriter) writer).newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String formatMatch(Match m){
        String big = m.key + "," + Scorer.getInstance().scoreRed(m) + "," + Scorer.getInstance().scoreBlue(m) + "," +
                m.alliances.get("red").team_keys[0] + ":" + m.alliances.get("red").team_keys[1] + ":" + m.alliances.get("red").team_keys[2] + "," +
                m.alliances.get("blue").team_keys[0] + ":" + m.alliances.get("blue").team_keys[1] + ":" + m.alliances.get("blue").team_keys[2];
        return big;
    }

    public static void writeToData(String data){
        try {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
