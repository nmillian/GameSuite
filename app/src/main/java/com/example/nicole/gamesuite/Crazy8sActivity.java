package com.example.nicole.gamesuite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Crazy8sActivity extends AppCompatActivity {

    /* *********************************************
`   * Private class variables
    ********************************************* */
    private Crazy8sBoard board;
    private Crazy8sComputer computer;
    private Crazy8sSave crazy8sSave;

    private String gameState = "start";
    private String currentPlayer = "human";

    private String saveFileName;

    /* *********************************************
`   * Constructor
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
     * Used in order to initialize the crazy 8s activity.
     *
     * Returns:
     * None
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy8s);

        View visibility;

        Bundle extras = getIntent().getExtras();
        String isSave = extras.getString("SAVE");

        System.out.print("HELLO? ");

        //Saved game
        if(isSave.equals("YES")){
            System.out.print("IN SAVE ");

            boolean validSave;

            String fileName = extras.getString("FILENAME");
            validSave = crazy8sSave.serializationFromFile(fileName, board);

            if(validSave) {
                SetSerializedBoard();
                gameState = "play";

                visibility = findViewById(R.id.skipTurn);
                visibility.setVisibility(View.GONE);
            }

            else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("The save file was not for Crazy8s, starting a fresh game.");
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

        //Don't allow human to save untill game is set up
        else{
            visibility = findViewById(R.id.skipTurn);
            visibility.setVisibility(View.GONE);

            visibility = findViewById(R.id.save);
            visibility.setVisibility(View.GONE);
        }
    }

    /**
     * Name:
     * Crazy8sActivity
     *
     * Synopsis:
     * public Crazy8sActivity();
     * No params.
     *
     * Description:
     * The constructor used in order to initialize the board, computer, and save for the crazy 8s game.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public Crazy8sActivity(){
        board = new Crazy8sBoard();
        computer = new Crazy8sComputer();
        crazy8sSave = new Crazy8sSave();
    }

    /* *********************************************
`   * Private functions
    ********************************************* */

    /**
     * Name:
     * PrintComputerHand
     *
     * Synopsis:
     * private void PrintComputerHand();
     * No params.
     *
     * Description:
     * Used in order to update the number of cards the computer hand has.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    private void PrintComputerHand(){
        String tile = "computerCards";
        String cardNum = Integer.toString(board.GetSizeOfComputerHand());

        System.out.println("COMPUTER SIZE " + board.GetSizeOfComputerHand());

        int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        TextView toChange = (TextView) findViewById(idOriginal);

        toChange.setText(cardNum);

    }

    /**
     * Name:
     * PrintHumanHand
     *
     * Synopsis:
     * private void PrintHumanHand();
     * No params.
     *
     * Description:
     * Used in order to update which cards are displayed for the human player.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
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

    /**
     * Name:
     * ComputerRunnable
     *
     * Synopsis:
     * private Runnable ComputerRunnable = new Runnable();
     * No params.
     *
     * Description:
     * Used in order to call the ComputerTurn function on a delay so actions aren't instant.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    private Runnable ComputerRunnable = new Runnable() {
        @Override
        public void run() {
            ComputerTurn();
        }
    };

    /**
     * Name:
     * ComputerTurn
     *
     * Synopsis:
     * private void ComputerTurn();
     * No params.
     *
     * Description:
     * Used in order to decide what card the computer player should play and checks if the computer player won after making a move.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    private void ComputerTurn(){
        board.printCompHand();

        if(currentPlayer.equals("computer")){

            //Decide computer move
            if(computer.DecideMove(board)){
                UpdateTrashCard();
                PrintComputerHand();

                //Check if the computer won
                if(board.GetSizeOfComputerHand() == 0){

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("The computer won by getting rid of all cards! Play again?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ResetGame();
                                    board.ResetGame();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }

                //Computer gets to go again because it played an 8
                else if(board.GetTopTrashCard().contains("8")){
                    ComputerTurn();
                    Toast.makeText(getApplicationContext(), "The computer played an 8, playing another card.", Toast.LENGTH_SHORT).show();
                }

                //The computer played a card, now the humans turn
                else {
                    currentPlayer = "human";
                    View visibility;
                    visibility = findViewById(R.id.save);
                    visibility.setVisibility(View.VISIBLE);

                    if(board.GetDeckSize() == 0){
                        String tile = "rightcard";

                        int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
                        ImageButton toChange = (ImageButton) findViewById(idOriginal);

                        String mDrawableName = "squaregrid";
                        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

                        toChange.setBackgroundResource(resID);

                        visibility = findViewById(R.id.skipTurn);
                        visibility.setVisibility(View.VISIBLE);
                    }
                }
            }

            //The computer could not play a card
            else{
                //Check if it's impossible for either player to win
                if(board.CheckForUnwinnableCondition() == false){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("Neither player can make a move. No winner.");
                    builder1.setCancelable(false)

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

                //Check if the deck is empty
                else if(board.GetDeckSize() == 0){
                    Toast.makeText(getApplicationContext(), "The computer cannot make a move, turn passed.", Toast.LENGTH_SHORT).show();

                    String tile = "rightcard";

                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
                    ImageButton toChange = (ImageButton) findViewById(idOriginal);

                    String mDrawableName = "squaregrid";
                    int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

                    toChange.setBackgroundResource(resID);

                    currentPlayer = "human";

                    View visibility;

                    visibility = findViewById(R.id.skipTurn);
                    visibility.setVisibility(View.VISIBLE);

                    visibility = findViewById(R.id.save);
                    visibility.setVisibility(View.VISIBLE);
                }

                //Draw another card
                else {
                    //Returned false so draw another card
                    board.AddCardToComputerHand();
                    PrintComputerHand();
                    ComputerTurn();
                }
            }
        }
    }

    /* *********************************************
`   * Public functions
    ********************************************* */

    /**
     * Name:
     * ResetGame
     *
     * Synopsis:
     * public void ResetGame();
     *
     * Description:
     * Used in order to reset the game to it's original state.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/14/2017
     */
    public void ResetGame(){
        View visibility;
        visibility = findViewById(R.id.skipTurn);
        visibility.setVisibility(View.GONE);

        visibility = findViewById(R.id.save);
        visibility.setVisibility(View.GONE);

        //Set top trash card to the click to play card
        String tile = "leftcard";
        int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        ImageButton toChange = (ImageButton) findViewById(idOriginal);

        String mDrawableName = "tapcard";
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

        toChange.setBackgroundResource(resID);

        //Set the right card to the top deck card
        tile = "rightcard";
        idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        toChange = (ImageButton) findViewById(idOriginal);

        mDrawableName = "topofcard";
        resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

        toChange.setBackgroundResource(resID);

        //Clear the human cards
        //Show the cards
        for(int i = 0; i < 40; i++){
            tile = "Tile" + i;

            idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
            toChange = (ImageButton) findViewById(idOriginal);

            mDrawableName = "squaregrid";
            resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

            toChange.setBackgroundResource(resID);
        }

        //Update computer text
        tile = "computerCards";
        String cardNum = Integer.toString(board.GetSizeOfComputerHand());

        idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        TextView textToChange = (TextView) findViewById(idOriginal);

        textToChange.setText("0");

        gameState = "start";
        board.ResetGame();
    }

    /**
     * Name:
     * UpdateTrashCard
     *
     * Synopsis:
     * public void UpdateTrashCard();
     *
     * Description:
     * Used in order to update the trash card, the left card pile, with the most recent addition to the pile.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
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

    /**
     * Name:
     * SetSerializedBoard
     *
     * Synopsis:
     * public void SetSerializedBoard();
     *
     * Description:
     * Used in order to update the board with the values from a serialized file. The computer hand, human hand, trash, and deck are all updated.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void SetSerializedBoard(){
        //Set top trash card
        String tile = "leftcard";
        int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        ImageButton toChange = (ImageButton) findViewById(idOriginal);

        String mDrawableName = board.GetTopTrashCard();
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

        toChange.setBackgroundResource(resID);

        //Set human hand
        Integer humanHandSize = board.GetSizeOfHumanHand();

        //Show the cards
        for(int i = 0; i < humanHandSize; i++){
            tile = "Tile" + i;

            idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
            toChange = (ImageButton) findViewById(idOriginal);

            mDrawableName = board.GetHumanCard(i);
            resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

            toChange.setBackgroundResource(resID);
        }


        //Update computer text
        tile = "computerCards";
        String cardNum = Integer.toString(board.GetSizeOfComputerHand());

        System.out.println("COMPUTER SIZE " + board.GetSizeOfComputerHand());
        System.out.println("PRINTING COMP HAND");
        board.printCompHand();

        idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        TextView textToChange = (TextView) findViewById(idOriginal);

        textToChange.setText(cardNum);

        gameState = "play";
        currentPlayer = "human";
    }

    /**
     * Name:
     * rightCardClick
     *
     * Synopsis:
     * public void rightCardClick(View view);
     * @param view -> The crazy 8s activity view.
     *
     * Description:
     * Used in order to handle when a player taps the top of the deck of cards, and gives the player a card if possible.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
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

                    View visibility;

                    visibility = findViewById(R.id.skipTurn);
                    visibility.setVisibility(View.VISIBLE);


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

                }
                else if (board.GetSizeOfHumanHand() == 40) {
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

    /**
     * Name:
     * TileClick
     *
     * Synopsis:
     * public void TileClick(View view);
     * @param view -> The crazy 8s activity view.
     *
     * Description:
     * Used in order to handle the human player choosing a card from their hand to add to the trash pile.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("You won the game by getting rid of your hand of cards! Play again?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        ResetGame();
                                        board.ResetGame();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                    //Check if the top card is an 8
                    //Let the human place another card
                    else if(board.GetTopTrashCard().contains("8")){
                        Toast.makeText(getApplicationContext(), "Placed an 8. Place another card to set the suite.", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        //Computer's turn
                        currentPlayer = "computer";

                        //Hide save button
                        View visibility;

                        visibility = findViewById(R.id.save);
                        visibility.setVisibility(View.GONE);

                        //Add a delay to the computer playing
                        Handler compHandler = new Handler();
                        compHandler.postDelayed(ComputerRunnable, 1000);
                    }
                }
            }
        }
    }

    /**
     * Name:
     * leftCardClick
     *
     * Synopsis:
     * public void leftCardClick(View view);
     * @param view -> The crazy 8s activity view.
     *
     * Description:
     * Get the first card to be placed in the trash pile and distribute cards
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void leftCardClick(View view) {

        if(gameState.equals("start")) {
            //Get the top card
            int num = 0;

            //Show the top card
            String mDrawableName = board.GetTopTrashCard();
            System.out.println("TOP CARD AFTER LEFT CLICK" + board.GetTopTrashCard());

            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            view.setBackgroundResource(resID);

            //Remove card from deck
            board.RemoveCardFromDeck(num);

            //Print human hand
            PrintHumanHand();
            PrintComputerHand();

            gameState = "play";
            currentPlayer = "human";

            View visibility;

            visibility = findViewById(R.id.save);
            visibility.setVisibility(View.VISIBLE);
        }

    }

    /**
     * Name:
     * skipClick
     *
     * Synopsis:
     * public void skipClick(View view);
     * @param view -> The crazy 8s activity view.
     *
     * Description:
     * Used in order to skip the human player's turn. The option is hidden until the deck is at 0.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/14/2017
     */
    public void skipClick(View view){
        if(currentPlayer.equals("human")){
            currentPlayer = "computer";

            //Add a delay to the computer playing
            Handler compHandler = new Handler();
            compHandler.postDelayed(ComputerRunnable, 1000);
        }
    }

    /**
     * Name:
     * SaveGame
     *
     * Description:
     * public void SaveGame(View view);
     * @param view -> The crazy 8s activity view.
     *
     * Description:
     * Used in order to save the current game state and exit. Only available on the human players turn.
     * The human player is asked to enter a file name to save to.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/16/2017
     */
    public void SaveGame(View view){
        //Only let the human save on it's turn
        if(currentPlayer.equals("human")) {

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
                    crazy8sSave.serializationToFile(saveFileName, board);

                    //Go back to the beginning activity
                    finish();
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
}
