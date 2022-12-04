package com.tictaktoe.demo;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.tictaktoe.demo.CommonConstants.Welcome.WELCOME_MESSAGE;

@RestController
public class GameApiController {

    private boolean isXturn = true;
    private boolean isDecided = false;
    private int  turnNumber = 0;
    private static final Logger logger = LoggerFactory.getLogger(GameApiController.class);
    private final Pattern patternSingleDigitNumeric = Pattern.compile("^[1-9]{1}$");

    String[] board = new String[9];
    private String turn;
    //
    @Description(value = "this API wil set/reset the board")
    @GetMapping(path = "/start", produces = "*/*")
    public String start() {
        isXturn = true;
        turnNumber = 0;

        Utility.resetBoard(this.board);

        Utility.printBoard(this.board, turn);

        StringBuilder stringBuilder = new StringBuilder(2048);
        stringBuilder.append(WELCOME_MESSAGE);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(Utility.printBoard(board));
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }


    @GetMapping(path = "/playturn",  produces = "application/json")
    //?turn=1
    public TurnResponse playTurn(@RequestParam(value = "turn", defaultValue = "0") String turn) {

        TurnResponse turnResponse = new TurnResponse();
        turnResponse.setResponseCode(ResponseCode.OK);
        if(turnNumber == 0){
            Utility.resetBoard(this.board);
        }
        logger.info("Request Landed");
        logger.info("turn Value is:" + turn);
        //
        if(!Pattern.matches(patternSingleDigitNumeric.toString(), turn)){
            turnResponse.setResponseCode(ResponseCode.INVALID_INPUT);
            turnResponse.setResponseDescription(CommonConstants.ResponseDescriptions.INVALID_INPUT);
            turnResponse.setOutputBoard(Utility.printBoard(board));
            turnResponse.setTurnNumber(String.valueOf(turnNumber));
            return turnResponse;
        }
        //
        try {
            Utility.turn(board, isXturn ? "X" : "Y", Integer.parseInt(turn));
        } catch (InternalError e){

            turnResponse.setResponseCode(ResponseCode.INTERNAL_ERROR);
            turnResponse.setResponseDescription(CommonConstants.ResponseDescriptions.SLOT_ALREADY_TAKEN);
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
            if (winner.equalsIgnoreCase(CommonConstants.Winner.X)) {
                turnResponse.setOutputBoard(Utility.printBoard(board));
                turnResponse.setResponseCode(ResponseCode.X_WINS);
                turnResponse.setResponseDescription(CommonConstants.ResponseDescriptions.X_WINS);
                isDecided = true;
                turnNumber = 0;
                return  turnResponse;
            } else if (winner.equalsIgnoreCase(CommonConstants.Winner.Y)) {
                turnResponse.setOutputBoard(Utility.printBoard(board));
                turnResponse.setResponseCode(ResponseCode.Y_WINS);
                turnResponse.setResponseDescription(CommonConstants.ResponseDescriptions.Y_WINS);
                isDecided = true;
                turnNumber = 0;
                return  turnResponse;
            } else if (winner.equalsIgnoreCase(CommonConstants.Winner.DRAW)) {
                turnResponse.setOutputBoard(Utility.printBoard(board));
                turnResponse.setResponseCode(ResponseCode.DRAW);
                turnResponse.setResponseDescription(CommonConstants.ResponseDescriptions.DRAW);
                isDecided = true;
                turnNumber = 0;
                return  turnResponse;
            }
        }



        turnResponse.setOutputBoard(Utility.printBoard(board));
        turnResponse.setTurnNumber(String.valueOf(turnNumber+= 1));
        if (isXturn){
            isXturn = false;
            turnResponse.setResponseDescription(CommonConstants.ResponseDescriptions.Y_TURN);
        }
        else{
            isXturn = true;
            turnResponse.setResponseDescription(CommonConstants.ResponseDescriptions.X_TURN);
        }
        return turnResponse;
    }

}
