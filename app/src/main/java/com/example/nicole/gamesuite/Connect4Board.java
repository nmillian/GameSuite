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
    public Connect4Board(){
        //Initialize the Hashtable with the defualt values at default locations
        connect4Board.clear();
        InitializeBoard();
    }

    /* *********************************************
`   * Private class functions
    ********************************************* */
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

    //Check that the placement is empty
    public boolean ChoiceValid(String row, String column){
        String tile = row + column;

        if(connect4Board.get(tile).equals("B")){
            return true;
        }
        else{
            return false;
        }
    }

    //Validate that the tile is empty and if it's not on the first row there's a piece underneath
    public boolean ValidateMove(String row, String column){
        Integer rowInt = Integer.parseInt(row);
        Integer colInt = Integer.parseInt(column);

        //Check that the placement is empty
        if(ChoiceValid(row, column)){

            //Check if it's being placed on the first row
            if(rowInt.equals(1)){
                return true;
            }

            //Not the first row, check that the tile below is filled
            else{
                Integer newRowInt = rowInt - 1;
                String underTile = Integer.toString(newRowInt) + column;

                if(connect4Board.get(underTile).equals("C") || connect4Board.get(underTile).equals("H")){
                    return true;
                }
            }
        }

        //Return false if player did not pick a blank tile or the tile under is not filled
        return false;
    }

    //Check if the huamn player won the game
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
    
    public void UpdateHumanMove(String row, String column){
        String tile = row + column;
        connect4Board.put(tile, "H");
    }

    public void UpdateComputerMove(String row, String column){
        String tile = row + column;
        connect4Board.put(tile, "C");
    }

    public void UpdateBlankMove(String row, String column){
        String tile = row + column;
        connect4Board.put(tile, "B");
    }

    public String GetValueAtTile(String row, String column){
        String tile = row + column;

        return connect4Board.get(tile);
    }

}
