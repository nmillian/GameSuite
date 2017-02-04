package com.example.nicole.gamesuite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //When the user clicks the Connect 4 button
    public void connect4Click(View v){
        Intent intent = new Intent(this, Connect4Activity.class);

        startActivity(intent);
    }
}
