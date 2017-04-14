package com.example.nicole.gamesuite;

import android.util.Log;

import java.util.Hashtable;
import java.util.Random;

/**
 * Created by Nicole on 2/12/2017.
 */

public class BattleshipBoard {
    /* *********************************************
   `* Private class variables
    ********************************************* */
    //Hashtable to hold the the type of piece located at each space
    //B for blank, S for ship, H for hit ship, BH for blank hit
    private static Hashtable<String, String> battleshipBoardHuman = new Hashtable<String, String>();
    private static Hashtable<String, String> battleshipBoardComputer = new Hashtable<String, String>();

    /* *********************************************
 `   * Constructor
     ********************************************* */

    /**
     * Name:
     * BattleshipBoard()
     *
     * Synopsis:
     * public BattleshipBoard()
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
     * 2/13/2017
     */
    public BattleshipBoard(){
        //Initialize the Hashtable with the defualt values at default locations
        battleshipBoardHuman.clear();
        battleshipBoardComputer.clear();

        InitializeBoardHuman();
        InitializeBoardComputer();

        PlaceOriginalShipsComputer();

        print();
    }

    /* *********************************************
`   * Public functions
    ********************************************* */

    /**
     * Name:
     * ResetGame
     *
     * Synopsis:
     * public void ResetGame();
     * No params.
     *
     * Description:
     * This function is used in order to reset the board back
     * to it's default values.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void ResetGame(){
        InitializeBoardHuman();
        InitializeBoardComputer();

        PlaceOriginalShipsComputer();
    }

    /**
     * Name:
     * SetBlankComputer
     *
     * Synopsis:
     * public void SetBlankComputer(String tile);
     * @param tile -> The tile to update with a blank value, B.
     *
     * Description:
     * Updates a tile in the battleshipBoardComputer hashtable with a B for blank.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void SetBlankComputer(String tile){
        battleshipBoardComputer.put(tile, "B");
    }

    /**
     * Name:
     * SetBlankHuman
     *
     * Synopsis:
     * public void SetBlankHuman(String tile);
     * @param tile -> The tile to be updated to a blank in the battleshipBoardHuman hashtable.
     *
     * Description:
     * Used in order to update a tile in the battleshipBoardHuman hashtable to blank.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void SetBlankHuman(String tile){
        battleshipBoardHuman.put(tile, "B");
    }

    /**
     * Name:
     * SetShipComputer
     *
     * Synopsis:
     * public void SetShipComputer(String tile);
     * @param tile -> The tile to be updated to a ship in the battleshipBoardComputer hashtable.
     *
     * Description:
     * Used in order to update a tile in the battleshipBoardComputer hashtable to a ship, S.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void SetShipComputer(String tile){
        battleshipBoardComputer.put(tile, "S");
    }

    /**
     * Name:
     * SetShipHuman
     *
     * Synopsis:
     * public void SetShipHuman(String tile);
     * @param tile -> The tile to be updated to a ship in the battleshipBoardHuman hashtable.
     *
     * Description:
     * Used in order to update a tile in the battleshipBoardHuman hashtable to a ship, S.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void SetShipHuman(String tile){
        battleshipBoardHuman.put(tile, "S");
    }

    /**
     * Name:
     * SetShipHitComputer
     *
     * Synopsis:
     * public void SetShipHitComputer(String tile);
     * @param tile -> The tile to be updated to hit in the battleshipBoardComputer hashtable.
     *
     * Description:
     * Used in order to update a tile in the battleshipBoardComputer hashtable to hit, H.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void SetShipHitComputer(String tile){
        battleshipBoardComputer.put(tile, "H");
    }

    /**
     * Name:
     * SetBlankHitComputer
     *
     * Synopsis:
     * public void SetBlankHitComputer(String tile)
     * @param tile -> The tile location to update from B to BH.
     *
     * Description:
     * Used in order to set the tile in the battleshipBoardComputer hashtable to BH for blank hit.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void SetBlankHitComputer(String tile){
        battleshipBoardComputer.put(tile, "BH");
    }

    /**
     * Name:
     * SetShipHitHuman
     *
     * Synopsis:
     * public void SetShipHitHuman(String tile);
     * @param tile -> The tile to update to hit.
     *
     * Description:
     * Updates a tile in the battleshipBoardHuman hashtable to H for hit.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void SetShipHitHuman(String tile){
        battleshipBoardHuman.put(tile, "H");
    }

    /**
     * Name:
     * SetBlankHitHuman
     *
     * Synopsis:
     * public void SetBlankHitHuman(String tile);
     * @param tile -> The tile to update to a blank hit.
     *
     * Description:
     * Updates a tile in the battleshipBoardHuman hashtable to a blank hit, BH.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public void SetBlankHitHuman(String tile){
        battleshipBoardHuman.put(tile, "BH");
    }

    /**
     * Name:
     * GetPieceAtSpaceComputer
     *
     * Synopsis:
     * public String GetPieceAtSpaceComputer(String tile);
     * @param tile -> The tile to get the piece at.
     *
     * Description:
     * Returns the type of piece located a specified tile.
     *
     * Returns:
     * @return String, the type of tile at the specified tile.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public String GetPieceAtSpaceComputer(String tile){
        return battleshipBoardComputer.get(tile);
    }

    /**
     * Name:
     * GetPieceAtSpaceHuman
     *
     * Synopsis:
     * public String GetPieceAtSpaceHuman(String tile);
     * @param tile -> The tile to get the piece at.
     *
     * Description:
     * Returns the type of piece located at the specified tile.
     *
     * Returns:
     * @return String, the type of piece located at the specified tile.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public String GetPieceAtSpaceHuman(String tile){
        return battleshipBoardHuman.get(tile);
    }

    /**
     * Name:
     * CheckForComputerShipHit
     *
     * Synopsis:
     * public String CheckForComputerShipHit(String tile);
     * @param tile -> The tile to be checked in the battleshipBoardComputer hashtable.
     *
     * Description:
     * Used in order to check if a tile that was hit by the computer was a ship that was already hit.
     *
     * Returns:
     * @return String, the type of piece located at the specified tile.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public String CheckForComputerShipHit(String tile){
        //Hit ship
        if(GetPieceAtSpaceComputer(tile).equals("S")){
            return "S";
        }

        //Hit blank
        else if(GetPieceAtSpaceComputer(tile).equals("B")){
            return "B";
        }

        //Hit already hit ship
        return "H";
    }

    /**
     * Name:
     * CheckForHumanShipHit
     *
     * Synopsis:
     * public String CheckForHumanShipHit(String tile);
     * @param tile -> The tile to be checked in the battleshipBoardHuman hashtable.
     *
     * Description:
     * Used in order to check the type of piece that was hit in the battleshipBoardHuman hashtable.
     *
     * Return:
     * @return String, the type of piece located at the specified tile.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public String CheckForHumanShipHit(String tile){
        //Hit ship
        if(GetPieceAtSpaceHuman(tile).equals("S")){
            return "S";
        }

        //Hit blank
        else if(GetPieceAtSpaceHuman(tile).equals("B")){
            return "B";
        }

        //Hit already
        return "H";
    }

    /**
     * Name:
     * GetNumberOfComputerShipTiles
     *
     * Synopsis:
     * public Integer GetNumberOfComputerShipTiles();
     *
     * Description:
     * Used in order to get the remaining number of ship tiles still not hit in the computer hashtable.
     *
     * Return:
     * @return Integer, the number of ships that are remaining in the computer board hashtable.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public Integer GetNumberOfComputerShipTiles(){
        Integer count = 0;

        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                String tile = Integer.toString(i) + Integer.toString(j);
                if(GetPieceAtSpaceComputer(tile).equals("S")){
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Name:
     * GetNumberOfHumanShipTiles
     *
     * Synopsis:
     * public Integer GetNumberOfHumanShipTiles();
     *
     * Description:
     * Used in order to get the remaining number of ship tiles still not hit in the human board hashtable.
     *
     * Return:
     * @return Integer, the number of ships remaining in the human board hashtable.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    public Integer GetNumberOfHumanShipTiles(){
        Integer count = 0;

        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                String tile = Integer.toString(i) + Integer.toString(j);
                if(GetPieceAtSpaceHuman(tile).equals("S")){
                    count++;
                }
            }
        }

        return count;
    }

    //Remove
    public void print(){
        //Row
        for(int i = 8; i > 0; i-- ){
            //Column
            for(int j = 1; j < 9; j++){
                String tile = Integer.toString(i) + Integer.toString(j);

                System.out.print(battleshipBoardComputer.get(tile) + " ");
            }
            System.out.print("\n");
        }
    }

    /* *********************************************
`   * Private functions
    ********************************************* */

