package com.example.nicole.gamesuite;

import java.util.Random;

/**
 * Created by Nicole on 3/10/2017.
 */

public class BattleshipComputer {

    private String lastHitTile;
    private String lastHitType;

    private String shipTile;

    private String state;
    private String direction = "NONE";

    private String up = "00";
    private String down = "00";
    private String left = "00";
    private String right = "00";

    private int incrementRow = 0;
    private int incrementColumn = 0;

    /* *********************************************
`   * Constructor
    ********************************************* */

    /**
     * Name:
     * BattleshipComputer()
     *
     * Synopsis:
     * public BattleshipComputer()
     * No params.
     *
     * Description:
     * This function is the constructor for the computer class.
     * Used in order to initialize all variables to their default state.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/10/2017
     */
    public BattleshipComputer(){
        state = "search";

        lastHitTile = "00";
        lastHitType = "NONE";

        shipTile = "00";
    }

    /* *********************************************
`   * Public functions
    ********************************************* */

    /**
     * Name:
     * PlayGame
     *
     * Synopsis:
     * public String PlayGame(BattleshipBoard board);
     * @param board -> The board used in order to play the game.
     *
     * Description:
     * Used in order to determine which tile the computer player should attack.
     *
     * Return:
     * @return String, the tile the computer player decided to attack.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/10/2017
     */
    public String PlayGame(BattleshipBoard board){
        String tile = "00";

        //char row = tile.charAt(0);
        //char column = tile.charAt(1);

        //int rowInt = Character.getNumericValue(row);
        //int columnInt = Character.getNumericValue(column);

        if(state.equals("search")){
            tile = RandomMove(board);

            lastHitTile = tile;
            lastHitType = board.GetPieceAtSpaceHuman(tile);

            if(lastHitType.equals("S")){
                state = "attack";
                GenerateTilesToCheck();
            }


        }

        else if(state.equals("attack")){

            if(direction.equals("NONE")){
                //Check every direction
                //If direction is wrong mark it as 00

                if(!up.equals("00")){
                    tile = up;
                    lastHitTile = up;
                    if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                        direction = "up";
                    }
                    else{
                        up = "00";
                    }
                }

                else if(!down.equals("00")){
                    tile = down;
                    lastHitTile = down;
                    if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                        direction = "down";
                    }
                    else{
                        down = "00";
                    }
                }

                else if(!left.equals("00")){
                    tile = left;
                    lastHitTile = left;
                    if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                        direction = "left";
                    }
                    else{
                        left = "00";
                    }
                }

