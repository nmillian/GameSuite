package com.example.nicole.gamesuite;

import android.util.Log;

import java.util.Hashtable;

/**
 * Created by Nicole on 2/7/2017.
 */

public class Connect4Board {
    /* *********************************************
`   * Private class variables
    ********************************************* */
    //Hashtable to hold the the type of piece located at each space
    //B for blank, H for human, C for computer
    private static Hashtable<String, String> connect4Board = new Hashtable<String, String>();

    /* *********************************************
 `   * Constructor
     ********************************************* */

    /**
     * Name:
     * Connect4Board()
     *
     * Synopsis:
     * public Connect4Board();
     * No params.
     *
     * Description:
     * This function is the constructor for the board class.
     * Used in order to initialized the board to all blanks.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    public Connect4Board(){
        //Initialize the Hashtable with the defualt values at default locations
        connect4Board.clear();
        InitializeBoard();
    }

    /* *********************************************
`   * Private class functions
    ********************************************* */

    /**
     * Name:
     * InitializeBoard
     *
     * Synopsis:
     * private void InitializeBoard()
     * No params.
     *
     * Description:
     * This function is used in order to initialize the default values in the Hashtable connect4Board to B for blank.
     * Used in order to initialized the board to all blanks.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    private void InitializeBoard(){
        connect4Board.clear();

        String row;
        String column;
        String tile;

        //Row
        for(int i = 1; i < 7; i++ ){
            //Column
            for(int j = 1; j < 8; j++){
                row = String.valueOf(i);
                column = String.valueOf(j);

                tile = row + column;

                //All the tiles are originally blank
                connect4Board.put(tile, "B");
            }
        }
    }

    /* *********************************************
`   * Public functions
    ********************************************* */


    public boolean ChoiceValid(String a_row, String a_column){
        String tile = a_row + a_column;

        if(connect4Board.get(tile).equals("B")){
            return true;
        }
        else{
            return false;
        }
    }

    //Validate that the tile is empty and if it's not on the first row there's a piece underneath
    public boolean ValidateMove(String a_row, String a_column){
        Integer rowInt = Integer.parseInt(a_row);
        Integer colInt = Integer.parseInt(a_column);

        //Check that the placement is empty
        if(ChoiceValid(a_row, a_column)){

            //Check if it's being placed on the first row
            if(rowInt.equals(1)){
                return true;
            }

            //Not the first row, check that the tile below is filled
            else{
                Integer newRowInt = rowInt - 1;
                String underTile = Integer.toString(newRowInt) + a_column;

                if(connect4Board.get(underTile).equals("C") || connect4Board.get(underTile).equals("H")){
                    return true;
                }
            }
        }

        //Return false if player did not pick a blank tile or the tile under is not filled
        return false;
    }


    public void UpdateHumanMove(String a_row, String a_column){
        String tile = a_row + a_column;
        connect4Board.put(tile, "H");
    }

    public void UpdateComputerMove(String a_row, String a_column){
        String tile = a_row + a_column;
        connect4Board.put(tile, "C");
    }

    public void UpdateBlankMove(String a_row, String a_column){
        String tile = a_row + a_column;
        connect4Board.put(tile, "B");
    }

    public String GetValueAtTile(String a_row, String a_column){
        String tile = a_row + a_column;
        return connect4Board.get(tile);
    }

    //Check if the human player won the game
    public boolean CheckForWinHuman(){
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
                String tileFour = rowString + Integer.toString(j+3);

                if(connect4Board.get(tileOne).equals("H") && connect4Board.get(tileTwo).equals("H") && connect4Board.get(tileThree).equals("H") && connect4Board.get(tileFour).equals("H")){
                    return true;
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
                String tileFour = Integer.toString(j + 3) + columnString;

                if(connect4Board.get(tileOne).equals("H") && connect4Board.get(tileTwo).equals("H") && connect4Board.get(tileThree).equals("H") && connect4Board.get(tileFour).equals("H")){
                    return true;
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

                if(connect4Board.get(tileOne).equals("H") && connect4Board.get(tileTwo).equals("H") && connect4Board.get(tileThree).equals("H") && connect4Board.get(tileFour).equals("H")){
                    return true;
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


                if(connect4Board.get(tileOne).equals("H") && connect4Board.get(tileTwo).equals("H") && connect4Board.get(tileThree).equals("H") && connect4Board.get(tileFour).equals("H")){
                    return true;
                }
            }
        }

        //No way to win
        return false;
    }

    //Check if the computer player won the game
    public boolean CheckForWinComputer(){
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
                String tileFour = rowString + Integer.toString(j+3);

                if(connect4Board.get(tileOne).equals("C") && connect4Board.get(tileTwo).equals("C") && connect4Board.get(tileThree).equals("C") && connect4Board.get(tileFour).equals("C")){
                    return true;
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
                String tileFour = Integer.toString(j + 3) + columnString;

                if(connect4Board.get(tileOne).equals("C") && connect4Board.get(tileTwo).equals("C") && connect4Board.get(tileThree).equals("C") && connect4Board.get(tileFour).equals("C")){
                    return true;
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

                if(connect4Board.get(tileOne).equals("C") && connect4Board.get(tileTwo).equals("C") && connect4Board.get(tileThree).equals("C") && connect4Board.get(tileFour).equals("C")){
                    return true;
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


                if(connect4Board.get(tileOne).equals("C") && connect4Board.get(tileTwo).equals("C") && connect4Board.get(tileThree).equals("C") && connect4Board.get(tileFour).equals("C")){
                    return true;
                }
            }
        }

        //No way to win
        return false;
    }

    /**
     * Name:
     * ResetBoard
     *
     * Synopsis:
     * public void ResetBoard();
     * No params.
     *
     * Description:
     * This function is used in order to reset the default values in the Hashtable connect4Board to B for blank.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    public void ResetBoard(){
        connect4Board.clear();

        String row;
        String column;
        String tile;

        //Row
        for(int i = 1; i < 7; i++ ){
            //Column
            for(int j = 1; j < 8; j++){
                row = String.valueOf(i);
                column = String.valueOf(j);

                tile = row + column;

                //All the tiles are originally blank
                connect4Board.put(tile, "B");
            }
        }
    }

}
