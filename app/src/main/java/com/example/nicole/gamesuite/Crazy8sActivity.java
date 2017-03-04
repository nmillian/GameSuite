package com.example.nicole.gamesuite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class Crazy8sActivity extends AppCompatActivity {

    private Crazy8sBoard board;

    private String gameState = "start";
    private String currentPlayer = "human";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy8s);
    }

    public Crazy8sActivity(){
        board = new Crazy8sBoard();
    }

    public void TileClick(View view){
        boolean valid = false;

        if(gameState.equals("play")){
            Integer card = 0;
            String temp = "NULL";

            String tile = view.getResources().getResourceEntryName(view.getId());
            System.out.println(tile);
            System.out.println(tile.length());

            //Parse char at 4
            if(tile.length() == 5){
                temp = Character.toString(tile.charAt(4));
                card = Integer.parseInt(temp);

                valid = board.VerifyHumanChoice(card);
            }

            //Parse char at 4 and 5
            else if(tile.length() == 6){
                temp = Character.toString(tile.charAt(4)) + Character.toString(tile.charAt(5));
                card = Integer.parseInt(temp);

               valid = board.VerifyHumanChoice(card);
            }

            if(valid){
                //Make card the top card
                String leftCard = "leftcard";
                int idOriginal = getResources().getIdentifier(leftCard, "id", getPackageName());
                ImageButton toChange = (ImageButton) findViewById(idOriginal);

                System.out.println("CARD TO SET " + board.GetTopTrashCard());
                String mDrawableName = board.GetTopTrashCard();
                Integer resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

                toChange.setBackgroundResource(resID);

                //Remove the card from the human hand
                board.RemoveCardFromHuman(card);

                //Reprint human hand
                PrintHumanHand();

                //Check if human won
                if(board.GetSizeOfHumanHand() == 0){

                }
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

    private void PrintComputerHand(){
        String tile = "computerCards";
        String cardNum = Integer.toString(board.GetSizeOfComputerHand());

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
