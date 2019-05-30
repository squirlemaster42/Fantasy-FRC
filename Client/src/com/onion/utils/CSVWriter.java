package com.onion.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CSVWriter {
    private static final String SAMPLE_CSV_FILE = "Teamvals.csv";
    private int row;
    private int col;
    private int number;
    private int [][] teamNums;

    public CSVWriter(int[][] teamNums, int row, int col){
        this.row = row;
        this.col = col;
        this.teamNums = teamNums;
        this.number = 0;

    }

    public CSVWriter(int[][] teamNums){
        this.row = 0;
        this.col = 0;
        this.teamNums = teamNums;
        this.number = 0;

    }

    public int[][] edit(int replacement){
        teamNums[row][col] = replacement;
        return teamNums;

    }

    public void setRow(int num){
        row = num;
    }

    public void setCol(int num){
        col = num;
    }

    public void write() throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)
        ) {
            csvPrinter.printRecord(Integer.toString(number));

            csvPrinter.printRecord(Arrays.asList("player5", "5", "3454"));

            csvPrinter.flush();
        }
    }
}