                else if(!right.equals("00")){
                    tile = right;
                    lastHitTile = right;
                    if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                        direction = "right";
                    }
                    else{
                        right = "00";
                    }
                }

                else{
                    //Reached the end
                    ResetTilesToCheck();
                    tile = RandomMove(board);

                    lastHitTile = tile;
                    lastHitType = board.GetPieceAtSpaceHuman(tile);

                    if(lastHitType.equals("S")){
                        state = "attack";
                        GenerateTilesToCheck();
                    }
                }

            }

            else{

                char row;
                char column;

                //increment up
                int rowInt;
                int columnInt;

                if(direction.equals("up")){
                    row = up.charAt(0);
                    column = up.charAt(1);

                    //increment up
                    rowInt = Character.getNumericValue(row);
                    columnInt = Character.getNumericValue(column);

                    if(incrementRow == 0) {
                        incrementRow = rowInt + 1;
                    }
                    else{
                        incrementRow = incrementRow + 1;
                    }

                    if(incrementRow != 9) {
                        //Increment
                        tile = Integer.toString(incrementRow) + column;
                        lastHitTile = tile;

                        //If last tile hit was a space stop checking
                        if(board.GetPieceAtSpaceHuman(lastHitTile).equals("B") || incrementRow + 1 == 9){
                            direction = "NONE";
                            up = "00";
                        }
                    }

                    else{
                        //Goes back to check the rest of the tiles afterwards
                        //Hits a random tile then goes back

                        direction = "NONE";
                        up = "00";

                        //Reached the end
                        tile = RandomMove(board);

                        lastHitTile = tile;
                        lastHitType = board.GetPieceAtSpaceHuman(tile);

                        if(lastHitType.equals("S")){
                            state = "attack";
                            GenerateTilesToCheck();
                        }

                    }
                }

                else if(direction.equals("down")){
                    row = down.charAt(0);
                    column = down.charAt(1);

                    //increment up
                    rowInt = Character.getNumericValue(row);
                    columnInt = Character.getNumericValue(column);

                    if(incrementRow == 0) {
                        incrementRow = rowInt - 1;
                    }
                    else{
                        incrementRow = incrementRow - 1;
                    }

                    if(incrementRow != 0) {
                        //Increment
                        tile = Integer.toString(incrementRow) + column;
                        lastHitTile = tile;

                        //If last tile hit was a space stop checking
                        if(board.GetPieceAtSpaceHuman(lastHitTile).equals("B") || incrementRow - 1 == 0){
                            direction = "NONE";
                            down = "00";
                        }
                    }

                    else{
                        //Reached the end
                        direction = "NONE";
                        down = "00";

                        //Reached the end
                        tile = RandomMove(board);

                        lastHitTile = tile;
                        lastHitType = board.GetPieceAtSpaceHuman(tile);

                        if(lastHitType.equals("S")){
                            state = "attack";
                            GenerateTilesToCheck();
                        }
                    }
                }

                else if(direction.equals("right")){
                    row = right.charAt(0);
                    column = right.charAt(1);

                    //increment up
                    rowInt = Character.getNumericValue(row);
                    columnInt = Character.getNumericValue(column);

                    if(incrementColumn == 0) {
                        incrementColumn = columnInt + 1;
                    }
                    else{
                        incrementColumn = incrementColumn + 1;
                    }

                    if(incrementColumn != 9) {
                        //Increment
                        tile = row + Integer.toString(incrementColumn);
                        lastHitTile = tile;

                        //If last tile hit was a space stop checking
                        if(board.GetPieceAtSpaceHuman(lastHitTile).equals("B") || incrementColumn + 1 == 9){
                            direction = "NONE";
                            right = "00";
                        }
                    }

                    else{
                        //Reached the end
                        direction = "NONE";
                        right = "00";

                        //Reached the end
                        tile = RandomMove(board);

                        lastHitTile = tile;
                        lastHitType = board.GetPieceAtSpaceHuman(tile);

                        if(lastHitType.equals("S")){
                            state = "attack";
                            GenerateTilesToCheck();
                        }
                    }
                }

                else if(direction.equals("left")){
                    row = left.charAt(0);
                    column = left.charAt(1);

                    //increment up
                    rowInt = Character.getNumericValue(row);
                    columnInt = Character.getNumericValue(column);

                    if(incrementColumn == 0) {
                        incrementColumn = columnInt - 1;
                    }
                    else{
                        incrementColumn = incrementColumn - 1;
                    }

                    if(incrementColumn != 0) {
                        //Increment
                        tile = row + Integer.toString(incrementColumn);
                        lastHitTile = tile;

                        //If last tile hit was a space stop checking
                        if(board.GetPieceAtSpaceHuman(lastHitTile).equals("B") || incrementColumn - 1 == 0){
                            direction = "NONE";
                            left = "00";
                        }
                    }

                    else{
                        //Reached the end
                        direction = "NONE";
                        left = "00";

                        //Reached the end
                        tile = RandomMove(board);

                        lastHitTile = tile;
                        lastHitType = board.GetPieceAtSpaceHuman(tile);

                        if(lastHitType.equals("S")){
                            state = "attack";
                            GenerateTilesToCheck();
                        }
                    }
                }
            }
        }

        System.out.print("TILE FOR COMP " + tile + "\n");
        return tile;

    }

    /* *********************************************
`   * Private functions
    ********************************************* */

    /**
     * Name:
     * ResetTilesToCheck
     *
     * Synopsis:
     * private void ResetTilesToCheck();
     *
     * Description:
     * Reset which tiles need to be checked in order to ensure that a ship was hit completely.
     *
     * Return:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/11/2017
     */
    private void ResetTilesToCheck(){
        direction = "NONE";
        state = "search";

        up = "00";
        down = "00";
        left = "00";
        right = "00";

        incrementRow = 0;
        incrementColumn = 0;
    }

    /**
     * Name:
     * GenerateTilesToCheck
     *
     * Synopsis:
     * private void GenerateTilesToCheck();
     *
     * Description:
     * Used in order to generate the next tile to hit when a ship piece has been hit.
     *
     * Return:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/11/2017
     */
    private void GenerateTilesToCheck(){
        char row = lastHitTile.charAt(0);
        char column = lastHitTile.charAt(1);

        int rowInt = Character.getNumericValue(row);
        int columnInt = Character.getNumericValue(column);

        int tempRow;
        int tempCol;

        tempRow = rowInt + 1;
        if(tempRow == 9 || tempRow == 0 ){
            up = "00";
        }
        else{
            up = Integer.toString(tempRow) + column;
        }

        tempRow = rowInt - 1;
        if(tempRow == 9 || tempRow == 0){
            down = "00";
        }
        else{
            down = Integer.toString(tempRow) + column;
        }

        tempCol = columnInt + 1;
        if(tempCol == 9 || tempCol == 0){
            right = "00";
        }
        else{
            right = row + Integer.toString(tempCol);
        }

        tempCol = columnInt - 1;
        if(tempCol == 9 || tempCol == 0){
            left = "00";
        }
        else{
            left = row + Integer.toString(tempCol);
        }


    }

    /**
     * Name:
     * RandomMove
     *
     * Synopsis:
     * private String RandomMove(BattleshipBoard board);
     * @param board -> The board used in order to keep track of pieces of the human and computer players.
     *
     * Description:
     * Used in order to pick a random tile to hit on the human board.
     *
     * Return:
     * @return String, the tile that will be hit.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/11/2017
     */
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
