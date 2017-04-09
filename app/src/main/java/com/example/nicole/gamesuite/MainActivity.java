package com.example.nicole.gamesuite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String game;
    private String isSave;
    private String fileName;

    /**
     * Name:
     * onCreate
     *
     * Synopsis:
     * protected void onCreate(Bundle savedInstanceState);
     * @param savedInstanceState -> The Bundle used in order to initialize the activity.
     *
     * Description:
     * This function is called when the activity is created and used to initialize the activity.
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View visibility;

        visibility = findViewById(R.id.save);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.newgame);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.cancel);
        visibility.setVisibility(View.GONE);
    }

    /**
     * Name:
     * connect4Click
     *
     * Synopsis:
     * public void connect4Click(View v);
     * @param v -> The activity view.
     *
     * Description:
     * This function is called when the human player taps the button to select the connect 4 game.
     * After the button is clicked, the option to select other games is invisible.
     * Buttons appear so the player can choose to load a saved game or to start a fresh game.
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
    public void connect4Click(View v){

        game = "connect4";

        View visibility;

        visibility = findViewById(R.id.button);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.button2);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.button3);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.save);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.newgame);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.cancel);
        visibility.setVisibility(View.VISIBLE);
    }

    /**
     * Name:
     * battleshipClick
     *
     * Synopsis:
     * public void battleshipClick (View v);
     * @param v -> The activity view.
     *
     * Description
     * This function is called when the human player taps the button to select the battleship game.
     * After the button is clicked, the option to select other games is invisible.
     * Buttons appear so the player can choose to load a saved game or to start a fresh game.
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
    public void battleshipClick (View v){

        game = "battleship";

        View visibility;

        visibility = findViewById(R.id.button);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.button2);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.button3);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.save);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.newgame);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.cancel);
        visibility.setVisibility(View.VISIBLE);
    }

    /**
     * Name:
     * crazy8sClick
     *
     * Synopsis:
     * public void crazy8sClick(View v);
     * @param v -> The activity view.
     *
     * Description:
     * This function is called when the human player taps the button to select the crazy 8s game.
     * After the button is clicked, the option to select other games is invisible.
     * Buttons appear so the player can choose to load a saved game or to start a fresh game.
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
    public void crazy8sClick(View v){

        game = "crazy8s";

        View visibility;

        visibility = findViewById(R.id.button);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.button2);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.button3);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.save);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.newgame);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.cancel);
        visibility.setVisibility(View.VISIBLE);
    }

    /**
     * Name:
     * newClick
     *
     * Synopsis:
     * public void newClick(View v);
     * @param v -> The activity view.
     *
     * Description:
     * Used in order to launch a new game and starts a new game activity.
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
    public void newClick(View v){
        isSave = "NO";

        if(game.equals("connect4")){
            Intent intent = new Intent(this, Connect4Activity.class);
            intent.putExtra("SAVE", isSave);
            startActivity(intent);
        }

        else if(game.equals("battleship")){
            Intent intent = new Intent(this, BattleshipActivity.class);
            intent.putExtra("SAVE", isSave);
            startActivity(intent);
        }

        else if(game.equals("crazy8s")){
            Intent intent = new Intent(this, Crazy8sActivity.class);
            intent.putExtra("SAVE", isSave);
            startActivity(intent);
        }

    }

    /**
     * Name:
     * saveClick
     *
     * Synopsis:
     * public void saveClick(View v);
     * @param v -> The activity view.
     *
     * Description:
     * Used in order to launch a saved game. Asks the user to specify a text file to get information from.
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
    public void saveClick(View v){
        isSave = "YES";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter a file name");

        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fileName = input.getText().toString() + ".txt";
                File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
                File file = new File(docsFolder.getAbsolutePath(), fileName);

                //File exists
                if (file.exists()) {

                    if(game.equals("connect4")){
                        Intent intent = new Intent(getApplicationContext(), Connect4Activity.class);
                        intent.putExtra("SAVE", isSave);
                        intent.putExtra("FILENAME", fileName);
                        startActivity(intent);
                    }

                    else if(game.equals("battleship")){
                        Intent intent = new Intent(getApplicationContext(), BattleshipActivity.class);
                        intent.putExtra("SAVE", isSave);
                        intent.putExtra("FILENAME", fileName);
                        startActivity(intent);
                    }

                    else if(game.equals("crazy8s")){
                        Intent intent = new Intent(getApplicationContext()  , Crazy8sActivity.class);
                        intent.putExtra("SAVE", isSave);
                        intent.putExtra("FILENAME", fileName);
                        startActivity(intent);
                    }

                }
                //File does not exist
                else{

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("A file with that name was not found.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     * Name:
     * cancelClick
     *
     * Synopsis:
     * cancelClick(View v);
     * @param v -> The activity view.
     *
     * Description:
     * Used in order to cancel starting a selected game and go back to the screen to select a game type.
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
    public void cancelClick(View v){
        View visibility;

        visibility = findViewById(R.id.button);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.button2);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.button3);
        visibility.setVisibility(View.VISIBLE);

        visibility = findViewById(R.id.save);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.newgame);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.cancel);
        visibility.setVisibility(View.GONE);

    }
}
