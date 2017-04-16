package com.example.nicole.gamesuite;

import android.util.Log;

import java.util.Random;

/**
 * Created by Nicole on 2/10/2017.
 */

public class Connect4Computer {

    /* *********************************************
`   * Public functions
    ********************************************* */
    public String DecideMove(Connect4Board a_board ){
        String checkWin;
        String checkRandom;
        String checkForBlockWinning;

        checkWin = MakeWinningMove(a_board);
        checkRandom = DecideRandomMove(a_board);
        checkForBlockWinning = BlockWinningMove(a_board);

        if(!checkWin.equals("none")){
            return checkWin;
        }

        if(!checkForBlockWinning.equals("none")){
            return checkForBlockWinning;
        }

        if(!checkRandom.equals("none")){
            return checkRandom;
        }

        return "none";
    }

    /* *********************************************
`   * Private functions
    ********************************************* */
    private String MakeWinningMove(Connect4Board a_board){
        //Check for three in a row

        String noMove = "none";

        //Check horizontal win
        //Row
        for(int i = 1; i < 7; i++) {
            //Column - only have to check to the middle column
            for(int j = 1; j < 5; j++){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                String tileOne = rowString + Integer.toString(j);
                String tileTwo = rowString + Integer.toString(j+1);
                String tileThree = rowString + Integer.toString(j+2);

                //This is the tile to place
                String tileFour = rowString + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j+3))){
                        return tileFour;
                    }
                }
            }
        }

        //Check vertical win
        //Column
        for(int i = 1; i < 8; i++){
            //Row - only have to check until the middle row
            for(int j = 1; j < 4; j++){
                //Column stays the same, column moves up until it gets to the middle, 3
                String columnString = Integer.toString(i);

                String tileOne = Integer.toString(j) + columnString;
                String tileTwo = Integer.toString(j + 1) + columnString;
                String tileThree = Integer.toString(j + 2) + columnString;

                //This is the tile to place
                String tileFour = Integer.toString(j + 3) + columnString;

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(j + 3), columnString)){
                        return tileFour;
                    }
                }
            }

        }

        //Check diagonal win up
        //Row
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);

                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(i+3), Integer.toString(j+3))){
                        return tileFour;
                    }
                }
            }
        }

        //Check diagonal win down
        //Row
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);
                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);

                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(i-3), Integer.toString(j+3))){
                        return tileFour;
                    }
                }
            }
        }

        //No way to win
        return noMove;

    }

    private String BlockWinningMove(Connect4Board a_board){
        //Check for three in a row
        String noMove = "none";

        //Check horizontal win
        //Row
        for(int i = 1; i < 7; i++) {
            //Column - only have to check to the middle column
            for(int j = 1; j < 5; j++){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                String tileOne = rowString + Integer.toString(j);
                String tileTwo = rowString + Integer.toString(j+1);
                String tileThree = rowString + Integer.toString(j+2);

                //This is the tile to place
                String tileFour = rowString + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileTwo).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j+3))){
                        return tileFour;
                    }
                }
            }
        }

        //Check vertical win
        //Column
        for(int i = 1; i < 8; i++){
            //Row - only have to check until the middle row
            for(int j = 1; j < 4; j++){
                //Column stays the same, column moves up until it gets to the middle, 3
                String columnString = Integer.toString(i);

                String tileOne = Integer.toString(j) + columnString;
                String tileTwo = Integer.toString(j + 1) + columnString;
                String tileThree = Integer.toString(j + 2) + columnString;

                //This is the tile to place
                String tileFour = Integer.toString(j + 3) + columnString;

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileTwo).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(j + 3), columnString)){
                        return tileFour;
                    }
                }
            }

        }

        //Check diagonal win up
        //Row
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);

                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileTwo).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(i+3), Integer.toString(j+3))){
                        return tileFour;
                    }
                }
            }
        }

        //Check diagonal win down
        //Row
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);
                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);

                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileTwo).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(i-3), Integer.toString(j+3))){
                        return tileFour;
                    }
                }
            }
        }

        //No way to win
        return noMove;

    }

    /**
     * Name:
     * DecideRandomMove
     *
     * Synopsis:
     * public String DecideRandomMove(Connect4Board a_board);
     * @param a_board -> The board object, used in order to keep track of the piece at each tile.
     *
     * Description:
     * Used in order to decide a random piece to place on the board for the computer.
     *
     * Returns:
     * @return
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    private String DecideRandomMove(Connect4Board a_board){
        String noMove = "none";

        //Row
        for(int i = 1; i < 8; i++){
            //Column
            for(int j = 1; j < 8; j++){

                String row = Integer.toString(i);
                String column = Integer.toString(j);

                String tile = row  + column;

                if(a_board.ValidateMove(row, column)){
                    return tile;
                }

            }
        }

        return noMove;
    }
}
