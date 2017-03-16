package com.example.nicole.gamesuite;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

}
