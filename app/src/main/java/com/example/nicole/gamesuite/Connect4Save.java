package com.example.nicole.gamesuite;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Nicole on 3/8/2017.
 */

public class Connect4Save {

    public void serializationToFile(String fileName, Connect4Board board ){

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");

        boolean isPresent = true;

        if (!docsFolder.exists()) {
            isPresent = docsFolder.mkdir();
        }
        if (isPresent) {

            File file = new File(docsFolder.getAbsolutePath(), fileName);

            try {
                file.createNewFile();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            String line = "Connect4";
            StringBuilder content = new StringBuilder();

            FileOutputStream outputStream;

            try {

                //Write each line to the file
                outputStream = new FileOutputStream(file);

                content.append(line);
                content.append(System.getProperty("line.separator"));

                for (int i = 6; i > 0; i--) {
                    for (int j = 1; j < 8; j++) {
                        String row = Integer.toString(i);
                        String column = Integer.toString(j);

                        System.out.println("TILE " + row + column);

                        //Human
                        if(board.GetValueAtTile(row, column).equals("H")){
                            line = "H ";
                            content.append(line);
                        }

                        //Computer
                        else if(board.GetValueAtTile(row, column).equals("C")){
                            line = "C ";
                            content.append(line);
                        }

                        //Blank space
                        else{
                            line = "B ";
                            content.append(line);
                        }
                    }

                    content.append(System.getProperty("line.separator"));
                }

                String finalString = content.toString();
                outputStream.write(finalString.getBytes());
                outputStream.close();

                System.out.println(finalString);
            }

            catch (IOException e) {
                e.printStackTrace();
            }


        }

        else {
            // Failure
        }

    }

    public boolean serializationFromFile(String fileName, Connect4Board board){
        //The final string consisting of the entire serialized file read in
        String finalString;

        FileInputStream is;
        BufferedReader reader;

        File file;

        boolean first = true;
        int row = 7;
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

        System.out.println("FINAL STRING " + finalString);

        //Break the string into individual lines
        Scanner scanner = new Scanner(finalString);

        while(scanner.hasNextLine()){
            String newLine = scanner.nextLine();

            String [] words = newLine.split("\\W+");

            //first line should say connect4
            if(first){
                if(words[0].equals("Connect4")){
                    first = false;
                }
                else{
                    return false;
                }
            }

            if(!words[0].equals("Connect4")) {
                row = row - 1;
                column = 0;

                for(int i = 0; i < words.length; i++){
                    column = column + 1;

                    if(words[i].equals("C")){
                        String rowString = String.valueOf(row);
                        String columnString = String.valueOf(column);

                        board.UpdateComputerMove(rowString, columnString);

                    }

                    else if(words[i].equals("H")){
                        String rowString = String.valueOf(row);
                        String columnString = String.valueOf(column);

                        board.UpdateHumanMove(rowString, columnString);
                    }

                    else{
                        String rowString = String.valueOf(row);
                        String columnString = String.valueOf(column);

                        board.UpdateBlankMove(rowString, columnString);

                    }
                }
            }
        }

        return true;
    }

}
