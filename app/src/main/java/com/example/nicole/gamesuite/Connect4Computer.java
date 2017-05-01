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

    /**
     * Name:
     * DecideMove
     *
     * Synopsis:
     * public String DecideMove(Connect4Board a_board );
     * @param a_board -> The board object, used in order to keep track of the piece at each tile.
     *
     * Description:
     * Used in order to decide which move is the best move the computer can make given the situation of the board.
     *
     * Returns:
     * @return -> String, the tile that the computer should place.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public String DecideMove(Connect4Board a_board ){
        String checkWin;
        String checkBasic;
        String checkThirdPiece;
        String checkSecondPiece;
        String checkForBlockWinning;

        checkWin = MakeWinningMove(a_board);
        checkBasic = DecideBasicMove(a_board);
        checkForBlockWinning = BlockWinningMove(a_board);
        checkThirdPiece = AddThirdPiece(a_board);
        checkSecondPiece = AddSecondPiece(a_board);

        if(!checkWin.equals("none")){
            return checkWin;
        }

        if(!checkForBlockWinning.equals("none")){
            return checkForBlockWinning;
        }

        if(!checkThirdPiece.equals("none")){
            return checkThirdPiece;
        }

        if(!checkSecondPiece.equals("none")){
            return checkSecondPiece;
        }

        if(!checkBasic.equals("none")){
            return checkBasic;
        }

        return "none";
    }

    /* *********************************************
`   * Private functions
    ********************************************* */

    /**
     * Name:
     * MakeWinningMove
     *
     * Synopsis:
     * private String MakeWinningMove(Connect4Board a_board);
     * @param a_board -> The board object, used in order to keep track of the piece at each tile.
     *
     * Description:
     * Used in order to determine if the computer player is able to make a winning move by placing the fourth piece of a line.
     *
     * Returns:
     * @return String, the tile to place the computer's piece, or none if there is no suitable move to be made.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 4/22/2017
     */
    private String MakeWinningMove(Connect4Board a_board){
        //Check for three in a row

        String noMove = "none";

        //Check horizontal win
        //Left to right
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

        //Right to left horizontal
        //Row
        for(int i = 1; i < 7; i++) {
            //Column
            for(int j = 7; j > 3; j--){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                String tileOne = rowString + Integer.toString(j);
                String tileTwo = rowString + Integer.toString(j-1);
                String tileThree = rowString + Integer.toString(j-2);

                //This is the tile to place
                String tileFour = rowString + Integer.toString(j-3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j-3))){
                        return tileFour;
                    }
                }
            }
        }

        //Check missing middle right
        for(int i = 1; i < 7; i++) {
            //Column
            for(int j = 7; j > 3; j--){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                //should be filled
                String tileOne = rowString + Integer.toString(j);

                //Missing piece to place
                String tileTwo = rowString + Integer.toString(j-1);

                //Should be filled
                String tileThree = rowString + Integer.toString(j-2);
                String tileFour = rowString + Integer.toString(j-3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C") && a_board.GetValueUsingTile(tileFour).equals("C")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j-1))){
                        return tileTwo;
                    }
                }
            }
        }


        //Check missing middle left
        for(int i = 1; i < 7; i++) {
            //Column
            for(int j = 7; j > 3; j--){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                //should be filled
                String tileOne = rowString + Integer.toString(j);
                String tileTwo = rowString + Integer.toString(j-1);

                //Missing piece
                String tileThree = rowString + Integer.toString(j-2);

                //Should be filled
                String tileFour = rowString + Integer.toString(j-3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("C")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j-2))){
                        return tileThree;
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

        //Diagonal up missing middle right(third piece)
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);

                //tile to place
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);

                //should be filled
                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(i+2), Integer.toString(j+2))){
                        return tileThree;
                    }
                }
            }
        }

        //Diagonal up missing middle left (second piece)
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                //Place
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);

                //filled
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileFour).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(i+1), Integer.toString(j+1))){
                        return tileTwo;
                    }
                }
            }
        }

        //Diagonal up missing first (first piece)
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                //missing
                String tileOne = Integer.toString(i) + Integer.toString(j);

                //filled
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(i), Integer.toString(j))){
                        return tileOne;
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

        //(TileTwo)
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);

                //Place
                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);

                //Filled
                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(i-2), Integer.toString(j+2))){
                        return tileThree;
                    }
                }
            }
        }

        //(TileThree)
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                //Place
                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);

                //Filled
                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C") && a_board.GetValueUsingTile(tileFour).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(i-1), Integer.toString(j+1))){
                        return tileTwo;
                    }
                }
            }
        }

        //(TileOne)
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                //Place
                String tileOne = Integer.toString(i) + Integer.toString(j);

                //Filled
                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);
                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileThree).equals("C") && a_board.GetValueUsingTile(tileFour).equals("C")){
                    if(a_board.ValidateMove(Integer.toString(i), Integer.toString(j))){
                        return tileOne;
                    }
                }
            }
        }

        //No way to win
        return noMove;

    }

    /**
     * Name:
     * AddThirdPiece
     *
     * Synopsis:
     * private String AddThirdPiece(Connect4Board a_board);
     * @param a_board -> The board object, used in order to keep track of the piece at each tile.
     *
     * Description:
     * Used in order to place a third connect 4 piece down when there are already two computer pieces lined up.
     *
     * Returns:
     * @return String, the tile to place the computer's piece, or none if there is no suitable move to be made.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 4/22/2017
     */
    private String AddThirdPiece(Connect4Board a_board){
        //Check for two in a row
        String noMove = "none";

        //Check horizontal
        //Row
        for(int i = 1; i < 7; i++) {
            //Column - only have to check to the middle column
            for(int j = 1; j < 5; j++){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                String tileOne = rowString + Integer.toString(j);
                String tileTwo = rowString + Integer.toString(j+1);

                //Tile to place
                String tileThree = rowString + Integer.toString(j+2);

                //Should be empty
                String tileFour = rowString + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j+2))){
                        return tileThree;
                    }
                }
            }
        }

        //Right to left horizontal
        //Row
        for(int i = 1; i < 7; i++) {
            //Column
            for(int j = 7; j > 3; j--){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                String tileOne = rowString + Integer.toString(j);
                String tileTwo = rowString + Integer.toString(j-1);

                //Tile to place
                String tileThree = rowString + Integer.toString(j-2);

                //should be blank
                String tileFour = rowString + Integer.toString(j-3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j-2))){
                        return tileThree;
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

                //Tile to place
                String tileThree = Integer.toString(j + 2) + columnString;

                //Blank
                String tileFour = Integer.toString(j + 3) + columnString;

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(Integer.toString(j + 2), columnString)){
                        return tileThree;
                    }
                }
            }

        }

        //Check diagonal
        //Row
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);

                //One to place
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);

                //Blank
                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(Integer.toString(i+2), Integer.toString(j+2))){
                        return tileThree;
                    }
                }
            }
        }

        //Check diagonal
        //Row
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);

                //Placing
                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);

                //Blank
                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileTwo).equals("C") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(Integer.toString(i-2), Integer.toString(j+2))){
                        return tileThree;
                    }
                }
            }
        }

        //No way to win
        return noMove;
    }

    /**
     * Name:
     * AddSecondPiece
     *
     * Synopsis:
     * private String AddSecondPiece(Connect4Board a_board);
     * @param a_board -> The board object, used in order to keep track of the piece at each tile.
     *
     * Description:
     * Used in order to place a second computer piece down when a single piece has already been placed.
     *
     * Returns:
     * @return String, the tile to place the computer's piece, or none if there is no suitable move to be made.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 4/22/2017
     */
    private String AddSecondPiece(Connect4Board a_board){
        String noMove = "none";

        //Check horizontal
        //Row
        for(int i = 1; i < 7; i++) {
            //Column - only have to check to the middle column
            for(int j = 1; j < 5; j++){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                //Filled
                String tileOne = rowString + Integer.toString(j);

                //Place
                String tileTwo = rowString + Integer.toString(j+1);

                //Blank
                String tileThree = rowString + Integer.toString(j+2);
                String tileFour = rowString + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileThree).equals("B") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j+1))){
                        return tileTwo;
                    }
                }
            }
        }

        //Right to left horizontal
        //Row
        for(int i = 1; i < 7; i++) {
            //Column
            for(int j = 7; j > 3; j--){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                //Filled
                String tileOne = rowString + Integer.toString(j);

                //Place
                String tileTwo = rowString + Integer.toString(j-1);

                //Blank
                String tileThree = rowString + Integer.toString(j-2);
                String tileFour = rowString + Integer.toString(j-3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileThree).equals("B") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j-1))){
                        return tileTwo;
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

                //Filled
                String tileOne = Integer.toString(j) + columnString;

                //Place
                String tileTwo = Integer.toString(j + 1) + columnString;

                //Blank
                String tileThree = Integer.toString(j + 2) + columnString;
                String tileFour = Integer.toString(j + 3) + columnString;

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileThree).equals("B") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(Integer.toString(j + 1), columnString)){
                        return tileTwo;
                    }
                }
            }

        }

        //Check diagonal win up
        //Row
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){

                //Filled
                String tileOne = Integer.toString(i) + Integer.toString(j);

                //Place
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);

                //Blanks
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileThree).equals("B") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(Integer.toString(i+1), Integer.toString(j+1))){
                        return tileTwo;
                    }
                }
            }
        }

        //Check diagonal win down
        //Row
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){

                //Filled
                String tileOne = Integer.toString(i) + Integer.toString(j);

                //Place
                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);

                //Blank
                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("C") && a_board.GetValueUsingTile(tileThree).equals("B") && a_board.GetValueUsingTile(tileFour).equals("B")){
                    if(a_board.ValidateMove(Integer.toString(i-1), Integer.toString(j+1))){
                        return tileTwo;
                    }
                }
            }
        }

        //No way to win
        return noMove;

    }

    /**
     * Name:
     * BlockWinningMove
     *
     * Synopsis:
     * private String BlockWinningMove(Connect4Board a_board);
     * @param a_board -> The board object, used in order to keep track of the piece at each tile.
     *
     * Description:
     * Used in order to block a human players winning move by placing a computer tile at the fourth slot if possible.
     *
     * Returns:
     * @return String, the tile to place the computer's piece, or none if there is no suitable move to be made.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 4/22/2017
     */
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

        //Right to left horizontal
        //Row
        for(int i = 1; i < 7; i++) {
            //Column
            for(int j = 7; j > 3; j--){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                String tileOne = rowString + Integer.toString(j);
                String tileTwo = rowString + Integer.toString(j-1);
                String tileThree = rowString + Integer.toString(j-2);

                //This is the tile to place
                String tileFour = rowString + Integer.toString(j-3);

                //System.out.println("TILES RIGHT TO LEFT " + tileOne + " " + tileTwo + " " + tileThree + " " + tileFour);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileTwo).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j-3))){
                        return tileFour;
                    }
                }
            }
        }

        //Check missing middle right
        for(int i = 1; i < 7; i++) {
            //Column
            for(int j = 7; j > 3; j--){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                //should be filled
                String tileOne = rowString + Integer.toString(j);

                //Missing piece to place
                String tileTwo = rowString + Integer.toString(j-1);

                //Should be filled
                String tileThree = rowString + Integer.toString(j-2);
                String tileFour = rowString + Integer.toString(j-3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H") && a_board.GetValueUsingTile(tileFour).equals("H")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j-1))){
                        return tileTwo;
                    }
                }
            }
        }


        //Check missing middle left
        for(int i = 1; i < 7; i++) {
            //Column
            for(int j = 7; j > 3; j--){
                //Row stays the same, column moves to the right until it gets to the middle, 4
                String rowString = Integer.toString(i);

                //should be filled
                String tileOne = rowString + Integer.toString(j);
                String tileTwo = rowString + Integer.toString(j-1);

                //Missing piece
                String tileThree = rowString + Integer.toString(j-2);

                //Should be filled
                String tileFour = rowString + Integer.toString(j-3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileTwo).equals("H") && a_board.GetValueUsingTile(tileFour).equals("H")){
                    if(a_board.ValidateMove(rowString, Integer.toString(j-2))){
                        return tileThree;
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

        //TileOne
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                //Place
                String tileOne = Integer.toString(i) + Integer.toString(j);

                //Filled
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileFour).equals("H") && a_board.GetValueUsingTile(tileFour).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(i), Integer.toString(j))){
                        return tileOne;
                    }
                }
            }
        }

        //TileTwo
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                //Place
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);

                //Filled
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileFour).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(i+1), Integer.toString(j+1))){
                        return tileTwo;
                    }
                }
            }
        }

        //TileThree
        for(int i = 1; i < 4; i++){
            //Column
            for(int j = 1; j < 5; j++){
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i+1) + Integer.toString(j+1);

                //Place
                String tileThree = Integer.toString(i+2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i+3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileFour).equals("H") && a_board.GetValueUsingTile(tileFour).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(i+2), Integer.toString(j+2))){
                        return tileThree;
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

        //TileOne
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                //Place
                String tileOne = Integer.toString(i) + Integer.toString(j);

                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);
                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileFour).equals("H") && a_board.GetValueUsingTile(tileTwo).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(i), Integer.toString(j))){
                        return tileOne;
                    }
                }
            }
        }

        //TileTwo
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                //Place
                String tileOne = Integer.toString(i) + Integer.toString(j);

                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);

                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);
                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileFour).equals("H") && a_board.GetValueUsingTile(tileThree).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(i-1), Integer.toString(j+1))){
                        return tileTwo;
                    }
                }
            }
        }

        //TileThree
        for(int i = 6; i > 3; i--){
            //Column
            for(int j = 1; j < 5; j++){
                //Place
                String tileOne = Integer.toString(i) + Integer.toString(j);
                String tileTwo = Integer.toString(i-1) + Integer.toString(j+1);

                String tileThree = Integer.toString(i-2) + Integer.toString(j+2);

                String tileFour = Integer.toString(i-3) + Integer.toString(j+3);

                if(a_board.GetValueUsingTile(tileOne).equals("H") && a_board.GetValueUsingTile(tileFour).equals("H") && a_board.GetValueUsingTile(tileTwo).equals("H")){
                    if(a_board.ValidateMove(Integer.toString(i-2), Integer.toString(j+2))){
                        return tileThree;
                    }
                }
            }
        }

        //No way to win
        return noMove;

    }

    /**
     * Name:
     * DecideBasicMove
     *
     * Synopsis:
     * public String DecideBasicMove(Connect4Board a_board);
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
     * 2/13/2017
     */
    private String DecideBasicMove(Connect4Board a_board){
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
