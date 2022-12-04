package com.tictaktoe.demo;

public interface CommonConstants {
    interface ResponseDescriptions{
        String X_WINS = "X Wins";
        String Y_WINS = "Y Wins";
        String X_TURN = "X Turn";
        String Y_TURN = "Y Turn";
        String DRAW = "draw";
    }

    interface Winner{
        String X = "x";
        String Y = "y";
        String DRAW = "draw";
    }
    interface Welcome{
        String WELCOME_MESSAGE = "Welcome to tik tac toe, X will take first turn";
    }
}
