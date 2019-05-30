package com.onion.test;

import com.onion.client.Client;
import com.onion.states.Teamdata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCSV {
    public static void main(String args[])throws IOException{
     /*   try (
                Reader reader = Files.newBufferedReader(Paths.get("G:\\Fantasy FRC\\Fantasy-FRC\\Client\\src\\com\\onion\\states\\Teamvals.csv"));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String name = csvRecord.get(0);
                String number = csvRecord.get(1);
                String points = csvRecord.get(2);


                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Name : " + name);
                System.out.println("Number : " + number);
                System.out.println("Points : " + points);
                System.out.println("---------------\n\n");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        */
        try (
                Reader reader = Files.newBufferedReader(Paths.get("G:\\Fantasy FRC\\Fantasy-FRC\\Client\\src\\com\\onion\\test\\Teamvals.csv"));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("player", "number", "points")
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by the names assigned to each column
                String player = csvRecord.get("player");
                String number = csvRecord.get("number");
                String points = csvRecord.get("points");


                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Name : " + player);
                System.out.println("Email : " + number);
                System.out.println("Phone : " + points);
                System.out.println("---------------\n\n");
            }
        }
    }
}



