package com.tictaktoe.demo;

import java.util.Arrays;

public class Utility {

    // CheckWinner method will
    // decide the combination
    // of three box given below.
    static String checkWinner(String[] board) {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            //For X winner
            if (line.equals("XXX")) {
                return "X";
            }

            // For O winner
            else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }

        return null;
    }

    // To print out the board.
    /* |---|---|---|
       | 1 | 2 | 3 |
       |-----------|
       | 4 | 5 | 6 |
       |-----------|
       | 7 | 8 | 9 |
       |---|---|---|*/

    static void printBoard(String[] board, String turn) {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | "
                + board[1] + " | " + board[2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | "
                + board[4] + " | " + board[5]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | "
                + board[7] + " | " + board[8]
                + " |");
        System.out.println("|---|---|---|");
    }

    static String printBoard(final String[] board) {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("|---|---|---|");
        sb.append(System.lineSeparator());
        sb.append("| " + board[0] + " | "
                + board[1] + " | " + board[2]
                + " |");
        sb.append(System.lineSeparator());
        sb.append("|-----------|");
        sb.append(System.lineSeparator());
        sb.append("| " + board[3] + " | "
                + board[4] + " | " + board[5]
                + " |");
        sb.append(System.lineSeparator());
        sb.append("|-----------|");
        sb.append(System.lineSeparator());
        sb.append("| " + board[6] + " | "
                + board[7] + " | " + board[8]
                + " |");
        sb.append(System.lineSeparator());
        sb.append("|---|---|---|");
        return sb.toString();
    }

    static void turn(String[] board, String turn, int numInput) {
        if (board[numInput - 1].equals(
                String.valueOf(numInput))) {
            board[numInput - 1] = turn;

            if (turn.equals("X")) {
                turn = "O";
            } else {
                turn = "X";
            }
            printBoard(board, turn);
        } else {
            throw new InternalError("Turn Already Taken");
        }
    }

    static void resetBoard(final String[] board) {
        //Resetting board//
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
    }
}

