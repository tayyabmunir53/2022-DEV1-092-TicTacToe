package com.tictaktoe.demo;

import org.springframework.context.annotation.Description;

public class TurnResponse {

    public TurnResponse() {
    }

    public TurnResponse(String outputBoard, String responseCode, String responseDescription) {
        this.outputBoard = outputBoard;
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
    }

    private String outputBoard;
    private String responseCode;
    private String responseDescription;
    private String turnNumber;

    public String getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(String turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void setOutputBoard(String outputBoard) {
        this.outputBoard = outputBoard;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getOutputBoard() {
        return outputBoard;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }
}
