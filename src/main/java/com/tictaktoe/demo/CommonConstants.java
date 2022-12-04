package com.tictaktoe.demo;

public interface CommonConstants {
    interface ResponseDescriptions{
        String X_WINS = "X Wins";
        String Y_WINS = "Y Wins";
        String X_TURN = "X Turn";
        String Y_TURN = "Y Turn";
        String DRAW = "draw";
        String INVALID_INPUT = "Please Enter a valid input between 1 to 9";
        String SLOT_ALREADY_TAKEN = "This Slot is already Taken, please pick another Slot";
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
