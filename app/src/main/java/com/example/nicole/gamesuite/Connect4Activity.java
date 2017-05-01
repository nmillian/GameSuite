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

    /* *********************************************
`   * Constructors
    ********************************************* */
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
     * 2/13/2017
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4);

        Bundle extras = getIntent().getExtras();
        String isSave = extras.getString("SAVE");

        //Saved game
        if(isSave.equals("YES")){
            boolean validSave;

            System.out.println("IN SAVE GAME FUNCTION ");

            String fileName = extras.getString("FILENAME");
            validSave = connectSave.serializationFromFile(fileName, board);

            if(validSave) {
                setSerializedBoard();
            }

            else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("The save file was not for Connect 4, starting a fresh game.");
                builder1.setCancelable(false)

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        }
    }

    /**
     * Name:
     * Connect4Activity
     *
     * Synopsis:
     * public Connect4Activity()
     * No params.
     *
     * Description:
     * This is the constuctor for the connect 4 activity. Used in order to initialize the board, computer, and save.
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
    public Connect4Activity(){
        board = new Connect4Board();
        computerPlayer = new Connect4Computer();
        connectSave = new Connect4Save();
    }

    /* *********************************************
`   * Public functions
    ********************************************* */

    /**
     * Name:
     * TileClick
     *
     * Synopsis:
     * public void TileClick(View view);
     * @param view -> The connect 4 activity view.
     *
     * Description:
     * This function is called when the human player taps a space on the connect 4 board.
     * The function validates that it is the human player's turn and that a piece is able to be
     *  played at the given space.
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
    public void TileClick(View view){

        //Get the id of the tile clicked
        String tile = getResources().getResourceEntryName(view.getId());

        //Get the tile row and column
        String rowString = Character.toString(tile.charAt(4));
        String columnString = Character.toString(tile.charAt(5));

        if(currentPlayer.equals("H")){

            if(board.ValidateMove(rowString, columnString)){
                board.UpdateHumanMove(rowString, columnString);
                view.setBackgroundResource(R.drawable.redcircle);

                //Hide the save button, can't save on computer turn
                View visibility;

                visibility = findViewById(R.id.save);
                visibility.setVisibility(View.GONE);


                if(board.CheckForWinHuman()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("You won by getting 4 in a row! Would you like to play again?");
                    builder1.setCancelable(false)

                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    ResetBoard();
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

                else if(board.GetNumberOfEmptyTiles() == 0){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Connect4Activity.this);
                    builder1.setMessage("There are no more possible moves. The game is a draw. Play again?");
                    builder1.setCancelable(false)

                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    ResetBoard();
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

    /**
     * Name:
     * SaveGame
     *
     * Synopsis:
     * public void SaveGame(View view);
     * @param view -> The connect 4 activity view.
     *
     * Description:
     * This function is called when the human player taps the button to save the game.
     * Prompts the user to enter what to name the file, and finshes the activity.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/18/2017
     */
    public void SaveGame(View view){

        //Only let the human save on it's turn
        if(currentPlayer.equals("H")) {

            //Create an alert box to ask the human for a file name to save to
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter a file name");

            final EditText input = new EditText(this);

            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Get the name and serialize
                    saveFileName = input.getText().toString() + ".txt";
                    connectSave.serializationToFile(saveFileName, board);

                    //Go back to the beginning activity
                    finish();
                    //System.exit(0);
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

    /**
     * Name:
     * ComputerRunnable
     *
     * Synopsis:
     * private Runnable ComputerRunnable;
     * No params.
     *
     * Description:
     * This function is called when it's the computer's turn to make a move.
     * Calls the DecideMove function from the computer class, and updates the board with the move the computer made.
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
    private Runnable ComputerRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d("COMP", currentPlayer);

            //Decide move
            String move = computerPlayer.DecideMove(board);

            if(move.equals("none")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Connect4Activity.this);
                builder1.setMessage("There are no more possible moves. The game is a draw. Play again?");
                builder1.setCancelable(false)

                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                ResetBoard();
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
                String compRow = Character.toString(move.charAt(0));
                String compCol = Character.toString(move.charAt(1));

                //Change color
                String tile = "Tile" + compRow + compCol;
                int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

                ImageButton toChange = (ImageButton) findViewById(idOriginal);
                toChange.setBackgroundResource(R.drawable.yellowcircle);

                //Update in board hashtable
                board.UpdateComputerMove(compRow, compCol);

                if (board.CheckForWinComputer()) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Connect4Activity.this);
                    builder1.setMessage("The computer won by getting 4 in a row! Would you like to play again?");
                    builder1.setCancelable(false)

                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    ResetBoard();
                                }
                            })

                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else if (board.GetNumberOfEmptyTiles() == 0) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Connect4Activity.this);
                    builder1.setMessage("There are no more possible moves. The game is a draw. Play again?");
                    builder1.setCancelable(false)

                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    ResetBoard();
                                }
                            })

                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else {
                    currentPlayer = "H";

                    //Show the save button again
                    View visibility;

                    visibility = findViewById(R.id.save);
                    visibility.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    /**
     * Name:
     * setSerializedBoard
     *
     * Synopsis:
     * private void setSerializedBoard;
     * No params.
     *
     * Description:
     * This function is called in order to set the board from a save file.
     * Sets the pieces on the board to the correct color.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/18/2017
     */
    private void setSerializedBoard(){
        String row;
        String column;
        String tile;

        //Row
        for(int i = 1; i < 7; i++ ){
            //Column
            for(int j = 1; j < 8; j++){
                row = String.valueOf(i);
                column = String.valueOf(j);

                tile = "Tile" + row + column;

                System.out.print(board.GetValueAtTile(row, column) + " ");

                if(board.GetValueAtTile(row, column).equals("C")){
                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

                    ImageButton toChange = (ImageButton)findViewById(idOriginal);
                    toChange.setBackgroundResource(R.drawable.yellowcircle);

                }

                else if(board.GetValueAtTile(row, column).equals("H")){
                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

                    ImageButton toChange = (ImageButton)findViewById(idOriginal);
                    toChange.setBackgroundResource(R.drawable.redcircle);

                }
            }
            System.out.print("\n");
        }

    }


    /**
     * Name:
     * ResetBoard
     *
     * Synopsis:
     * private void ResetBoard()
     * No params.
     *
     * Description:
     * This function is called in order to reset the board to it's original state.
     * Sets the pieces on the board to the correct color.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 2/18/2017
     */
    private void ResetBoard(){
        board.ResetBoard();

        String row;
        String column;
        String tile;

        //Row
        for(int i = 1; i < 7; i++ ){
            //Column
            for(int j = 1; j < 8; j++){
                row = String.valueOf(i);
                column = String.valueOf(j);

                tile = "Tile" + row + column;

                int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

                ImageButton toChange = (ImageButton)findViewById(idOriginal);
                toChange.setBackgroundResource(R.drawable.whitecircle);

            }
        }

        currentPlayer = "H";
    }

}
