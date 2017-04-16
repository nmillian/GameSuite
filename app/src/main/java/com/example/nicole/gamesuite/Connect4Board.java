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
`   * Private functions
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

    /**
     * Name:
     * ChoiceValid
     *
     * Synopsis:
     * public boolean ChoiceValid(String a_row, String a_column)
     * @param a_row -> The row the tile is located at.
     * @param a_column -> The column the tile is located at.
     *
     * Description:
     * Used in order to validate if a piece can be placed on the tile, aka it's empty.
     *
     * Returns:
     * @return Boolean, true if the piece can be placed, false if the piece can't be placed.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    public boolean ChoiceValid(String a_row, String a_column){
        String tile = a_row + a_column;

        if(connect4Board.get(tile).equals("B")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Name:
     * ValidateMove
     *
     * Synopsis:
     * public boolean ValidateMove(String a_row, String a_column);
     * @param a_row -> The row the tile is located at.
     * @param a_column -> The column the tile is located at.
     *
     * Description:
     * Validate that the tile is empty and if it's not on the first row there's a piece underneath.
     *
     * Returns:
     * @return Boolean, true if the piece can be placed, false if the piece can't be placed.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
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

    /**
     * Name:
     * UpdateHumanMove
     *
     * Synopsis:
     * public void UpdateHumanMove(String a_row, String a_column);
     * @param a_row -> The row the tile is located at.
     * @param a_column -> The column the tile is located at.
     *
     * Description:
     * Used in order to update the tile in the connect4Board hashtable, updates the tile from a B to H.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    public void UpdateHumanMove(String a_row, String a_column){
        String tile = a_row + a_column;
        connect4Board.put(tile, "H");
    }

    /**
     * Name:
     * UpdateComputerMove
     *
     * Synopsis:
     * public void UpdateComputerMove(String a_row, String a_column);
     * @param a_row -> The row the tile is located at.
     * @param a_column -> The column the tile is located at.
     *
     * Description:
     * Used in order to update the tile in the connect4Board hashtable, updates the tile from a B to C.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    public void UpdateComputerMove(String a_row, String a_column){
        String tile = a_row + a_column;
        connect4Board.put(tile, "C");
    }

    /**
     * Name:
     * UpdateBlankMove
     *
     * Synopsis:
     * public void UpdateBlankMove(String a_row, String a_column);
     * @param a_row -> The row the tile is located at.
     * @param a_column-> The column the tile is located at.
     *
     * Description:
     * Used in order to update the connect4Board hashtable with a blank value, B.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    public void UpdateBlankMove(String a_row, String a_column){
        String tile = a_row + a_column;
        connect4Board.put(tile, "B");
    }

    /**
     * Name:
     * GetValueAtTile
     *
     * Synopsis:
     * public String GetValueAtTile(String a_row, String a_column);
     * @param a_row -> The row the tile is located at.
     * @param a_column -> The column the tile is located at.
     *
     * Description:
     * Used in order to get the type of the tile at a specified location.
     *
     * Returns:
     * @return String, the value located at the tile, either a B for blank, C for computer, or H for human.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    public String GetValueAtTile(String a_row, String a_column){
        String tile = a_row + a_column;
        return connect4Board.get(tile);
    }

    /**
     * Name:
     * GetValueUsingTile
     *
     * Synopsis:
     * public String GetValueUsingTile(String a_tile);
     * @param a_tile -> The tile to get the value at.
     *
     * Description:
     * Used in order to get the value at a specified tile.
     *
     * Returns:
     * @return String, the value at the specified tile, either H for human or C for computer.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
    public String GetValueUsingTile(String a_tile){
        return connect4Board.get(a_tile);
    }

    /**
     * Name:
     * CheckForWinHuman
     *
     * Synopsis:
     * public boolean CheckForWinHuman();
     * No params.
     *
     * Description:
     * Used in order to check if the human won by getting four in a row.
     *
     * Returns:
     * @return Boolean, true if the human won, false if the human did not win.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
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

    /**
     * Name:
     * CheckForWinComputer
     *
     * Synopsis:
     * public boolean CheckForWinComputer();
     * No params.
     *
     * Description:
     * Checks if the computer player won the game by getting four in a row.
     *
     * Returns:
     * @return Boolean, true if the computer won, false if the computer did not win.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/3/2017
     */
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
     * GetNumberOfEmptyTiles
     *
     * Synopsis:
     * public Integer GetNumberOfEmptyTiles();
     * No params.
     *
     * Description:
     * Used in order to check how many blank spaces are left on the board. If the board has no more
     * blanks and no one has won, the game should end in a draw.
     *
     * Returns:
     * @return Integer, the number of blank tiles remaining on the board.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/23/2017
     */
    public Integer GetNumberOfEmptyTiles(){
        Integer count = 0;

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

                if(connect4Board.get(tile).equals("B")){
                    count++;
                }
            }
        }

        return count;
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
