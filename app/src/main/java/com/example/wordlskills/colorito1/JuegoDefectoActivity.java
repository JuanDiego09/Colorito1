package com.example.wordlskills.colorito1;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class JuegoDefectoActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    TextView palabra, txtdesplegadas, txtintentos, txtcorrectas, txtincorrectas, txttiempo, txtporcentaje;
    ArrayList<String> listaColores;
    ArrayList<String> listaPalabras;
    int desplegas, correctas, incorrectas, intentos = 3, porcentaje;
    int numeroP, numeroC, n, k;
    int numero[];
    int resultado[];
    Random rnd;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_defecto);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        palabra = findViewById(R.id.Palabra);
        txtdesplegadas = findViewById(R.id.textDesplegadas);
        txtintentos = findViewById(R.id.textIntentos);
        txtcorrectas = findViewById(R.id.textCorrectas);
        txtincorrectas = findViewById(R.id.textIncorrectas);
        txttiempo = findViewById(R.id.tiempo);
        txtporcentaje = findViewById(R.id.porcentaje);


        txtdesplegadas.setText("Desplegadas " + desplegas);
        txtincorrectas.setText("Incorrectas " + incorrectas);
        txtcorrectas.setText("Correctas " + correctas);
        txtintentos.setText("Intentos " + intentos);

        llenarArray();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intentos != 0) {
                    comprobar(1);
                    timer.cancel();
                } else {
                    termina();
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intentos != 0) {
                    comprobar(2);
                    timer.cancel();
                } else {
                    termina();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intentos != 0) {
                    comprobar(3);
                    timer.cancel();
                } else {
                    termina();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intentos != 0) {
                    comprobar(4);
                    timer.cancel();
                } else {
                    termina();
                }
            }
        });
    }

    private void termina() {
        Toast.makeText(getApplicationContext(), "FINALIZA", Toast.LENGTH_SHORT).show();
    }

    private void comprobar(int clic) {
        desplegas++;
        switch (clic) {
            case 1:
                if (listaColores.get(numeroC) == listaColores.get(resultado[0] - 1)) {
                    correctas++;
                } else {
                    incorrectas++;
                    intentos--;
                }
                break;
            case 2:
                if (listaColores.get(numeroC) == listaColores.get(resultado[1] - 1)) {
                    correctas++;
                } else {
                    incorrectas++;
                    intentos--;
                }
                break;
            case 3:
                if (listaColores.get(numeroC) == listaColores.get(resultado[2] - 1)) {
                    correctas++;
                } else {
                    incorrectas++;
                    intentos--;
                }
                break;
            case 4:
                if (listaColores.get(numeroC) == listaColores.get(resultado[3] - 1)) {
                    correctas++;
                } else {
                    incorrectas++;
                    intentos--;
                }
                break;

        }
        txtdesplegadas.setText("Desplegadas " + desplegas);
        txtincorrectas.setText("Incorrectas " + incorrectas);
        txtcorrectas.setText("Correctas " + correctas);
        txtintentos.setText("Intentos " + intentos);
        generarNumero();
    }


    private void llenarArray() {
        listaColores = new ArrayList<>();
        listaPalabras = new ArrayList<>();

        listaColores.add("#ffdcdc0b");
        listaColores.add("#ff0b34dc");
        listaColores.add("#faf20b22");
        listaColores.add("#fa30b707");

        listaPalabras.add("Amarillo");
        listaPalabras.add("Azul");
        listaPalabras.add("Rojo");
        listaPalabras.add("Verde");

        generarNumero();

    }

    private void generarNumero() {
        numeroC = (int) Math.floor(Math.random() * 4);
        numeroP = (int) Math.floor(Math.random() * 4);

        n = 4;
        k = n;

        numero = new int[n];
        resultado = new int[n];

        for (int i = 0; i < n; i++) {
            numero[i] = i + 1;
        }
        rnd = new Random();
        int res;
        for (int i = 0; i < n; i++) {
            res = rnd.nextInt(k);
            resultado[i] = numero[res];
            numero[res] = numero[k - 1];
            k--;
        }

        btn1.setBackgroundColor(Color.parseColor(listaColores.get(resultado[0] - 1)));
        btn2.setBackgroundColor(Color.parseColor(listaColores.get(resultado[1] - 1)));
        btn3.setBackgroundColor(Color.parseColor(listaColores.get(resultado[2] - 1)));
        btn4.setBackgroundColor(Color.parseColor(listaColores.get(resultado[3] - 1)));

        btn1.setText(listaPalabras.get(resultado[0] - 1));
        btn2.setText(listaPalabras.get(resultado[1] - 1));
        btn3.setText(listaPalabras.get(resultado[2] - 1));
        btn4.setText(listaPalabras.get(resultado[3] - 1));

        palabra.setText(listaPalabras.get(numeroP));
        palabra.setTextColor(Color.parseColor(listaColores.get(numeroC)));
        tiempo();
    }

    public void tiempo(){
        timer=new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long l) {
                int segundosRestantes = (int) l / 1000;
                txttiempo.setText("Tiempo: " + Integer.toString(segundosRestantes));
            }

            @Override
            public void onFinish() {
                intentos--;
                desplegas++;
                incorrectas++;
                generarNumero();
            }
        };
        timer.start();
    }
}
