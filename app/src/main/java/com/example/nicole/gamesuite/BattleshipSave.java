package com.example.nicole.gamesuite;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Nicole on 3/22/2017.
 */

public class BattleshipSave {

    /**
     * Name:
     * serializationToFile
     *
     * Synopsis:
     * public void serializationToFile(String fileName, BattleshipBoard board);
     * @param fileName -> The file name to save to.
     * @param board -> The battleship board to save.
     *
     * Description:
     * Used in order to save the current state of the battleship board and exit the game.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/22/2017
     */
    public void serializationToFile(String fileName, BattleshipBoard board) {
        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");

        boolean isPresent = true;

        if (!docsFolder.exists()) {
            isPresent = docsFolder.mkdir();
        }

        if (isPresent) {

            File file = new File(docsFolder.getAbsolutePath(), fileName);

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String line = "Battleship";
            StringBuilder content = new StringBuilder();

            FileOutputStream outputStream;

            try {

                //Write each line to the file
                outputStream = new FileOutputStream(file);

                content.append(line);
                content.append(System.getProperty("line.separator"));

                //content.append("Computer");
                //content.append(System.getProperty("line.separator"));

                //Computer
                for (int i = 8; i > 0; i--) {
                    for (int j = 1; j < 9; j++) {
                        String row = Integer.toString(i);
                        String column = Integer.toString(j);

                        System.out.println("TILE " + row + column);

                        String tile = row + column;

                        //Hit ship
                        if(board.GetPieceAtSpaceComputer(tile).equals("H")){
                            line = "H ";
                            content.append(line);
                        }

                        //Ship
                        else if(board.GetPieceAtSpaceComputer(tile).equals("S")){
                            line = "S ";
                            content.append(line);
                        }

                        //Blank space
                        else if(board.GetPieceAtSpaceComputer(tile).equals("B")){
                            line = "B ";
                            content.append(line);
                        }

                        //Blank hit
                        else if(board.GetPieceAtSpaceComputer(tile).equals("BH")){
                            line = "BH ";
                            content.append(line);
                        }

                    }
                    content.append(System.getProperty("line.separator"));
                }


                //content.append("Human");
                //content.append(System.getProperty("line.separator"));

                //Human
                for (int i = 8; i > 0; i--) {
                    for (int j = 1; j < 9; j++) {
                        String row = Integer.toString(i);
                        String column = Integer.toString(j);

                        System.out.println("TILE " + row + column);

                        String tile = row + column;

                        //Hit ship
                        if(board.GetPieceAtSpaceHuman(tile).equals("H")){
                            line = "H ";
                            content.append(line);
                        }

                        //Ship
                        else if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                            line = "S ";
                            content.append(line);
                        }

                        //Blank space
                        else if(board.GetPieceAtSpaceHuman(tile).equals("B")){
                            line = "B ";
                            content.append(line);
                        }

                        //Blank hit
                        else if(board.GetPieceAtSpaceHuman(tile).equals("BH")){
                            line = "BH ";
                            content.append(line);
                        }

                    }
                    content.append(System.getProperty("line.separator"));
                }

                String finalString = content.toString();
                outputStream.write(finalString.getBytes());
                outputStream.close();

                System.out.println("final " + finalString);
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Name:
     * serializationFromFile
     *
     * Synopsis:
     * public boolean serializationFromFile(String fileName, BattleshipBoard board);
     * @param fileName -> The file name to read from.
     * @param board -> The board to update with the contents from the save file.
     *
     * Description:
     * Used in order to update the game with contents from a save file.
     *
     * Return:
     * @return Boolean, true if it was able to read the save, false if it was unable to read the save.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/22/2017
     */
    public boolean serializationFromFile(String fileName, BattleshipBoard board) {
        //The final string consisting of the entire serialized file read in
        String finalString;

        FileInputStream is;
        BufferedReader reader;

        File file;

        boolean first = true;

        boolean computer = false;
        boolean human = false;

        int row = 9;
        int column = 0;

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        file = new File(docsFolder.getAbsolutePath(), fileName);

        //Read in from the file
        StringBuilder sb = new StringBuilder();

        //Know the file exists already
        try {

            //File exists
            if (file.exists()) {

                is = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(is));

                String line = reader.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                    line = reader.readLine();
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finalString = sb.toString();

        System.out.println("FINAL STRING IN SERIALIZATION FROM FILE " + finalString);

        //Break the string into individual lines
        Scanner scanner = new Scanner(finalString);

        while(scanner.hasNextLine()){

            String tile;

            String newLine = scanner.nextLine();

            String [] words = newLine.split("\\W+");

            System.out.print("IN SCANNER HAS NEXT LINE \n");

            //first line should say connect4
            if(first){
                if(words[0].equals("Battleship")){
                    System.out.print("IN BATTLESHP");
                    first = false;
                    computer = true;
                }
                else{
                    return false;
                }
            }

            //Reset for human after the computer is done
            if(row == 1){
                System.out.print("IN ROW == 1");
                row = 9;
                computer = false;
                human = true;
            }

            if(!words[0].equals("Battleship")) {
                System.out.print("IN BATTLE SHIP ");
                if(computer) {

                    row = row - 1;
                    column = 0;

                    System.out.print("IN COMPUTER " + row);

                    for (int i = 0; i < words.length; i++) {
                        column = column + 1;

                        if (words[i].equals("S")) {
                            String rowString = String.valueOf(row);
                            String columnString = String.valueOf(column);

                            tile = rowString + columnString;
                            board.SetShipComputer(tile);

                        } else if (words[i].equals("H")) {
                            String rowString = String.valueOf(row);
                            String columnString = String.valueOf(column);

                            tile = rowString + columnString;
                            board.SetShipHitComputer(tile);

                        } else if (words[i].equals("B")) {
                            String rowString = String.valueOf(row);
                            String columnString = String.valueOf(column);

                            tile = rowString + columnString;
                            board.SetBlankComputer(tile);

                        } else if (words[i].equals("BH")) {
                            String rowString = String.valueOf(row);
                            String columnString = String.valueOf(column);

                            tile = rowString + columnString;
                            board.SetBlankHitComputer(tile);
                        }
                    }
                }

                else if(human){

                    row = row - 1;
                    column = 0;

                    System.out.print("IN HUMAN " + row);

                    for (int i = 0; i < words.length; i++) {
                        column = column + 1;

                        if (words[i].equals("S")) {
                            String rowString = String.valueOf(row);
                            String columnString = String.valueOf(column);

                            tile = rowString + columnString;
                            board.SetShipHuman(tile);

                        } else if (words[i].equals("H")) {
                            String rowString = String.valueOf(row);
                            String columnString = String.valueOf(column);

                            tile = rowString + columnString;
                            board.SetShipHitHuman(tile);

                        } else if (words[i].equals("B")) {
                            String rowString = String.valueOf(row);
                            String columnString = String.valueOf(column);

                            tile = rowString + columnString;
                            board.SetBlankHuman(tile);

                        } else if (words[i].equals("BH")) {
                            String rowString = String.valueOf(row);
                            String columnString = String.valueOf(column);

                            tile = rowString + columnString;
                            board.SetBlankHitHuman(tile);
                        }
                    }
                }
            }
        }

        return true;
    }


}
