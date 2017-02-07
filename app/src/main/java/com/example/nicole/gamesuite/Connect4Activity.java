package com.example.nicole.gamesuite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Connect4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4);
    }

    public void tileClick(View view){

        //Get the id of the tile clicked
        String tile = getResources().getResourceEntryName(view.getId());

        //Get the tile row and column
        String rowString = Character.toString(tile.charAt(4));
        String columnString = Character.toString(tile.charAt(5));

        view.setBackgroundResource(R.drawable.redcircle);

    }
}
