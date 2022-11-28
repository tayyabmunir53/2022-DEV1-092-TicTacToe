package com.tictaktoe.demo;

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
