package com.example.arielpc.tictactoe;

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

    public void cTwoPlayer(View view) {
        Intent i = new Intent(this,TicTacTwoPlayer.class);
        startActivity(i);
    }

    public void cOnePlayer(View view) {
        Intent i = new Intent(this,TicTacToeEasy.class);
        startActivity(i);
    }


    public void cSalir(View view) {
        finish();
    }
}
