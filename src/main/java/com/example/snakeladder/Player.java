package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.SpotLight;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currPosition;
    private String name;
    private static Board gameBoard=new Board();

    public Player(int tileSize, Color coinColor, String playerName)
    {
        coin=new Circle(tileSize/2);
        coin.setFill(coinColor);
        currPosition=0;
        movePlayer(1);
        name=playerName;
    }

    public void movePlayer(int diceValue)
    {
        if(currPosition+diceValue<=100)
        {
            currPosition+=diceValue;

            TranslateTransition secondMove = null;
            TranslateTransition firstMove = translateAnimation(diceValue);

            int newPosition = gameBoard.getNewPosition(currPosition);
            if(newPosition!=currPosition && newPosition!=-1)
            {
                currPosition=newPosition;
                secondMove=translateAnimation(6);
            }

            if(secondMove!=null)
            {
                SequentialTransition sequentialTransition=new SequentialTransition(firstMove, new PauseTransition(Duration.millis(50)),secondMove);
                sequentialTransition.play();
            }
            else {
                firstMove.play();
            }
        }
    }

    public boolean isWinner()
    {
        if(currPosition==100)
        {
            return true;
        }
        return false;
    }

    public void startingPoint()
    {
        currPosition=0;
        movePlayer(1);
    }

    private TranslateTransition translateAnimation(int diceValue) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(150*diceValue),coin);
        animate.setToX(gameBoard.getXCoordinate(currPosition));
        animate.setToY(gameBoard.getYCoordinate(currPosition));
        animate.setAutoReverse(false);
        return animate;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public String getName() {
        return name;
    }
}
