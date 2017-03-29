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

                    content.append(System.getProperty("line.separator"));
                }

                line = "Human";
                content.append(line);
                content.append(System.getProperty("line.separator"));
                for (int j = 0; j < board.GetSizeOfHumanHand(); j++) {

                    line = board.GetHumanCard(j);

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

    public boolean serializationFromFile(String fileName, Crazy8sBoard board){
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

        finalString = sb.toString();

        //Break the string into individual lines
        Scanner scanner = new Scanner(finalString);

        int count = 0;

        while(scanner.hasNextLine()){
            String newLine = scanner.nextLine();

            String [] words = newLine.split("\\W+");

            //first line should say connect4
            if(first){
                if(words[0].equals("Crazy8s")){
                    first = false;
                    computer = true;
                }
                else{
                    return false;
                }
            }

            if(computer) {

                if(words[0].equals("Human")){
                    computer = false;
                    human = true;
                }

                else{
                    board.AddCardComputerFromSerial(words[0]);

                }
            }

            if(human){
                board.AddCardHumanFromSerial(words[0]);
            }
        }

        return true;
    }
}
