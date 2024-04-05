package com.example.lab06_1a_210041115;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player2controller {

    int clickCountSend;


    @FXML
    private Label welcomeText;
    @FXML
    private Label score;
    @FXML
    Button butt=new Button();
    @FXML
    private Label winner;
    @FXML
    private Label score1;
    @FXML
    GridPane gridPane = new GridPane();
    private int clickCount = 0;
    private static final int MAX_CLICKS = 10;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int[][] buttonClicks = new int[11][12];
    private int[][] buttonClickssend = new int[11][12];
    int[] checkX=new int[]{-1, 0, 1, 0};
    int[] checkY=new int[]{0, 1, 0, -1};
    List<Pair<Integer, Integer>> buttonListRED=new ArrayList<>();
    List<Pair<Integer, Integer>> buttonListREDsend=new ArrayList<>();
    List<Pair<Integer, Integer>> buttonListBLACK=new ArrayList<>();
    List<Pair<Integer, Integer>> buttonListBLACKsend=new ArrayList<>();

    // Create buttons and add them to the grid
    public void dataOfOne(int[][] buttonClicksNew, int count,  List<Pair<Integer, Integer>> buttonListRED2, List<Pair<Integer, Integer>> buttonListBLK2)
    {
        buttonClickssend=buttonClicksNew;
        clickCountSend=count;
        buttonListREDsend=buttonListRED2;
        buttonListBLACKsend=buttonListBLK2;
    }


    public void initialize() {
        butt.setVisible(false);
        winner.setVisible(false);
        //butt.setDisable(true);
        // Initialize the buttonClicks array to false for all buttons
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                buttonClicks[i][j] = 0;
            }
        }
        //System.out.println("GP SIZE: " +gridPane.getChildren().size());

    }
    public void dataOfTwo(int[][] buttonClicksNew, int count,  List<Pair<Integer, Integer>> buttonListRED2, List<Pair<Integer, Integer>> buttonListBLK2)
    {
        buttonClicks=buttonClicksNew;
        clickCount=count;
        buttonListRED=buttonListRED2;
        buttonListBLACK=buttonListBLK2;
        for(int i=0; i<gridPane.getChildren().size(); i++) {
            Button b3=(Button) gridPane.getChildren().get(i);
            int row = GridPane.getRowIndex(b3);
            int col = GridPane.getColumnIndex(b3);
            score.setText("Score: " + buttonListRED.size());

            for (Pair<Integer, Integer> pair : buttonListRED) {
                int rowred = pair.getKey();
                int colred = pair.getValue();

                if(rowred==row && colred==col)
                {
                    Platform.runLater(()->{
                        b3.setStyle("-fx-background-color: red;");
                        b3.setDisable(true);

                    });
                    b3.requestLayout();
                    b3.applyCss();
                    //System.out.println("Checked: "+b3+" ");
                }
                //System.out.println(rowred + " " + colred);
            }
            for (Pair<Integer, Integer> pair : buttonListBLACK) {
                int rowblk = pair.getKey();
                int colblk = pair.getValue();

                if(rowblk==row && colblk==col)
                {
                    Platform.runLater(()->{
                        b3.setStyle("-fx-background-color: black;");
                        b3.setDisable(true);

                    });
                    b3.requestLayout();
                    b3.applyCss();
                    //System.out.println("Checked: "+b3+" ");
                }
                //System.out.println(rowblk + " " + colblk);
            }
        }
    }
    public void handleButtonClick(ActionEvent event) throws IOException {
        clickCount++;
        Button clickedButton = (Button) event.getSource();
        int row = GridPane.getRowIndex(clickedButton);
        int col = GridPane.getColumnIndex(clickedButton);
        if(clickCount==2)
        {
            int flag=0;
            if(buttonClicks[row][col]==0) {
                for (int i = 0; i < 4; i++) {
                    int newX=checkX[i];
                    int newY=checkY[i];
                    if(buttonClicks[row + newX][col + newY]==1)
                    {
                        extracted(row, col, clickedButton, 1);
                        score1.setText("Select 3rd input for battleship");
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    score1.setText("Invalid selection. Select again");
                    clickCount--;
                }
            }
        }
        else if(clickCount==3)
        {
            if(buttonClicks[row][col]==0) {
                int flag=0;
                for (int i = 0; i < 4; i++) {
                    int newX=checkX[i];
                    int newY=checkY[i];
                    if(buttonClicks[row + newX][col + newY]==1)
                    {
                        if(row==row+newX)
                        {
                            if(buttonClicks[row][col+newY-1]==1 || buttonClicks[row][col+newY+1]==1){
                                extracted(row, col, clickedButton, 1);
                                score1.setText("Select two buttons for destroyers");
                                flag=0;
                                break;
                            }
                            else{
                                flag=1;
                            }
                        }
                        else if(col==col+newY)
                        {
                            if(buttonClicks[row+newX-1][col]==1 || buttonClicks[row+newX+1][col]==1){
                                extracted(row, col, clickedButton, 1);
                                score1.setText("Select two buttons for 1st destroyers");
                                flag=0;
                                break;
                            }
                            else{
                                flag=1;
                            }
                        }
                        else{
                            flag=1;
                        }

                    }
                    else{
                        score1.setText("Invalid selection. Select again ");
                        flag=1;
                    }
                }
                if(flag==1) clickCount--;
            }

            System.out.println("click count at 3: "+clickCount);
        }
        else if(clickCount==5)
        {
            int flag=0;
            if(buttonClicks[row][col]==0) {
                for (int i = 0; i < 4; i++) {
                    int newX=checkX[i];
                    int newY=checkY[i];
                    if(buttonClicks[row + newX][col + newY]==2)
                    {
                        extracted(row, col, clickedButton,2);
                        score1.setText("Select 1st button for 2nd destroyer");
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    score1.setText("Invalid selection. Select again");
                    clickCount--;
                }
            }
        }
        else if(clickCount==7)
        {
            int flag=0;
            if(buttonClicks[row][col]==0) {
                for (int i = 0; i < 4; i++) {
                    int newX=checkX[i];
                    int newY=checkY[i];
                    if(buttonClicks[row + newX][col + newY]==3)
                    {
                        extracted(row, col, clickedButton, 3);
                        score1.setText("Select button for 1st submarine");
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    score1.setText("Invalid selection. Select again");
                    clickCount--;
                }
            }
        }
        else if(clickCount==1) {
            extracted(row, col, clickedButton, 1);
            score1.setText("Select 2nd input for battleship");
        }
        else if(clickCount==4){
            extracted(row, col, clickedButton, 2);
            score1.setText("Select 2nd button for 1st destroyer");
        }
        else if(clickCount==6){
            extracted(row, col, clickedButton, 3);
            score1.setText("Select 2nd button for 2nd destroyer");
        }
        else if(clickCount==8){
            extracted(row, col, clickedButton, 4);
            score1.setText("Select button for 2nd submarine");
        }
        else if(clickCount==9){
            extracted(row, col, clickedButton, 5);
            score1.setText("Select button for 3rd submarine");
        }
        else if(clickCount==10){
            extracted(row, col, clickedButton, 6);
            score1.setText("Selection complete");
            butt.setVisible(true);
            gridPane.setDisable(true);
          //  butt.setDisable(false);
        }
        if(clickCount>MAX_CLICKS){
            //butt.setDisable(false);
            if (buttonClicks[row][col]==0) {
                clickedButton.setStyle("-fx-background-color: #000000;");
                score1.setText("Oops! Wrong shot");
                buttonListBLACK.add(new Pair<>(row, col));
                gridPane.setDisable(true);
                butt.setVisible(true);
            }
            else {
                System.out.println("Button turning red: " + clickedButton);
                clickedButton.setStyle("-fx-background-color: red;");
                clickedButton.setDisable(true);
                buttonListRED.add(new Pair<>(row, col));
                score.setText("Score: " + buttonListRED.size());
                score1.setText("Correct shot!");
                if (buttonListRED.size() == MAX_CLICKS) {
                    gridPane.setDisable(true);
                    winner.setVisible(true);
                    winner.setText("Player 2 wins!!!!!!");
                    welcomeText.setVisible(false);
                    butt.setVisible(false);
                    score.setVisible(false);
                }
            }

            //switchPlayer2();
        }
    }
    private void extracted(int row, int col, Button clickedButton, int val) {
        buttonClicks[row][col] = val;
        clickedButton.setDisable(true);
        clickedButton.setStyle("-fx-border-color: blue;");

        //System.out.println("Button at row " + row + ", column " + col + " clicked.");
       // System.out.println("Button click status: " + buttonClicks[row][col]);
    }

    public void switchPlayer1(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root=loader.load();
        HelloController cc=loader.getController();
        cc.dataOfOne(buttonClickssend, clickCountSend, buttonListREDsend, buttonListBLACKsend);
        cc.dataOfTwo(buttonClicks, clickCount, buttonListRED, buttonListBLACK);

        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
