package com.example.arielpc.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TicTacToeEasy extends AppCompatActivity {
    // Jugador 1 = X y 2 = O
    private int player = 1;

    //hay Ganador = 1
    private int win = 0;

    //Para poner un valor cuando marquen la celda ya sea 1 o 2
    private int[][] valuesPlayer = new int[3][3];

    //Para tener todos los valores de las imagenes
    private int[] idImagenes = new int[]{R.id.imgCelda00,R.id.imgCelda01,R.id.imgCelda02,R.id.imgCelda10,
            R.id.imgCelda11,R.id.imgCelda12,R.id.imgCelda20,R.id.imgCelda21,R.id.imgCelda22};
    private ImageView[] imagenesV = new ImageView[9];
    private ImageView[][] imagenesV2 = new ImageView[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_easy);

        //For para guardar todas las imagenes en una sola Variable
        for (int i = 0; i < 9; i++) {
            int id = idImagenes[i];
            imagenesV[i] = (ImageView)findViewById(id);
        }

        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int id = idImagenes[x];
                imagenesV2[i][j] = (ImageView) findViewById(id);
                x++;
            }
        }

    }

    public void cCelda00 (View v) {
        clickCelda(0,0);
    }

    public void cCelda01 (View v) {
        clickCelda(0,1);
    }

    public void cCelda02 (View v) {
        clickCelda(0,2);
    }

    public void cCelda10 (View v) {
        clickCelda(1,0);
    }

    public void cCelda11 (View v) {
        clickCelda(1,1);
    }

    public void cCelda12 (View v) {
        clickCelda(1,2);
    }

    public void cCelda20 (View v) {
        clickCelda(2,0);
    }

    public void cCelda21 (View v) {
        clickCelda(2,1);
    }

    public void cCelda22 (View v) {
        clickCelda(2,2);
    }

    public void clickCelda (int fila, int col) {
        //ImageView celdaX = (ImageView)findViewById(idImagenes[id]);
        ImageView celdaX = imagenesV2[fila][col];

        if (player==1){
            celdaX.setImageResource(R.drawable.equix);
            celdaX.setClickable(false);
            valuesPlayer[fila][col] = 1;
            player++;
            ganar();
            machine();
        }
    }

    public void limpiar() {
        for (ImageView forImagen : imagenesV) {
            forImagen.setImageDrawable(null);
            forImagen.setClickable(true);
        }

        player = 1;
        win = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                valuesPlayer[i][j] = 0;
            }
        }
    }

    public void noClick() {
        for (ImageView forImagen : imagenesV) {
            forImagen.setClickable(false);
        }
    }

    public void cReset(View v) {
        limpiar();
    }

    public void cExit(View v) {
        finish();
    }

    public void ganar() {

        String winX = "El Ganador es X";
        String winO = "El Ganador es O";


        //Horizontal
        for (int i = 0; i < 3; i++) {
            if (valuesPlayer[i][0] == 1 && valuesPlayer[i][1] == 1 && valuesPlayer[i][2] == 1) {
                alerta(winX);
                noClick();
                win = 1;
            }
            if (valuesPlayer[i][0] == 2 && valuesPlayer[i][1] == 2 && valuesPlayer[i][2] == 2) {
                alerta(winO);
                noClick();
                win = 1;
            }
        }

        //Vertical
        for (int i = 0; i < 3; i++) {
            if (valuesPlayer[0][i] == 1 && valuesPlayer[1][i] == 1 && valuesPlayer[2][i] == 1) {
                alerta(winX);
                noClick();
                win = 1;
            }
            if (valuesPlayer[0][i] == 2 && valuesPlayer[1][i] == 2 && valuesPlayer[2][i] == 2) {
                alerta(winO);
                noClick();
                win = 1;
            }
        }

        //Diagonal
        if ((valuesPlayer[0][0] == 1 && valuesPlayer[1][1] == 1 && valuesPlayer[2][2] == 1) ||
                (valuesPlayer[0][2] == 1 && valuesPlayer[1][1] == 1 && valuesPlayer[2][0] == 1)) {
            alerta(winX);
            noClick();
            win = 1;
        }
        if ((valuesPlayer[0][0] == 2 && valuesPlayer[1][1] == 2 && valuesPlayer[2][2] == 2) ||
                (valuesPlayer[0][2] == 2 && valuesPlayer[1][1] == 2 && valuesPlayer[2][0] == 2)) {
            alerta(winO);
            noClick();
            win = 1;
        }

        //Empate
        if (valuesPlayer[0][0] != 0 && valuesPlayer[0][1] != 0 && valuesPlayer[0][2] != 0 &&
                valuesPlayer[1][0] != 0 && valuesPlayer[1][1] != 0 && valuesPlayer[1][2] != 0 &&
                valuesPlayer[2][0] != 0 && valuesPlayer[2][1] != 0 && valuesPlayer[2][2] != 0 &&
                win == 0) {
            alerta("Empate");
            noClick();
        }
    }

    public void alerta (String Mensaje) {
        AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("TicTacToe")
                .setMessage(Mensaje)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        limpiar();
                    }
                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        finish();
                    }
                })
                .create();
        alert.show();
        SystemClock.sleep(300);
    }

    public void machine(){
        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                ImageView celdaX = imagenesV2[f][c];
                if (player == 2) {
                    if (valuesPlayer[f][c] == 0 && win == 0) {
                        //SystemClock.sleep(500);
                        celdaX.setImageResource(R.drawable.circulo);
                        celdaX.setClickable(false);
                        valuesPlayer[f][c] = 2;
                        player--;
                        ganar();
                    }
                }
            }
        }
    }

}
