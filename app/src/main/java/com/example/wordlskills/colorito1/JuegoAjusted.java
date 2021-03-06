package com.example.wordlskills.colorito1;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordlskills.colorito1.utilidades.Conexion;
import com.example.wordlskills.colorito1.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Random;

public class JuegoAjusted extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    TextView palabra, txtdesplegadas, txtintentos, txtcorrectas, txtincorrectas, txttiempo, txtporcentaje, txtTiempoTotal;
    ArrayList<String> listaColores;
    ArrayList<String> listaPalabras;
    int desplegas, correctas, incorrectas, intentos = 3, porcentaje;
    int numeroP, numeroC, n, k;
    int numero[];
    int resultado[];
    Random rnd;
    long total, tiempoP;
    boolean tipo1, tipo2;
    CountDownTimer timer, tiempoTotal;
    Conexion conn;
    SQLiteDatabase bd;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_ajusted);
        Bundle miBundle = getIntent().getBundleExtra("datos");


        conn = new Conexion(getApplicationContext(), "puntajes", null, 1);
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
        txtTiempoTotal = findViewById(R.id.tiempoTotal);


        txtdesplegadas.setText("Desplegadas " + desplegas);
        txtincorrectas.setText("Incorrectas " + incorrectas);
        txtcorrectas.setText("Correctas " + correctas);
        txtintentos.setText("Intentos " + intentos);
        txtporcentaje.setText("Porcentaje Reaccion " + intentos);

        tiempoP = Integer.parseInt(miBundle.getString("tiempoPa")) * 1000;
        llenarArray();
        if (miBundle.getInt("tipo") == 1) {
            intentos = Integer.parseInt(miBundle.getString("intentos"));
            tipo1 = true;
            tipo2 = false;
            txtTiempoTotal.setVisibility(View.INVISIBLE);
            txtintentos.setVisibility(View.VISIBLE);
            tiempo();
        } else {
            total = Integer.parseInt(miBundle.getString("tiempoTo")) * 1000;
            txtintentos.setVisibility(View.INVISIBLE);
            txtTiempoTotal.setVisibility(View.VISIBLE);
            tipo2 = true;
            tipo1 = false;
            tiempoTo();
            tiempo();
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipo1 == true) {
                    if (intentos == 0) {
                        termina();
                    } else {
                        comprobar(1);
                    }
                } else if (tipo2 == true) {
                    comprobar(1);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipo1 == true) {
                    if (intentos == 0) {
                        termina();
                    } else {
                        comprobar(2);
                    }
                } else if (tipo2 == true) {
                    comprobar(2);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipo1 == true) {
                    if (intentos == 0) {
                        termina();
                    } else {
                        comprobar(3);
                    }
                } else if (tipo2 == true) {
                    comprobar(3);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipo1 == true) {
                    if (intentos == 0) {
                        termina();
                    } else {
                        comprobar(4);
                    }
                } else if (tipo2 == true) {
                    comprobar(4);
                }
            }
        });
    }

    private void termina() {
        timer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Finaliza");
        String mensaje = "";
        mensaje += "Desplegadas " + desplegas + "\n";
        mensaje += "Correctas " + correctas + "\n";
        ;
        mensaje += "Incorrectas " + incorrectas + "\n";
        ;
        mensaje += "Porcentaje de reaccion " + porcentaje + "\n";
        ;
        builder.setMessage(mensaje);

        builder.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog = builder.create();
        dialog.show();

    }

    private void comprobar(int clic) {
        desplegas++;
        porcentaje = (correctas * 100) / desplegas;
        switch (clic) {
            case 1:
                if (listaColores.get(numeroC) == listaColores.get(resultado[0] - 1)) {
                    correctas++;
                } else {
                    incorrectas++;
                    intentos--;
                }
                timer.cancel();
                tiempo();
                break;
            case 2:
                if (listaColores.get(numeroC) == listaColores.get(resultado[1] - 1)) {
                    correctas++;
                } else {
                    incorrectas++;
                    intentos--;
                }
                timer.cancel();
                tiempo();

                break;
            case 3:
                if (listaColores.get(numeroC) == listaColores.get(resultado[2] - 1)) {
                    correctas++;
                } else {
                    incorrectas++;
                    intentos--;
                }
                timer.cancel();
                tiempo();

                break;
            case 4:
                if (listaColores.get(numeroC) == listaColores.get(resultado[3] - 1)) {
                    correctas++;
                } else {
                    incorrectas++;
                    intentos--;
                }
                timer.cancel();
                tiempo();
                break;
            case 5:

                intentos--;
                incorrectas++;
                timer.cancel();
                tiempo();
                break;
            case 6:
                termina();
                break;

        }

        txtdesplegadas.setText("Desplegadas " + desplegas);
        txtincorrectas.setText("Incorrectas " + incorrectas);
        txtcorrectas.setText("Correctas " + correctas);
        txtintentos.setText("Intentos " + intentos);
        txtporcentaje.setText("Porcentaje Reaccion " + porcentaje);
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

    }

    public void tiempo() {
        timer = new CountDownTimer(tiempoP, 1000) {
            @Override
            public void onTick(long l) {
                int segundosRestantes = (int) l / 1000;
                txttiempo.setText("Tiempo: " + Integer.toString(segundosRestantes));
            }

            @Override
            public void onFinish() {
                if (tipo1 == true) {
                    if (intentos == 0) {
                        termina();
                    } else {
                        comprobar(5);
                    }
                } else {
                    comprobar(5);
                }

            }
        };
        timer.start();
    }

    public void tiempoTo() {
        tiempoTotal = new CountDownTimer(total, 1000) {
            @Override
            public void onTick(long l) {
                int segundosRestantes = (int) l / 1000;
                txtTiempoTotal.setText("Tiempo: " + Integer.toString(segundosRestantes));
            }

            @Override
            public void onFinish() {
                comprobar(6);
            }
        };
        tiempoTotal.start();
    }

    public void onClick(View view) {
        finish();
    }
}
