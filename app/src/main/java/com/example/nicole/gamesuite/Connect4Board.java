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
    private static Hashtable<String, String> connect4Board = new Hashtable<String, String>();

    /* *********************************************
 `   * Constructor
     ********************************************* */
    public Connect4Board(){
        //Initialize the Hashtable with the defualt values at default locations
        connect4Board.clear();
        initializeBoard();
    }

    /* *********************************************
`   * Private class functions
    ********************************************* */
    private void initializeBoard(){
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

                Log.d("TILE", tile);

                //All the tiles are originally blank
                connect4Board.put(tile, "B");

                Log.d("BOARD", connect4Board.get(tile));
            }
        }
    }

    /* *********************************************
`   * Public functions
    ********************************************* */

    //Check that the placement is empty
    public boolean choiceValid(String row, String column){
        String tile = row + column;

        if(connect4Board.get(tile).equals("B")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean validateMove(String row, String column){
        Integer rowInt = Integer.parseInt(row);
        Integer colInt = Integer.parseInt(column);

        //Check that the placement is empty
        if(choiceValid(row, column)){

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

    public boolean checkForWinHuman(String row, String column){
        Integer rowInt = Integer.parseInt(row);
        Integer colInt = Integer.parseInt(column);

        return false;
    }

    public void updateHumanMove(String row, String column){
        String tile = row + column;
        connect4Board.put(tile, "H");
    }

    public void updateComputerMove(String row, String column){
        String tile = row + column;
        connect4Board.put(tile, "C");
    }


}
