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
`   * Public class variables
    ********************************************* */
    public boolean choiceValid(String row, String column){
        String tile = row + column;
        Log.d("VALID", connect4Board.get(tile));

        if(connect4Board.get(tile).equals("B")){
            return true;
        }
        else{
            return false;
        }
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
