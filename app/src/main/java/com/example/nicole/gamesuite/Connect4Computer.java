package com.example.nicole.gamesuite;

import android.util.Log;

import java.util.Random;

/**
 * Created by Nicole on 2/10/2017.
 */

public class Connect4Computer {

    /*
    public String DecideMove(){

    }
*/
    public String DecideRandomMove(Connect4Board a_board){
        String noMove = "none";

        //Row
        for(int i = 1; i < 8; i++){
            //Column
            for(int j = 1; j < 8; j++){

                String row = Integer.toString(i);
                String column = Integer.toString(j);

                String tile = row  + column;

                if(a_board.ValidateMove(row, column)){
                    Log.d("CTILE", tile);
                    return tile;
                }

            }
        }

        return noMove;
    }
}