    /**
     * Name:
     * InitializeBoardComputer
     *
     * Synopsis:
     * private void InitializeBoardComputer();
     *
     * Description:
     * Used in order to set all the spaces in the battleshipBoardComputer hashtable to B for blank.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    private void InitializeBoardComputer(){
        battleshipBoardComputer.clear();

        String row;
        String column;
        String tile;

        //Row
        for(int i = 1; i < 9; i++ ){
            //Column
            for(int j = 1; j < 9; j++){
                row = String.valueOf(i);
                column = String.valueOf(j);

                tile = row + column;

                //All the tiles are originally blank
                battleshipBoardComputer.put(tile, "B");
            }
        }

    }

    /**
     * Name:
     * InitializeBoardHuman
     *
     * Synopsis:
     * private void InitializeBoardHuman();
     *
     * Description:
     * Used in order to set all the spaces in the battleshipBoardHuman hashtable to B for blank.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    private void InitializeBoardHuman(){
        battleshipBoardHuman.clear();

        String row;
        String column;
        String tile;

        //Row
        for(int i = 1; i < 9; i++ ){
            //Column
            for(int j = 1; j < 9; j++){
                row = String.valueOf(i);
                column = String.valueOf(j);

                tile = row + column;

                //All the tiles are originally blank
                battleshipBoardHuman.put(tile, "B");
            }
        }
    }

    /**
     * Name:
     * PlaceOriginalShipsComputer
     *
     * Synopsis:
     * private void PlaceOriginalShipsComputer();
     *
     * Description:
     * Sets the original five ships in the battleshipBoardComputer hashtable.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/13/2017
     */
    private void PlaceOriginalShipsComputer(){
        /*
        5 Ship Types:
        Aircraft - Size 5
        BattleShip - Size 4
        Submarine - Size 3
        Cruiser - Size 3
        Destroyer - Size 2
         */
        Integer numPlaced = 0;

        int row = 0;
        int column = 0;

        int min = 0;
        int max = 0;

        boolean aircraftNotPlaced = true;
        boolean battleshipNotPlaced = true;
        boolean submarineNotPlaced = true;
        boolean cruiserNotPlaced = true;
        boolean destroyerNotPlaced = true;

        while(aircraftNotPlaced) {

            boolean valid = true;

            //Place horizontally
            if (Math.random() >= 0.5) {

                Random rand = new Random();

                //Can be in any row, only columns up to 4
                //this is the starting tile
                row = 1 + rand.nextInt((8 - 1) + 1);
                column = 1 + rand.nextInt((4 - 1) + 1);

                //Validate
                for (int i = column; i <= column + 4; i++) {
                    String tile = Integer.toString(row) + Integer.toString(i);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = column; i <= column + 4; i++) {
                        String tile = Integer.toString(row) + Integer.toString(i);
                        SetShipComputer(tile);
                    }

                    aircraftNotPlaced = false;
                }
            }

            //Place vertically
            else {

                Random rand = new Random();

                //Can be in any row, only columns up to 4
                //this is the starting tile
                row = 1 + rand.nextInt((4 - 1) + 1);
                column = 1 + rand.nextInt((8 - 1) + 1);

                //Validate
                for (int i = row; i <= row + 4; i++) {
                    String tile = Integer.toString(i) + Integer.toString(column);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = row; i <= row + 4; i++) {
                        String tile = Integer.toString(i) + Integer.toString(column);
                        SetShipComputer(tile);
                    }

                    aircraftNotPlaced = false;
                }
            }
        }

        while(battleshipNotPlaced){
            boolean valid = true;

            //Place horizontally
            if (Math.random() >= 0.5) {

                Random rand = new Random();

                //Can be in any row, only columns up to 5
                //this is the starting tile
                row = 1 + rand.nextInt((8 - 1) + 1);
                column = 1 + rand.nextInt((5 - 1) + 1);

                //Validate
                for (int i = column; i <= column + 3; i++) {
                    String tile = Integer.toString(row) + Integer.toString(i);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = column; i <= column + 3; i++) {
                        String tile = Integer.toString(row) + Integer.toString(i);
                        SetShipComputer(tile);
                    }

                    battleshipNotPlaced = false;
                }
            }

            else {
                Random rand = new Random();

                //Can be in any row, only columns up to 4
                //this is the starting tile
                row = 1 + rand.nextInt((5 - 1) + 1);
                column = 1 + rand.nextInt((8 - 1) + 1);

                //Validate
                for (int i = row; i <= row + 3; i++) {
                    String tile = Integer.toString(i) + Integer.toString(column);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = row; i <= row + 3; i++) {
                        String tile = Integer.toString(i) + Integer.toString(column);
                        SetShipComputer(tile);
                    }

                    battleshipNotPlaced = false;
                }
            }

        }

        while(submarineNotPlaced){
            boolean valid = true;

            //Place horizontally
            if (Math.random() >= 0.5) {

                Random rand = new Random();

                //Can be in any row, only columns up to 6
                //this is the starting tile
                row = 1 + rand.nextInt((8 - 1) + 1);
                column = 1 + rand.nextInt((6 - 1) + 1);

                //Validate
                for (int i = column; i <= column + 2; i++) {
                    String tile = Integer.toString(row) + Integer.toString(i);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = column; i <= column + 2; i++) {
                        String tile = Integer.toString(row) + Integer.toString(i);
                        SetShipComputer(tile);
                    }

                    submarineNotPlaced = false;
                }
            }

            else {
                Random rand = new Random();

                //Can be in any row, only columns up to 6
                //this is the starting tile
                row = 1 + rand.nextInt((6 - 1) + 1);
                column = 1 + rand.nextInt((8 - 1) + 1);

                //Validate
                for (int i = row; i <= row + 2; i++) {
                    String tile = Integer.toString(i) + Integer.toString(column);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = row; i <= row + 2; i++) {
                        String tile = Integer.toString(i) + Integer.toString(column);
                        SetShipComputer(tile);
                    }

                    submarineNotPlaced = false;
                }
            }
        }

        while(cruiserNotPlaced){
            boolean valid = true;

            //Place horizontally
            if (Math.random() >= 0.5) {

                Random rand = new Random();

                //Can be in any row, only columns up to 6
                //this is the starting tile
                row = 1 + rand.nextInt((8 - 1) + 1);
                column = 1 + rand.nextInt((6 - 1) + 1);

                //Validate
                for (int i = column; i <= column + 2; i++) {
                    String tile = Integer.toString(row) + Integer.toString(i);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = column; i <= column + 2; i++) {
                        String tile = Integer.toString(row) + Integer.toString(i);
                        SetShipComputer(tile);
                    }

                    cruiserNotPlaced = false;
                }
            }

            else {
                Random rand = new Random();

                //Can be in any row, only columns up to 6
                //this is the starting tile
                row = 1 + rand.nextInt((6 - 1) + 1);
                column = 1 + rand.nextInt((8 - 1) + 1);

                //Validate
                for (int i = row; i <= row + 2; i++) {
                    String tile = Integer.toString(i) + Integer.toString(column);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = row; i <= row + 2; i++) {
                        String tile = Integer.toString(i) + Integer.toString(column);
                        SetShipComputer(tile);
                    }

                    cruiserNotPlaced = false;
                }
            }
        }

        while(destroyerNotPlaced){
            boolean valid = true;

            //Place horizontally
            if (Math.random() >= 0.5) {

                Random rand = new Random();

                //Can be in any row, only columns up to 7
                //this is the starting tile
                row = 1 + rand.nextInt((8 - 1) + 1);
                column = 1 + rand.nextInt((7 - 1) + 1);

                //Validate
                for (int i = column; i <= column + 1; i++) {
                    String tile = Integer.toString(row) + Integer.toString(i);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = column; i <= column + 1; i++) {
                        String tile = Integer.toString(row) + Integer.toString(i);
                        SetShipComputer(tile);
                    }

                    destroyerNotPlaced = false;
                }
            }

            else {
                Random rand = new Random();

                //Can be in any row, only columns up to 7
                //this is the starting tile
                row = 1 + rand.nextInt((7 - 1) + 1);
                column = 1 + rand.nextInt((8 - 1) + 1);

                //Validate
                for (int i = row; i <= row + 1; i++) {
                    String tile = Integer.toString(i) + Integer.toString(column);
                    if (GetPieceAtSpaceComputer(tile).equals("S")) {
                        //ship can't go here
                        valid = false;
                        break;
                    }
                }

                if (valid == true) {
                    //Place
                    for (int i = row; i <= row + 1; i++) {
                        String tile = Integer.toString(i) + Integer.toString(column);
                        SetShipComputer(tile);
                    }

                    destroyerNotPlaced = false;
                }
            }

        }

    }
}
