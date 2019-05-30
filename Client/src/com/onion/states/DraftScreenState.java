package com.onion.states;
import com.onion.utils.CSVWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DraftScreenState implements State {

    private Scene scene;
    private TableView<Teamdata> table;
    private static final String FILE_LOCATION = "Teamvals.csv";
    private String [] colHeaders;
    private String [] rowHeaders;
    private int [][] teamslist = new int [7][7];


   public DraftScreenState() {
   }


    @Override
    public void setScene(final Stage primaryStage) {
        primaryStage.setScene(scene);
    }

    @Override
    public void createScene(final Stage primaryStage) {


        Button backBtn = new Button("Back");
        HBox hbbackBtn = new HBox(10);
        backBtn.setLayoutX(1000.0);
        backBtn.setLayoutY(1.0);
        hbbackBtn.getChildren().add(backBtn);


        TableColumn<Teamdata, String> playerCol = new TableColumn<>("Players");
        playerCol.setCellValueFactory(new PropertyValueFactory<>("players"));


        TableColumn<Teamdata, Integer> numCol = new TableColumn<>("Teams");
        numCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Teamdata, Integer> col2 = new TableColumn<>(" ");

        TableColumn<Teamdata, Integer> col4 = new TableColumn<>("These");
        TableColumn<Teamdata, Integer> col5 = new TableColumn<>("Are");
        TableColumn<Teamdata, Integer> col6 = new TableColumn<>("Other");
        TableColumn<Teamdata, Integer> col7 = new TableColumn<>("Columns");
        TableColumn<Teamdata, Integer> col8 = new TableColumn<>("You're");
        TableColumn<Teamdata, Integer> col9 = new TableColumn<>("ah");

        table = new TableView<>();
        table.setItems(getTeam());
        table.getColumns().addAll(playerCol,col2,numCol, col4,col5,col6,col7,col8,col9);
        table.setLayoutX(100.0);
        table.setLayoutY(100.0);



        Pane pane = new Pane();
        this.scene = new Scene(pane, 1100, 800);
        scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        pane.getChildren().add(backBtn);
        pane.getChildren().add(table);

        backBtn.setOnAction(e -> {
            try {
                StateManager.getInstance().setCurrentState("MainScreenState");
            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }


        });

    }

    public ObservableList<Teamdata> getTeam(){
        ObservableList<Teamdata> teams = FXCollections.observableArrayList();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(FILE_LOCATION));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("player", "number", "points")
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            int x = 0;
            int y = 0;
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by the names assigned to each column
                String player = csvRecord.get("player");
                String number = csvRecord.get("number");
                String points = csvRecord.get("points");

                int numberInt = Integer.parseInt(number);
                int totalPoints = Integer.parseInt(points);
                rowHeaders[x] = player;
                colHeaders[y] = player;

                teamslist[x][y] = numberInt;
                if(x < 7 && y < 7) {
                    x += 1;
                    y += 1;
                }
                CSVWriter writer = new CSVWriter(teamslist,1,1);
                writer.edit(1345);

                //teams.add(new Teamdata(player,numberInt,totalPoints));



            }
        }
        catch (IOException e){
            e.printStackTrace();
        }





        return teams;


    }

}
