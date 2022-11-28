package com.tictaktoe.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameApiController {

    private boolean isXturn = true;
    private int  turnNumber = 0;

    @GetMapping(path = "/start", produces = "*/*")
    public String start() {
        isXturn = true;
        turnNumber = 0;
        return "Welcome to tik tac toe, X will take first turn";
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
