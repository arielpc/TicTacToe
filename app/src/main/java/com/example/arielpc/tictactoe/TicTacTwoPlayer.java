package com.example.arielpc.tictactoe;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class TicTacTwoPlayer extends AppCompatActivity {

    // Jugador 1 = X y 0 = O
    private int player = 1;

    //Para poner un valor cuando marquen la celda ya sea 1 o 0
    private int[][] valuesPlayer = new int[3][3];

    //Para tener todos los valores de las imagenes
    private int[] idImagenes = new int[]{R.id.imgCelda00,R.id.imgCelda01,R.id.imgCelda02,R.id.imgCelda10,
                                         R.id.imgCelda11,R.id.imgCelda12,R.id.imgCelda20,R.id.imgCelda21,R.id.imgCelda22};
    private ImageView[] imagenesV = new ImageView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_two_player);

        //For para guardar todas las imagenes en una sola Variable
        for (int i = 0; i < 9; i++) {
            int id = idImagenes[i];
            imagenesV[i] = (ImageView)findViewById(id);
        }
    }

    public void limpiar() {
        for (ImageView forImagen : imagenesV) {
            forImagen.setImageDrawable(null);
            forImagen.setClickable(true);
        }
        player = 1;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                valuesPlayer[i][j] = 0;
            }
        }
    }

    public void cReset(View v){
        limpiar();
    }

    public void ganar() {
        //Horizontal
        for (int i = 0; i < 3; i++){
            if (valuesPlayer[i][0] == 1 && valuesPlayer[i][1] == 1 && valuesPlayer[i][2] == 1) {
                Toast notificar = Toast.makeText(this, "El jugador X ha ganado", Toast.LENGTH_LONG);
                notificar.show();
            }
            if (valuesPlayer[i][0] == 2 && valuesPlayer[i][1] == 2 && valuesPlayer[i][2] == 2) {
                Toast notificar = Toast.makeText(this, "El jugador O ha ganado", Toast.LENGTH_LONG);
                notificar.show();
            }
        }

        //Vertical
        for (int i = 0; i < 3; i++){
            if (valuesPlayer[0][i] == 1 && valuesPlayer[1][i] == 1 && valuesPlayer[2][i] == 1) {
                Toast notificar = Toast.makeText(this, "El jugador X ha ganado", Toast.LENGTH_LONG);
                notificar.show();
            }
            if (valuesPlayer[0][i] == 2 && valuesPlayer[1][i] == 2 && valuesPlayer[2][i] == 2) {
                Toast notificar = Toast.makeText(this, "El jugador O ha ganado", Toast.LENGTH_LONG);
                notificar.show();
            }
        }

        //Diagonal
        if ( (valuesPlayer[0][0] == 1 && valuesPlayer[1][1] == 1 && valuesPlayer[2][2] == 1) ||
             (valuesPlayer[0][2] == 1 && valuesPlayer[1][1] == 1 && valuesPlayer[2][0] == 1) ) {
            Toast notificar = Toast.makeText(this, "El jugador X ha ganado", Toast.LENGTH_LONG);
            notificar.show();
        }
        if ( (valuesPlayer[0][0] == 2 && valuesPlayer[1][1] == 2 && valuesPlayer[2][2] == 2) ||
                (valuesPlayer[0][2] == 2 && valuesPlayer[1][1] == 2 && valuesPlayer[2][0] == 2) ) {
            Toast notificar = Toast.makeText(this, "El jugador O ha ganado", Toast.LENGTH_LONG);
            notificar.show();
        }

        //limpiar();
    }

    public void cCelda00 (View v) {
        ImageView celda00 = (ImageView)findViewById(R.id.imgCelda00);

        if (player==1){
            celda00.setImageResource(R.drawable.equix);
            celda00.setClickable(false);
            valuesPlayer[0][0] = 1;
            player++;
        } else
        {
            celda00.setImageResource(R.drawable.circulo);
            celda00.setClickable(false);
            valuesPlayer[0][0] = 2;
            player--;
        }

        ganar();
    }

    public void cCelda01 (View v) {
        ImageView celda01 = (ImageView)findViewById(R.id.imgCelda01);

        if (player==1){
            celda01.setImageResource(R.drawable.equix);
            celda01.setClickable(false);
            valuesPlayer[0][1] = 1;
            player++;
        } else
        {
            celda01.setImageResource(R.drawable.circulo);
            celda01.setClickable(false);
            valuesPlayer[0][1] = 2;
            player--;
        }
        ganar();
    }

    public void cCelda02 (View v) {

        ImageView celda02 = (ImageView)findViewById(R.id.imgCelda02);

        if (player==1){
            celda02.setImageResource(R.drawable.equix);
            celda02.setClickable(false);
            valuesPlayer[0][2] = 1;
            player++;
        } else
        {
            celda02.setImageResource(R.drawable.circulo);
            celda02.setClickable(false);
            valuesPlayer[0][2] = 2;
            player--;
        }
        ganar();
    }

    public void cCelda10 (View v) {
        ImageView celda10 = (ImageView)findViewById(R.id.imgCelda10);

        if (player==1){
            celda10.setImageResource(R.drawable.equix);
            celda10.setClickable(false);
            valuesPlayer[1][0] = 1;
            player++;
        } else
        {
            celda10.setImageResource(R.drawable.circulo);
            celda10.setClickable(false);
            valuesPlayer[1][0] = 2;
            player--;
        }
        ganar();
    }

    public void cCelda11 (View v) {
        ImageView celda11 = (ImageView)findViewById(R.id.imgCelda11);

        if (player==1){
            celda11.setImageResource(R.drawable.equix);
            celda11.setClickable(false);
            valuesPlayer[1][1] = 1;
            player++;
        } else
        {
            celda11.setImageResource(R.drawable.circulo);
            celda11.setClickable(false);
            valuesPlayer[1][1] = 2;
            player--;
        }
        ganar();
    }

    public void cCelda12 (View v) {
        ImageView celda12 = (ImageView)findViewById(R.id.imgCelda12);

        if (player==1){
            celda12.setImageResource(R.drawable.equix);
            celda12.setClickable(false);
            valuesPlayer[1][2] = 1;
            player++;
        } else
        {
            celda12.setImageResource(R.drawable.circulo);
            celda12.setClickable(false);
            valuesPlayer[1][2] = 2;
            player--;
        }
        ganar();
    }

    public void cCelda20 (View v) {
        ImageView celda20 = (ImageView)findViewById(R.id.imgCelda20);

        if (player==1){
            celda20.setImageResource(R.drawable.equix);
            celda20.setClickable(false);
            valuesPlayer[2][0] = 1;
            player++;
        } else
        {
            celda20.setImageResource(R.drawable.circulo);
            celda20.setClickable(false);
            valuesPlayer[2][0] = 2;
            player--;
        }
        ganar();
    }

    public void cCelda21 (View v) {
        ImageView celda21 = (ImageView)findViewById(R.id.imgCelda21);

        if (player==1){
            celda21.setImageResource(R.drawable.equix);
            celda21.setClickable(false);
            valuesPlayer[2][1] = 1;
            player++;
        } else
        {
            celda21.setImageResource(R.drawable.circulo);
            celda21.setClickable(false);
            valuesPlayer[2][1] = 2;
            player--;
        }
        ganar();
    }

    public void cCelda22 (View v) {
        ImageView celda22 = (ImageView)findViewById(R.id.imgCelda22);

        if (player==1){
            celda22.setImageResource(R.drawable.equix);
            celda22.setClickable(false);
            valuesPlayer[2][2] = 1;
            player++;
        } else
        {
            celda22.setImageResource(R.drawable.circulo);
            celda22.setClickable(false);
            valuesPlayer[2][2] = 2;
            player--;
        }
        ganar();
    }
}

