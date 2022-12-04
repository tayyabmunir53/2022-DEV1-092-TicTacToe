package com.tictaktoe.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameApiController {

    private boolean isXturn = true;
    private int  turnNumber = 0;
    private static final Logger logger = LoggerFactory.getLogger(GameApiController.class);

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
    //?turn=1
    public TurnResponse playTurn(@RequestParam(value = "turn", defaultValue = "0") String turn) {

        TurnResponse turnResponse = new TurnResponse();
        turnResponse.setResponseCode("00");

        logger.info("Request Landed");
        logger.info("turn Value is:" + turn);
        //
        try {
            Utility.turn(board, isXturn ? "X" : "Y", Integer.parseInt(turn));
        } catch (InternalError e){

            turnResponse.setResponseCode("04");
            turnResponse.setResponseDescription("This Slot is already Taken, please pick another Slot");
            turnResponse.setOutputBoard(Utility.printBoard(board));
            turnResponse.setTurnNumber(String.valueOf(turnNumber));
            return turnResponse;
        }
        //
        String winner = null;
        if(turnNumber >= 4){
        winner =  Utility.checkWinner(board);
        }

        if (winner != null) {
            if (winner.equalsIgnoreCase("x")) {
                turnResponse.setOutputBoard(Utility.printBoard(board));
                turnResponse.setResponseCode("02");
                turnResponse.setResponseDescription("X Wins");
                return  turnResponse;
            } else if (winner.equalsIgnoreCase("y")) {
                turnResponse.setOutputBoard(Utility.printBoard(board));
                turnResponse.setResponseCode("03");
                turnResponse.setResponseDescription("Y Wins");
                return  turnResponse;
            } else if (winner.equalsIgnoreCase("draw")) {
                turnResponse.setOutputBoard(Utility.printBoard(board));
                turnResponse.setResponseCode("01");
                turnResponse.setResponseDescription("draw");
                return  turnResponse;
            }
        }



        turnResponse.setOutputBoard(Utility.printBoard(board));
        turnResponse.setTurnNumber(String.valueOf(turnNumber+= 1));
        if (isXturn){
            isXturn = false;
            turnResponse.setResponseDescription("Y Turn");
        }
        else{
            isXturn = true;
            turnResponse.setResponseDescription("X Turn");
        }
        return turnResponse;
    }

}
