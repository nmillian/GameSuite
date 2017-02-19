package com.example.nicole.gamesuite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BattleshipActivity extends AppCompatActivity {

    private String startRow;
    private String startColumn;

    private String endRow;
    private String endColumn;

    private String position; //The piece being put down
    private String playing; //Whether playing the game or placing ships down

    private BattleshipBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battleship);
    }

    public BattleshipActivity(){
        startRow = "0";
        startColumn = "0";

        endRow = "0";
        endColumn = "0";

        position = "aircraft"; //the first piece being put down
        playing = "place"; //or play for playing

        board = new BattleshipBoard();

    }

    public void hTileClick(View view){

        //Check if it's the placement stage, the only time the human should be hitting a human tile is when placing ships
        if(playing.equals("place")){

            if(startRow.equals("0")) {
                //Get the id of the tile clicked
                String tile = getResources().getResourceEntryName(view.getId());

                //Get the tile row and column
                startRow = Character.toString(tile.charAt(4));
                startColumn = Character.toString(tile.charAt(5));

                //Set tile to blue
                view.setBackgroundResource(R.drawable.bluesquaregrid);
            }

            else{
                String tile = getResources().getResourceEntryName(view.getId());

                //Get the tile row and column
                endRow = Character.toString(tile.charAt(4));
                endColumn = Character.toString(tile.charAt(5));

                //Validate
                if(validateTiles()){
                    //Color in any middle pieces to blue and set end to blue

                    //move on to the next piece placement
                    if(position.equals("aircraft")){
                        //Reset tiles
                        startRow = "0";
                        startColumn = "0";

                        endRow = "0";
                        endColumn = "0";

                        String textNext = "shipType";
                        int idOriginal = getResources().getIdentifier(textNext, "id", getPackageName());

                        TextView toChange = (TextView) findViewById(idOriginal);
                        toChange.setText("Battleship - Size 4");
                        position = "battleship";
                    }

                    else if(position.equals("battleship")){
                        //Reset tiles
                        startRow = "0";
                        startColumn = "0";

                        endRow = "0";
                        endColumn = "0";

                        String textNext = "shipType";
                        int idOriginal = getResources().getIdentifier(textNext, "id", getPackageName());

                        TextView toChange = (TextView) findViewById(idOriginal);
                        toChange.setText("Submarine - Size 3");

                        position = "submarine";
                    }

                    else if(position.equals("submarine")){
                        //Reset tiles
                        startRow = "0";
                        startColumn = "0";

                        endRow = "0";
                        endColumn = "0";

                        String textNext = "shipType";
                        int idOriginal = getResources().getIdentifier(textNext, "id", getPackageName());

                        TextView toChange = (TextView) findViewById(idOriginal);
                        toChange.setText("Cruiser - Size 3");
                        position = "cruiser";
                    }

                    else if(position.equals("cruiser")){
                        //Reset tiles
                        startRow = "0";
                        startColumn = "0";

                        endRow = "0";
                        endColumn = "0";

                        String textNext = "shipType";
                        int idOriginal = getResources().getIdentifier(textNext, "id", getPackageName());

                        TextView toChange = (TextView) findViewById(idOriginal);
                        toChange.setText("Destroyer - Size 2");

                        position = "destroyer";
                    }

                    else if(position.equals("destroyer")){
                        playing = "play";
                    }
                }

                //There was an error
                else {
                    Log.d("ERR", "ERROR SOMEWHERE");
                    String resetTile = "Tile" + startRow + startColumn;

                    if(board.GetPieceAtSpaceHuman(startRow + startColumn).equals("B")){
                        //Reset everything and pick again

                        int idOriginal = getResources().getIdentifier(resetTile, "id", getPackageName());

                        ImageButton toChange = (ImageButton) findViewById(idOriginal);
                        toChange.setBackgroundResource(R.drawable.squaregrid);

                    }

                    //Reset tiles
                    startRow = "0";
                    startColumn = "0";

                    endRow = "0";
                    endColumn = "0";
                }
            }
        }

        //Otherwise it's the playing stage
    }

    public boolean validateTiles(){

        //Check if they selected the same tile
        if (startRow.equals(endRow) && startColumn.equals(endColumn)) {
            return false;
        }

        //Check if they're in the same row - horizontal
        else if (startRow.equals(endRow)) {
            //Check which col is bigger before doing math
            Integer startColInt = Integer.parseInt(startColumn);
            Integer endColInt = Integer.parseInt(endColumn);

            //Check which is bigger
            if (startColInt < endColInt) {
                //Check that total amount of tiles being selected is correct

                if (position.equals("aircraft")) {
                    if ((endColInt - startColInt) != 4) {
                        //error
                        return false;
                    }
                }

                else if(position.equals("battleship")){
                    if((endColInt - startColInt) != 3){
                        return false;
                    }
                }

                else if(position.equals("submarine")){
                    if((endColInt - startColInt) != 2){
                        return false;
                    }
                }

                else if(position.equals("cruiser")){
                    if((endColInt - startColInt) != 2){
                        return false;
                    }
                }

                else if(position.equals("destroyer")){
                    if((endColInt - startColInt) != 1){
                        return false;
                    }
                }

                //Check that all the tiles are empty
                for(int i = startColInt; i <= endColInt; i++){
                    String tile = startRow + i;

                    //Piece already at space
                    if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                        return false;
                    }
                }

                for (int i = startColInt; i <= endColInt; i++) {
                    //Change color
                    String tile = "Tile" + startRow + i;

                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
                    ImageButton toChange = (ImageButton) findViewById(idOriginal);

                    toChange.setBackgroundResource(R.drawable.bluesquaregrid);

                    String basicTile = startRow + Integer.toString(i);
                    board.SetShipHuman(basicTile);
                }

                return true;
            }

            else {

                if (position.equals("aircraft")) {
                    if ((startColInt - endColInt) != 4) {
                        //error
                        return false;
                    }
                }
                else if (position.equals("battleship")) {
                    if ((startColInt - endColInt) != 3) {
                        //error
                        return false;
                    }
                }
                else if (position.equals("submarine")) {
                    if ((startColInt - endColInt) != 2) {
                        //error
                        return false;
                    }
                }
                else if (position.equals("cruiser")) {
                    if ((startColInt - endColInt) != 2) {
                        //error
                        return false;
                    }
                }
                else if (position.equals("destroyer")) {
                    if ((startColInt - endColInt) != 1) {
                        //error
                        return false;
                    }
                }

                for(int i = endColInt; i <= startColInt; i++){
                    String tile = startRow + i;
                    //Piece already at space
                    if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                        return false;
                    }
                }

                for (int i = endColInt; i <= startColInt; i++) {
                    //Change color
                    String tile = "Tile" + startRow + i;
                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
                    ImageButton toChange = (ImageButton) findViewById(idOriginal);

                    toChange.setBackgroundResource(R.drawable.bluesquaregrid);

                    String basicTile = startRow + Integer.toString(i);
                    board.SetShipHuman(basicTile);
                }

                return true;
            }
        }

        //Check if they're in the same column - vertical
        else if (startColumn.equals(endColumn)) {
            //Check which row is bigger before doing math
            Integer startRowInt = Integer.parseInt(startRow);
            Integer endRowInt = Integer.parseInt(endRow);

            //Check which is bigger
            if (startRowInt < endRowInt) {
                //Check that total amount of tiles being selected is correct

                if (position.equals("aircraft")) {
                    if ((endRowInt - startRowInt) != 4) {
                        //error
                        return false;
                    }
                }

                else if(position.equals("battleship")){
                    if((endRowInt - startRowInt) != 3){
                        return false;
                    }
                }

                else if(position.equals("submarine")){
                    if((endRowInt - startRowInt) != 2){
                        return false;
                    }
                }

                else if(position.equals("cruiser")){
                    if((endRowInt - startRowInt) != 2){
                        return false;
                    }
                }

                else if(position.equals("destroyer")){
                    if((endRowInt - startRowInt) != 1){
                        return false;
                    }
                }

                for(int i = startRowInt; i <= endRowInt; i++){
                    String tile = i + startColumn;
                    //Piece already at space
                    if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                        return false;
                    }
                }

                for (int i = startRowInt; i <= endRowInt; i++) {
                    //Change color
                    String tile = "Tile" + i + startColumn;
                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

                    ImageButton toChange = (ImageButton) findViewById(idOriginal);
                    toChange.setBackgroundResource(R.drawable.bluesquaregrid);

                    String basicTile = Integer.toString(i) + startColumn;
                    board.SetShipHuman(basicTile);
                }

                return true;
            }

            else {
                if (position.equals("aircraft")) {
                    if ((startRowInt - endRowInt) != 4) {
                        //error
                        return false;
                    }
                }
                else if (position.equals("battleship")) {
                    if ((startRowInt - endRowInt) != 3) {
                        //error
                        return false;
                    }
                }
                else if (position.equals("submarine")) {
                    if ((startRowInt - endRowInt) != 2) {
                        //error
                        return false;
                    }
                }
                else if (position.equals("cruiser")) {
                    if ((startRowInt - endRowInt) != 2) {
                        //error
                        return false;
                    }
                }
                else if (position.equals("destroyer")) {
                    if ((startRowInt - endRowInt) != 1) {
                        //error
                        return false;
                    }
                }

                for(int i = endRowInt; i <= startRowInt; i++){
                    String tile = i + startColumn;
                    //Piece already at space
                    if(board.GetPieceAtSpaceHuman(tile).equals("S")){
                        return false;
                    }
                }

                for (int i = endRowInt; i <= startRowInt; i++) {
                    //Change color
                    String tile = "Tile" + i + startColumn;
                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
                    ImageButton toChange = (ImageButton) findViewById(idOriginal);

                    toChange.setBackgroundResource(R.drawable.bluesquaregrid);

                    String basicTile = Integer.toString(i) + startColumn;
                    board.SetShipHuman(basicTile);
                }

                return true;
            }

        }


        return false;
    }

    public void cTileClick(View view){
        Integer shipsLeft;

        String row;
        String column;
        String tile;

        if(playing.equals("play")){
             tile = getResources().getResourceEntryName(view.getId());

            //Get the tile row and column
            row = Character.toString(tile.charAt(5));
            column = Character.toString(tile.charAt(6));

            tile = row + column;

            if(board.CheckForComputerShipHit(tile).equals("S")){
                //Set tile to hit square
                view.setBackgroundResource(R.drawable.hitsquaregrid);
                //Set to hit in the hashtable
                board.SetShipHitComputer(tile);
                //Switch turns
            }
            else if(board.CheckForComputerShipHit(tile).equals("B")){
                view.setBackgroundResource(R.drawable.redsquaregrid);
                board.SetShipHitComputer(tile);
            }

            else{
                //hit a tile that already was hit
            }

            shipsLeft = board.GetNumberOfComputerShipTiles();

            if(shipsLeft.equals(0)){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("You won by hitting all ships. Play again?");
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

        }
    }
}
