package com.example.nicole.gamesuite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Connect4Activity extends AppCompatActivity {

    /* *********************************************
`   * Private class variables and objects
    ********************************************* */
    //The Connect 4 board object
    private Connect4Board board;

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
            if(board.ValidateMove(rowString, columnString)){
                board.updateHumanMove(rowString, columnString);
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
                else {
                    currentPlayer = "C";
                }
            }
        }

        else{
            if(board.ValidateMove(rowString, columnString)){
                board.updateComputerMove(rowString, columnString);
                view.setBackgroundResource(R.drawable.yellowcircle);
                currentPlayer = "H";
            }
        }
    }

    /* *********************************************
`   * Private functions
    ********************************************* */
    private void playGame(){

    }

    private void resetGame(){

    }
}
