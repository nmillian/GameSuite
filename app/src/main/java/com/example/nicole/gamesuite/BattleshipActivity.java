package com.example.nicole.gamesuite;

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

    private Integer totalShipsPlaced;

    private String position = "aircraft";

    private String playing = "place"; //or play for playing

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

    }

    public void hTileClick(View view){

        //Check if it's the placement stage
        if(playing.equals("place")){

            if(startRow.equals("0")) {
                //Get the id of the tile clicked
                String tile = getResources().getResourceEntryName(view.getId());

                //Get the tile row and column
                startRow = Character.toString(tile.charAt(4));
                startColumn = Character.toString(tile.charAt(5));

                Log.d("STARTR", startRow);
                //Set tile to blue
                view.setBackgroundResource(R.drawable.bluesquaregrid);
            }

            else{
                String tile = getResources().getResourceEntryName(view.getId());

                //Get the tile row and column
                endRow = Character.toString(tile.charAt(4));
                endColumn = Character.toString(tile.charAt(5));

                Log.d("ENDR", endRow);

                //Validate
                if(validateTiles()){
                    //Color in any middle pieces to blue and set end to blue
                    view.setBackgroundResource(R.drawable.bluesquaregrid);

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
                    //Reset everything and pick again
                    String resetTile = "Tile" + startRow + startColumn;
                    int idOriginal = getResources().getIdentifier(resetTile, "id", getPackageName());

                    ImageButton toChange = (ImageButton) findViewById(idOriginal);
                    toChange.setBackgroundResource(R.drawable.squaregrid);

                    //Reset tiles
                    startRow = "0";
                    startColumn = "0";

                    endRow = "0";
                    endColumn = "0";

                }
            }
        }

        //Otherwise it's the playing stage
        else{

        }

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

            Log.d("STARTC", startColumn);
            Log.d("ENDC", endColumn);

            //Check which is bigger
            if (startColInt < endColInt) {
                //Check that total amount of tiles being selected is correct

                if (position.equals("aircraft")) {
                    Log.d("IN", position);
                    if ((endColInt - startColInt) != 4) {
                        //error
                        Log.d("IF", "false");
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

                for (int i = startColInt; i < endColInt; i++) {
                    //Change color
                    Log.d("COLOR", "SHOULD BE COLOR");
                    String tile = "Tile" + startRow + i;
                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

                    ImageButton toChange = (ImageButton) findViewById(idOriginal);
                    toChange.setBackgroundResource(R.drawable.bluesquaregrid);
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

                for (int i = endColInt; i < startColInt; i++) {
                    //Change color
                    String tile = "Tile" + startRow + i;
                    int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());

                    ImageButton toChange = (ImageButton) findViewById(idOriginal);
                    toChange.setBackgroundResource(R.drawable.bluesquaregrid);
                }

                return true;

            }
        }

        //Check if they're in the same column - vertical
        else if (startColumn.equals(endColumn)) {

        }


        return false;
    }

}
