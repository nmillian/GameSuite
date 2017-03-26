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
    public BattleshipBoard(){
        //Initialize the Hashtable with the defualt values at default locations
        battleshipBoardHuman.clear();
        battleshipBoardComputer.clear();

        InitializeBoardHuman();
        InitializeBoardComputer();

        PlaceOriginalShipsComputer();

        print();
    }

    public void ResetGame(){
        InitializeBoardHuman();
        InitializeBoardComputer();

        PlaceOriginalShipsComputer();
    }

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

    public void SetBlankComputer(String tile){
        battleshipBoardComputer.put(tile, "B");
    }

    public void SetBlankHuman(String tile){
        battleshipBoardHuman.put(tile, "B");
    }

    public void SetShipComputer(String tile){
        battleshipBoardComputer.put(tile, "S");
    }

    public void SetShipHuman(String tile){
        battleshipBoardHuman.put(tile, "S");
        Log.d("SHIP", tile + battleshipBoardHuman.get(tile));
    }

    public void SetShipHitComputer(String tile){
        battleshipBoardComputer.put(tile, "H");
    }

    public void SetBlankHitComputer(String tile){
        battleshipBoardComputer.put(tile, "BH");
    }

    public void SetShipHitHuman(String tile){
        battleshipBoardHuman.put(tile, "H");
    }

    public void SetBlankHitHuman(String tile){
        battleshipBoardHuman.put(tile, "BH");
    }

    public String GetPieceAtSpaceComputer(String tile){
        return battleshipBoardComputer.get(tile);
    }

    public String GetPieceAtSpaceHuman(String tile){
        return battleshipBoardHuman.get(tile);
    }

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
}
