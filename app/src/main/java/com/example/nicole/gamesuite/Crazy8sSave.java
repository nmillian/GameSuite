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
 * Created by Nicole on 3/26/2017.
 */

public class Crazy8sSave {

    /* *********************************************
`   * Public functions
    ********************************************* */

    /**
     * Name:
     * serializationToFile
     *
     * Synopsis:
     * public void serializationToFile(String fileName, Crazy8sBoard board )
     * @param fileName -> The file name to save the game state to.
     * @param board -> The board of the game to be saved, includes the top card, deck, human hand, and computer hand.
     *
     * Description:
     * Used in order to save the state of a game to a text file and then exits the game.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/26/2017
     */
    public void serializationToFile(String fileName, Crazy8sBoard board ){

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

            String line = "Crazy8s";
            StringBuilder content = new StringBuilder();

            FileOutputStream outputStream;

            try {

                //Write each line to the file
                outputStream = new FileOutputStream(file);

                content.append(line);
                content.append(System.getProperty("line.separator"));

                line = "Computer";
                content.append(line);
                content.append(System.getProperty("line.separator"));

                for (int i = 0; i < board.GetSizeOfComputerHand(); i++) {

                    line = board.GetComputerCard(i);
                    content.append(line);

                    content.append(System.getProperty("line.separator"));
                }

                line = "Human";
                content.append(line);
                content.append(System.getProperty("line.separator"));
                for (int j = 0; j < board.GetSizeOfHumanHand(); j++) {

                    line = board.GetHumanCard(j);
                    content.append(line);

                    content.append(System.getProperty("line.separator"));
                }

                line = "Deck";
                content.append(line);
                content.append(System.getProperty("line.separator"));
                for (int d = 0; d < board.GetDeckSize(); d++) {

                    line = board.GetDeckCard(d);
                    content.append(line);

                    content.append(System.getProperty("line.separator"));
                }

                line = "Trash";
                content.append(line);
                content.append(System.getProperty("line.separator"));

                line = board.GetTopTrashCard();
                System.out.println("TRASH CARD" + board.GetTopTrashCard());
                content.append(line);
                content.append(System.getProperty("line.separator"));


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

    /**
     * Name:
     * serializationFromFile
     *
     * Synopsis:
     * public boolean serializationFromFile(String fileName, Crazy8sBoard board);
     * @param fileName -> The file name to read from.
     * @param board -> The board to be updated with the contents from the file.
     *
     * Description:
     * Validates that the file is for the correct game type and reads in the top trash card, deck, computer hand, and human hand.
     *
     * Returns:
     * @return Boolean, true if the file is able to be read, false if there is an error with the file.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/26/2017
     */
    public boolean serializationFromFile(String fileName, Crazy8sBoard board){
        System.out.println("IN SERIAL FROM FILE");

        board.ClearGame();

        //The final string consisting of the entire serialized file read in
        String finalString;

        FileInputStream is;
        BufferedReader reader;

        File file;

        boolean first = true;

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

        boolean computer = false;
        boolean human = false;
        boolean deck = false;
        boolean trash = false;

        finalString = sb.toString();

        System.out.println("FINAL " + finalString);

        //Break the string into individual lines
        Scanner scanner = new Scanner(finalString);

        int count = 0;

        while(scanner.hasNextLine()){
            String newLine = scanner.nextLine();

            String [] words = newLine.split("\\W+");

            //first line should say crazy8s
            if(first){
                if(words[0].equals("Crazy8s")){
                    first = false;
                    computer = true;
                }
                else{
                    return false;
                }
            }

            else if(computer) {

                if(words[0].equals("Human")){
                    computer = false;
                    human = true;
                }

                else{
                    if(!words[0].equals("Computer")){
                        board.AddCardComputerFromSerial(words[0]);
                    }
                }
            }

            else if(human){

                if(words[0].equals("Deck")){
                    human = false;
                    deck = true;
                }
                else {
                    board.AddCardHumanFromSerial(words[0]);
                }
            }

            else if(deck){
                if(words[0].equals("Trash")){
                    deck = false;
                    trash = true;
                }
                else {
                    board.AddCardDeckFromSerial(words[0]);
                }
            }

            else if(trash){
                System.out.println("IN SAVE TRASH CARD" + words[0]);
                board.SetTopTrashCard(words[0]);
            }
        }

        return true;
    }
}
