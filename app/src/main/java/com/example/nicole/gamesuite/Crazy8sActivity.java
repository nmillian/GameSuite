package com.example.nicole.gamesuite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class Crazy8sActivity extends AppCompatActivity {

    private Crazy8sBoard board;
    private Crazy8sComputer computer;

    private String gameState = "start";
    private String currentPlayer = "human";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy8s);
    }

    public Crazy8sActivity(){
        board = new Crazy8sBoard();
        computer = new Crazy8sComputer();
    }

    public void TileClick(View view){
        boolean valid = false;

        if(gameState.equals("play")) {
            if (currentPlayer.equals("human")) {
                Integer card = 0;
                String temp = "NULL";

                String tile = view.getResources().getResourceEntryName(view.getId());
                System.out.println(tile);
                System.out.println(tile.length());

                //Parse char at 4 to get the location of the card
                if (tile.length() == 5) {
                    temp = Character.toString(tile.charAt(4));
                    card = Integer.parseInt(temp);

                    valid = board.VerifyHumanChoice(card);
                }

                //Parse char at 4 and 5 to get the location of the card
                else if (tile.length() == 6) {
                    temp = Character.toString(tile.charAt(4)) + Character.toString(tile.charAt(5));
                    card = Integer.parseInt(temp);

                    valid = board.VerifyHumanChoice(card);
                }

                if (valid) {

                    UpdateTrashCard();

                    //Remove the card from the human hand
                    board.RemoveCardFromHuman(card);

                    //Reprint human hand
                    PrintHumanHand();


                    //Check if human won
                    if (board.GetSizeOfHumanHand() == 0) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                        builder1.setMessage("You won the game by getting rid of your hand of cards!");
                        builder1.setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }

                    //Check if the top card is an 8
                    //Let the huamn place another card
                    else if(board.GetTopTrashCard().contains("8")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                        builder1.setMessage("After placing an 8, place another card from your hand to determine the suit.");
                        builder1.setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }

                    else{
                        //Computer's turn
                        currentPlayer = "computer";

                        //Add a delay to the computer playing
                        Handler compHandler = new Handler();
                        compHandler.postDelayed(ComputerRunnable, 1000);
                    }
                }
            }
        }
    }

    private Runnable ComputerRunnable = new Runnable() {
        @Override
        public void run() {
            ComputerTurn();
        }
    };

    private void ComputerTurn(){
        board.printCompHand();

        if(currentPlayer.equals("computer")){

            //Returned true so can move on
            if(computer.DecideMove(board)){
                UpdateTrashCard();
                PrintComputerHand();

                if(board.GetSizeOfComputerHand() == 0){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("The computer won the game by getting rid of all the cards!");
                    builder1.setCancelable(false)

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }

                //Computer gets to go again because it played an 8
                else if(board.GetTopTrashCard().contains("8")){
                    ComputerTurn();
                }

                else {

                    currentPlayer = "human";
                }
            }

            else{
                //Returned false so draw another card
                board.AddCardToComputerHand();
                PrintComputerHand();
                ComputerTurn();
            }
        }
    }

    public void rightCardClick(View view){

        if(gameState.equals("play")) {
            //If the size is 0 can't get more cards or human hand is full
            if (board.GetDeckSize() != 0 && board.GetSizeOfHumanHand() != 40) {
                board.AddCardToHumanHand();
                PrintHumanHand();

                //Can become 0 after drawing the last card
                //Update the visual
                if (board.GetDeckSize() == 0) {
                    String tile = "rightcard";

                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
                    ImageButton toChange = (ImageButton) findViewById(idOriginal);

                    String mDrawableName = "squaregrid";
                    int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

                    toChange.setBackgroundResource(resID);
                }
            }

            //If the deck is empty then say something
            else {
                if (board.GetDeckSize() == 0) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("The deck size is 0. No more cards to draw.");
                    builder1.setCancelable(false)

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else if (board.GetSizeOfHumanHand() == 40) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("Your hand is full, you cannot draw anymore cards.");
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

    }

    //Get the first card to be placed in the trash pile and distribute cards
    public void leftCardClick(View view) {

        if(gameState.equals("start")) {
            //Get the top card
            int num = 0;

            //Show the top card
            String mDrawableName = board.GetTopCard(num);

            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            view.setBackgroundResource(resID);

            //Remove card from deck
            board.RemoveCardFromDeck(num);
            System.out.println("SIZE AFTRE LEFT CLICK: " + board.GetDeckSize());

            //Print human hand
            PrintHumanHand();
            PrintComputerHand();

            gameState = "play";
        }

        /*
        String tile = "imageButton";
        int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        ImageButton toChange = (ImageButton) findViewById(idOriginal);

        mDrawableName = "redsquaregrid";
        resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

        toChange.setBackgroundResource(resID);
        */
    }

    public void UpdateTrashCard(){
        //Make card the top card
        String leftCard = "leftcard";
        int idOriginal = getResources().getIdentifier(leftCard, "id", getPackageName());
        ImageButton toChange = (ImageButton) findViewById(idOriginal);

        System.out.println("CARD TO SET " + board.GetTopTrashCard());
        String mDrawableName = board.GetTopTrashCard();
        Integer resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

        toChange.setBackgroundResource(resID);
    }

    private void PrintComputerHand(){
        String tile = "computerCards";
        String cardNum = Integer.toString(board.GetSizeOfComputerHand());

        System.out.println("COMPUTER SIZE " + board.GetSizeOfComputerHand());

        int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        TextView toChange = (TextView) findViewById(idOriginal);

        toChange.setText(cardNum);

    }

    private void PrintHumanHand(){
        String tile;
        Integer humanHandSize = board.GetSizeOfHumanHand();

        //Show the cards
        for(int i = 0; i < humanHandSize; i++){
            tile = "Tile" + i;

            int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
            ImageButton toChange = (ImageButton) findViewById(idOriginal);

            String mDrawableName = board.GetHumanCard(i);
            int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

            toChange.setBackgroundResource(resID);
        }

        //Clear the blank cards
        for(int i = humanHandSize; i < 40; i++){
            tile = "Tile" + i;

            int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
            ImageButton toChange = (ImageButton) findViewById(idOriginal);

            String mDrawableName = "squaregrid";
            int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

            toChange.setBackgroundResource(resID);
        }
    }
}
