package com.example.nicole.gamesuite;

import android.content.Context;
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
    private String currentTopCard = "NULL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy8s);
    }

    public Crazy8sActivity(){
        board = new Crazy8sBoard();
    }

    public void TileClick(View view){

    }

    //Get the first card to be placed in the trash pile and distribute cards
    public void leftCardClick(View view) {

        if(gameState.equals("start")) {
            //Get the top card
            int num = 0;

            //Show the top card
            String mDrawableName = board.GetTopCard(num);
            currentTopCard = mDrawableName;
            
            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            view.setBackgroundResource(resID);

            //Remove card from deck
            board.RemoveCardFromDeck(num);

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

        for(int i = 0; i < board.GetSizeOfHumanHand(); i++){
            tile = "Tile" + i;

            int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
            ImageButton toChange = (ImageButton) findViewById(idOriginal);

            String mDrawableName = board.GetHumanCard(i);
            int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

            toChange.setBackgroundResource(resID);
        }
    }
}
