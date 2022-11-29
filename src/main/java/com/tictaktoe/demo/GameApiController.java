package com.tictaktoe.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameApiController {

    private boolean isXturn = true;
    private int  turnNumber = 0;

    String[] board = new String[9];
    private String turn;
    //

    @GetMapping(path = "/start", produces = "*/*")
    public String start() {
        isXturn = true;
        turnNumber = 0;

        //Resetting board//
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }

        Utility.printBoard(board, turn);

        StringBuilder stringBuilder = new StringBuilder(2048);
        stringBuilder.append("Welcome to tik tac toe, X will take first turn");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(Utility.printBoard(board));
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }


    @GetMapping(path = "/playturn",  produces = "application/json")
    public TurnResponse playTurn() {
        System.out.println("PostRequest Landed");
        TurnResponse turnResponse = new TurnResponse();
        turnResponse.setResponseCode("00");
        turnResponse.setResponseDescription("");
        turnResponse.setOutputBoard("----------------%%%%%%%%%%%%%%%---------------");
        if (isXturn){
            isXturn = false;
        }
        else isXturn = true;
        return turnResponse;
    }

}
