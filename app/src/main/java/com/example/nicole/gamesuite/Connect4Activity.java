package com.example.nicole.gamesuite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Connect4Activity extends AppCompatActivity {

    /* *********************************************
`   * Private class variables and objects
    ********************************************* */
    //The Connect 4 board object
    private Connect4Board board;
    private Connect4Computer computerPlayer;
    private Connect4Save connectSave;

    private String saveFileName;

    //The current player
    private String currentPlayer = "H";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4);
    }

    /* *********************************************
`   * Constructor
    ********************************************* */
    public Connect4Activity(){
        board = new Connect4Board();
        computerPlayer = new Connect4Computer();
        connectSave = new Connect4Save();
    }

    /* *********************************************
`   * Public functions
    ********************************************* */
    public void tileClick(View view){

        //Get the id of the tile clicked
        String tile = getResources().getResourceEntryName(view.getId());

        //Get the tile row and column
        String rowString = Character.toString(tile.charAt(4));
        String columnString = Character.toString(tile.charAt(5));

        if(currentPlayer.equals("H")){

            Log.d("HUMNAN", currentPlayer);

            if(board.ValidateMove(rowString, columnString)){
                board.UpdateHumanMove(rowString, columnString);
                view.setBackgroundResource(R.drawable.redcircle);

                if(board.CheckForWinHuman()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("You won by getting 4 in a row! Would you like to play again?");
                    builder1.setCancelable(false)

                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            })

                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }

                else{
                    currentPlayer = "C";
                    Handler compHandler = new Handler();
                    compHandler.postDelayed(ComputerRunnable, 500);
                    //PlayGameComputer();
                }
            }
        }

    }

    public void saveGame(View view){

        //Only let the human save on it's turn
        if(currentPlayer.equals("H")) {

            //Create an alert box to ask the human for a file name to save to
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter a file name (include .txt)");

            final EditText input = new EditText(this);

            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Get the name and serialize
                    saveFileName = input.getText().toString();
                    connectSave.serializationToFile(saveFileName, board);

                    //Go back to the beginning activity
                    finish();
                    System.exit(0);
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
    }

    /* *********************************************
`   * Private functions
    ********************************************* */

    private Runnable ComputerRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d("COMP", currentPlayer);

            //Decide move
            String move = computerPlayer.DecideRandomMove(board);
            String compRow = Character.toString(move.charAt(0));
            String compCol = Character.toString(move.charAt(1));

            //Change color
            String tile = "Tile" + compRow + compCol;
            int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

            ImageButton toChange = (ImageButton)findViewById(idOriginal);
            toChange.setBackgroundResource(R.drawable.yellowcircle);

            //Update in board hashtable
            board.UpdateComputerMove(compRow, compCol);

            if (board.CheckForWinComputer()) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Connect4Activity.this);
                builder1.setMessage("The computer won by getting 4 in a row! Would you like to play again?");
                builder1.setCancelable(false)

                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })

                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

            else {
                currentPlayer = "H";
            }
        }
    };

    private void PlayGameComputer(){
        Log.d("COMP", currentPlayer);

        //Decide move
        String move = computerPlayer.DecideRandomMove(board);
        String compRow = Character.toString(move.charAt(0));
        String compCol = Character.toString(move.charAt(1));

        //Change color
        String tile = "Tile" + compRow + compCol;
        int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

        ImageButton toChange = (ImageButton)findViewById(idOriginal);
        toChange.setBackgroundResource(R.drawable.yellowcircle);

        //Update in board hashtable
        board.UpdateComputerMove(compRow, compCol);

        if (board.CheckForWinComputer()) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("The computer won by getting 4 in a row! Would you like to play again?");
            builder1.setCancelable(false)

                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })

                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

        else {
            currentPlayer = "H";
        }

    }

    private void ResetGame(){

    }
}
