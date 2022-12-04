package com.tictaktoe.demo;

public enum ResponseCode {
    OK("00"), 
    DRAW("01"),
    X_WINS("02"),
    Y_WINS("03"),
    INTERNAL_ERROR("04");

    ResponseCode(String s) {
    }

}
