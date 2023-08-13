package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40, width=10, height=10;
    public static final int buttonLine= height*tileSize + 40, infoLine= buttonLine-20;
    private static Dice dice=new Dice();
    private static Player playerOne,playerTwo;
    private boolean gameStarted=false, playerOneTurn=false, playerTwoTurn=false;
    private Pane createContent() {
       Pane root = new Pane();
       root.setPrefSize(width*tileSize, height*tileSize + 100);

       // grid
        for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Tile tile=new Tile(tileSize);
                    tile.setTranslateX(j*tileSize);
                    tile.setTranslateY(i*tileSize);
                    root.getChildren().addAll(tile);
                }
            }

        // image
        Image img=new Image("C:\\Users\\Sumit\\IdeaProjects\\SnakeLadder\\src\\main\\istockphoto-531466314-1024x1024.png");
        ImageView board=new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        // downImage
        Image downImage=new Image("C:\\Users\\Sumit\\IdeaProjects\\SnakeLadder\\src\\main\\images.jpeg");
        ImageView down=new ImageView();
        down.setImage(downImage);
        down.setFitHeight(109);
        down.setFitWidth(400);
        down.setTranslateY(buttonLine-40);
        down.setOpacity(1);

        // leftArrow
        Image arrow=new Image("C:\\Users\\Sumit\\IdeaProjects\\SnakeLadder\\src\\main\\Arrows_down_animated.gif");
        ImageView downLeft=new ImageView();
        downLeft.setImage(arrow);
        downLeft.setFitHeight(50);
        downLeft.setFitWidth(25);
        downLeft.setTranslateX(45);
        downLeft.setTranslateY(buttonLine-47);
        downLeft.setOpacity(-1);

        // rightArrow
        ImageView downRight=new ImageView();
        downRight.setImage(arrow);
        downRight.setFitHeight(50);
        downRight.setFitWidth(25);
        downRight.setTranslateX(325);
        downRight.setTranslateY(buttonLine-47);
        downRight.setOpacity(-1);

        // celebration
        Image celeb=new Image("C:\\Users\\Sumit\\IdeaProjects\\SnakeLadder\\src\\main\\YGg4.gif");
        ImageView celebPop=new ImageView(celeb);
        celebPop.setFitHeight(100);
        celebPop.setFitWidth(400);
        celebPop.setTranslateY(buttonLine-40);
        celebPop.setOpacity(-1);

        // buttons
        Button playerOneButton=new Button("Player One");
        playerOneButton.setFont(Font.font("Italic", FontWeight.BOLD, FontPosture.REGULAR,12));
        playerOneButton.setTextFill(Color.DARKGOLDENROD);
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setDisable(true);

        Button startButton=new Button("Start");
        startButton.setFont(Font.font("Italic", FontWeight.BOLD, FontPosture.REGULAR,12));
        startButton.setTextFill(Color.BLACK);
        startButton.setTranslateX(178);
        startButton.setTranslateY(buttonLine);

        Button playerTwoButton=new Button("Player Two");
        playerTwoButton.setFont(Font.font("Italic", FontWeight.BOLD, FontPosture.REGULAR,12));
        playerTwoButton.setTextFill(Color.DEEPPINK);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setDisable(true);

        // labels
        Label playerOneLabel=new Label("");
        playerOneLabel.setTranslateX(40);
        playerOneLabel.setTranslateY(infoLine);

        Label startLabel=new Label("Let`s Start!");
        startLabel.setTranslateX(172);
        startLabel.setTranslateY(infoLine);

        Label playerTwoLabel=new Label("");
        playerTwoLabel.setTranslateX(319);
        playerTwoLabel.setTranslateY(infoLine);

        playerOne=new Player(tileSize-2, Color.LIGHTCYAN, "Amit");
        playerTwo=new Player(tileSize-3, Color.PALEGREEN, "Sumit");

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted)
                {
                    if(playerOneTurn)
                    {
                        int diceValue=dice.diceRoll();
                        playerOne.movePlayer(diceValue);
                        startLabel.setText("");
                        startButton.setText("<- " + diceValue);

                        if(playerOne.isWinner())
                        {
                            playerOneTurn=false;
                            playerTwoTurn=false;
                            playerOneButton.setDisable(false);
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            playerOneButton.setText("    Won!    ");
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            gameStarted=false;
                            downRight.setOpacity(-1);
                            downLeft.setOpacity(-1);
                            celebPop.setOpacity(1);
                        }
                        else {
                            playerOneTurn=false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn=true;
                            playerTwoButton.setDisable(false);
                            downRight.setOpacity(1);
                            downLeft.setOpacity(-1);
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted)
                {
                    if(playerTwoTurn)
                    {
                        int diceValue=dice.diceRoll();
                        playerTwo.movePlayer(diceValue);
                        startLabel.setText("");
                        startButton.setText(diceValue + " ->");

                        if(playerTwo.isWinner())
                        {
                            playerOneTurn=false;
                            playerTwoTurn=false;
                            playerOneButton.setDisable(true);
                            playerTwoButton.setDisable(false);
                            playerOneLabel.setText("");
                            playerTwoButton.setText("    Won!    ");
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            gameStarted=false;
                            downRight.setOpacity(-1);
                            downLeft.setOpacity(-1);
                            celebPop.setOpacity(1);
                        }
                        else {
                            playerTwoTurn=false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            playerOneTurn=true;
                            playerOneButton.setDisable(false);
                            downLeft.setOpacity(1);
                            downRight.setOpacity(-1);
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!gameStarted)
                {
                    gameStarted=true;
                    startButton.setDisable(true);
                    startLabel.setText("Good Luck!");
                    playerOneTurn=true;
                    playerOneButton.setDisable(false);
                    playerOne.startingPoint();
                    playerTwo.startingPoint();
                    startButton.setText("Start");
                    downLeft.setOpacity(1);
                    playerOneButton.setText("Player One");
                    playerTwoButton.setText("Player two");
                    celebPop.setOpacity(-1);
                }
            }
        });

        root.getChildren().addAll(board,down,downLeft,downRight,celebPop,playerOneButton,startButton,playerTwoButton,playerOneLabel,startLabel,playerTwoLabel,playerOne.getCoin(),playerTwo.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}