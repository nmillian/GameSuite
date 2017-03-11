package com.example.nicole.gamesuite;

import java.util.Random;

/**
 * Created by Nicole on 3/10/2017.
 */

public class BattleshipComputer {

    private String lastHitTile;
    private String lastHitType;

    public BattleshipComputer(){
        lastHitTile = "00";
        lastHitType = "NONE";
    }

    public String PlayGame(BattleshipBoard board){
        String tile = RandomMove(board);
        return tile;
    }

    private String RandomMove(BattleshipBoard board){
        int row;
        int column;
        Random rand = new Random();

        //Pick a random tile to hit for now
        row = 1 + rand.nextInt((8 - 1) + 1);
        column = 1 + rand.nextInt((8 - 1) + 1);

        String tile = Integer.toString(row) + Integer.toString(column);

        return tile;
    }
}
