package com.example.nicole.gamesuite;

import android.util.Log;

import java.util.Random;

/**
 * Created by Nicole on 2/10/2017.
 */

public class Connect4Computer {

    /*
    public String DecideMove(Connect4Board a_board ){

    }
*/

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
                    return tile;
                }

            }
        }

        return noMove;
    }
}
