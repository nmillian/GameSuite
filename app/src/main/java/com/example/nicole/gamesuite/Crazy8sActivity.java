package com.example.nicole.gamesuite;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class Crazy8sActivity extends AppCompatActivity {

    private Crazy8sBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy8s);
    }

    public Crazy8sActivity(){
        board = new Crazy8sBoard();
    }

    public void leftCardClick(View view) {

        Random rand = new Random();
        int num = 1 + rand.nextInt((13 - 1) + 1);

        String mDrawableName = board.getTopCard(num);
        int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
        view.setBackgroundResource(resID);

        /*
        String tile = "imageButton";
        int idOriginal = getResources().getIdentifier(tile, "id", getPackageName());
        ImageButton toChange = (ImageButton) findViewById(idOriginal);

        mDrawableName = "redsquaregrid";
        resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

        toChange.setBackgroundResource(resID);
        */
    }

}
