package com.example.arielpc.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class TicTacToeEasy extends AppCompatActivity {
    // Jugador 1 = X y 2 = O
    private int player = 1;

    //Para poner un valor cuando marquen la celda ya sea 1 o 2
    private int[][] valuesPlayer = new int[3][3];

    //Para tener todos los valores de las imagenes
    private int[] idImagenes = new int[]{R.id.imgCelda00,R.id.imgCelda01,R.id.imgCelda02,R.id.imgCelda10,
            R.id.imgCelda11,R.id.imgCelda12,R.id.imgCelda20,R.id.imgCelda21,R.id.imgCelda22};
    private ImageView[] imagenesV = new ImageView[9];
    private ImageView[][] imagenesV2 = new ImageView[3][3];

    Handler handler = new Handler();

    private Spinner spLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_easy);

        String level[] = {"1","2"};
        spLevel = (Spinner)findViewById(R.id.spLevel);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, level);
        spLevel.setAdapter(adapter);

        //For para guardar todas las imagenes en un Array[]
        for (int i = 0; i < 9; i++) {
            int id = idImagenes[i];
            imagenesV[i] = (ImageView)findViewById(id);
        }

        //Otro for para guardar imagenes en un array[][]
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int id = idImagenes[x];
                imagenesV2[i][j] = (ImageView) findViewById(id);
                x++;
            }
        }

        limpiar();
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
        ImageView celdaX = imagenesV2[fila][col];
        String sLevel =spLevel.getSelectedItem().toString();

        if (player==1 && valuesPlayer[fila][col] == 0){
            celdaX.setImageResource(R.drawable.equix);
            celdaX.setClickable(false);
            valuesPlayer[fila][col] = 1;
            player++;
            ganador();
            if (sLevel.equals("1")) machine();
            else machineMiniMax();
        }
    }

    public void cReset(View v) {
        limpiar();
    }

    public void cExit(View v) {
        finish();
    }

    public void ganador() {
        int vGanador = ganaPartida();
        if (vGanador == 1) {
            alerta("El Ganador es X");
            noClick();
        }
        if (vGanador == 2) {
            alerta("El Ganador es O");
            noClick();
        }
        if (tableroCompleto() && vGanador == -1){
            alerta("Hay un Empate");
            noClick();
        }
    }

    public void noClick() {
        for (ImageView forImagen : imagenesV) {
            forImagen.setClickable(false);
        }
    }

    public int ganaPartida(){

        for (int n=0;n<3;n++){
            //Horizontal
            if (valuesPlayer[n][0] != 0 && valuesPlayer[n][0] == valuesPlayer[n][1]
                    && valuesPlayer[n][0] == valuesPlayer[n][2])
                return valuesPlayer[n][0];

            //Vertical
            if (valuesPlayer[0][n] != 0 && valuesPlayer[0][n] == valuesPlayer[1][n]
                    && valuesPlayer[0][n] == valuesPlayer[2][n])
                return valuesPlayer[0][n];
        }

        //Diagonales
        if (valuesPlayer[0][0] != 0 && valuesPlayer[0][0] == valuesPlayer[1][1]
                && valuesPlayer[0][0] == valuesPlayer[2][2])
            return valuesPlayer[0][0];
        if (valuesPlayer[0][2] != 0 && valuesPlayer[0][2] == valuesPlayer[1][1]
                && valuesPlayer[0][2] == valuesPlayer[2][0])
            return valuesPlayer[0][2];

        return -1;
    }

    public void alerta (String Mensaje) {
                AlertDialog alert = new AlertDialog.Builder(this)
                        .setTitle("TicTacToe")
                        .setMessage(Mensaje)
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                limpiar();
                            }
                        })
                        .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .create();
                alert.show();
    }

    public void valImagenes (int f, int c) {
        if (valuesPlayer[f][c] == 0) {
            ImageView celdaX = imagenesV2[f][c];
            celdaX.setImageResource(R.drawable.circulo);
            celdaX.setClickable(false);
            valuesPlayer[f][c] = 2;
            player--;
        }
    }

    private boolean tableroCompleto(){
        for (int n=0;n<3;n++)
            for (int m=0;m<3;m++)
                if (valuesPlayer[n][m]==0)
                    return false;
        return true;
    }

    private boolean finPartida(){
        return tableroCompleto() || ganaPartida()!=-1;
    }

    public void machine(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                for (int f = 0; f < 3; f++) {
                    for (int c = 0; c < 3; c++) {
                        if (player == 2) {
                            if (valuesPlayer[f][c] == 0 && ganaPartida()==-1) {
                                valImagenes(f, c);
                                ganador();
                            }
                        }
                    }
                }

            }
        }, 400);
    }

    private int max(){

        if (finPartida()){
            if (ganaPartida()!=-1) return -1;
            else return 0;
        }

        int v = Integer.MIN_VALUE;
        int aux;
        for (int n = 0; n < 3; n++) {
            for (int m = 0; m < 3; m++) {
                if (valuesPlayer[n][m] == 0) {
                    valuesPlayer[n][m] = 2;
                    aux = min();
                    if (aux > v) v = aux;
                    valuesPlayer[n][m] = 0;
                }
            }
        }
        return v;
}

    private int min(){
        if (finPartida()){
            if (ganaPartida()!=-1) return 1;
            else return 0;
        }

        int v = Integer.MAX_VALUE;
        int aux;
        for (int n = 0; n < 3; n++) {
            for (int m = 0; m < 3; m++) {
                if (valuesPlayer[n][m] == 0) {
                    valuesPlayer[n][m] = 1;
                    aux = max();
                    if (aux < v) v = aux;
                    valuesPlayer[n][m] = 0;
                }
            }
        }
        return v;
    }

    private void machineMiniMax(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!finPartida()) {
                    int f = 0, c = 0;
                    int v = Integer.MIN_VALUE;
                    int aux;
                    for (int n = 0; n < 3; n++) {
                        for (int m = 0; m < 3; m++) {
                            if (valuesPlayer[n][m] == 0) {
                                valuesPlayer[n][m] = 2;
                                aux = min();
                                if (aux > v) {
                                    v = aux;
                                    f = n;
                                    c = m;
                                }
                                valuesPlayer[n][m] = 0;
                            }
                        }
                    }
                    valImagenes(f, c);
                    ganador();
                }
            }

        }, 400);
    }

    /*
    public void machineMedium() {
                if (numJugada == 1) {
                    if (valuesPlayer[1][1] == 0) {
                        valImagenes(1, 1);
                    } else {
                        valImagenes(0, 0);
                    }
                }

                if (numJugada == 3) {
                    if (valuesPlayer[1][1] == 1) {
                        //centrales
                        if (valuesPlayer[0][1] == 1) {
                            valImagenes(2, 1);
                        } else if (valuesPlayer[1][0] == 1) {
                            valImagenes(1, 2);
                        } else if (valuesPlayer[1][2] == 1) {
                            valImagenes(1, 0);
                        } else if (valuesPlayer[2][1] == 1) {
                            valImagenes(0, 1);
                        }

                        //esquinas
                        if (valuesPlayer[0][0] == 1) {
                            valImagenes(2, 2);
                        } else if (valuesPlayer[0][2] == 1) {
                            valImagenes(2, 0);
                        } else if (valuesPlayer[2][0] == 1) {
                            valImagenes(0, 2);
                        } else if (valuesPlayer[2][2] == 1) {
                            valImagenes(2, 0);
                        }
                    } else {
                        if (valuesPlayer[0][0] == 1 && valuesPlayer[0][1] == 1) {
                            valImagenes(0, 2);
                        }
                        if (valuesPlayer[0][0] == 1 && valuesPlayer[0][2] == 1) {
                            valImagenes(0, 1);
                        }
                        if (valuesPlayer[0][1] == 1 && valuesPlayer[0][2] == 1) {
                            valImagenes(0, 0);
                        }
                        if (valuesPlayer[2][0] == 1 && valuesPlayer[2][1] == 1) {
                            valImagenes(2, 2);
                        }
                        if (valuesPlayer[2][0] == 1 && valuesPlayer[2][2] == 1) {
                            valImagenes(2, 1);
                        }
                        if (valuesPlayer[2][1] == 1 && valuesPlayer[2][2] == 1) {
                            valImagenes(2, 0);
                        }

                        if (valuesPlayer[0][0] == 1 && valuesPlayer[1][0] == 1) {
                            valImagenes(2, 0);
                        }
                        if (valuesPlayer[0][0] == 1 && valuesPlayer[2][0] == 1) {
                            valImagenes(1, 0);
                        }
                        if (valuesPlayer[1][0] == 1 && valuesPlayer[2][0] == 1) {
                            valImagenes(0, 0);
                        }
                        if (valuesPlayer[0][2] == 1 && valuesPlayer[1][2] == 1) {
                            valImagenes(2, 2);
                        }
                        if (valuesPlayer[0][2] == 1 && valuesPlayer[2][2] == 1) {
                            valImagenes(1, 2);
                        }
                        if (valuesPlayer[1][2] == 1 && valuesPlayer[2][2] == 1) {
                            valImagenes(0, 2);
                        }
                        if (valuesPlayer[0][2] == 1 && valuesPlayer[2][0] == 1) {
                            valImagenes(2, 2);
                        }
                        if (valuesPlayer[0][0] == 1 && valuesPlayer[2][2] == 1) {
                            valImagenes(2, 0);
                        }
                        if (valuesPlayer[0][0] == 1 && valuesPlayer[1][2] == 1) {
                            valImagenes(2, 0);
                        }
                        if (valuesPlayer[0][0] == 1 && valuesPlayer[2][1] == 1) {
                            valImagenes(2, 0);
                        }
                        if (valuesPlayer[0][2] == 1 && valuesPlayer[1][0] == 1) {
                            valImagenes(2, 2);
                        }
                        if (valuesPlayer[0][2] == 1 && valuesPlayer[2][1] == 1) {
                            valImagenes(2, 2);
                        }
                        if (valuesPlayer[2][0] == 1 && valuesPlayer[0][1] == 1) {
                            valImagenes(2, 2);
                        }
                        if (valuesPlayer[2][0] == 1 && valuesPlayer[1][2] == 1) {
                            valImagenes(2, 2);
                        }
                        if (valuesPlayer[2][2] == 1 && valuesPlayer[0][1] == 1) {
                            valImagenes(2, 0);
                        }
                        if (valuesPlayer[2][2] == 1 && valuesPlayer[1][0] == 1) {
                            valImagenes(2, 0);
                        }

                        if ((valuesPlayer[0][1] == 1 && valuesPlayer[2][1] == 1) ||
                                (valuesPlayer[0][1] == 1 && valuesPlayer[1][2] == 1) ||
                                (valuesPlayer[0][1] == 1 && valuesPlayer[1][0] == 1) ||
                                (valuesPlayer[1][0] == 1 && valuesPlayer[1][2] == 1) ||
                                (valuesPlayer[2][1] == 1 && valuesPlayer[1][0] == 1) ||
                                (valuesPlayer[2][1] == 1 && valuesPlayer[1][2] == 1)) {
                            valImagenes(2, 2);
                        }
                    }
                }


                if (numJugada > 5) {
                    machine();
    }
    */
}
