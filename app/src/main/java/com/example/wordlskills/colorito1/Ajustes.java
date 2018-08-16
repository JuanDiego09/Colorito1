package com.example.wordlskills.colorito1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Ajustes extends AppCompatActivity {

    EditText campoIntentos;
    EditText campoTiempoTotal;
    EditText campoTiempoPalabra;

    Spinner listaTipo;
    ArrayList arrayTipo;
    String tipo;
    boolean mostrarCampo;
    boolean valida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);


        campoTiempoPalabra = findViewById(R.id.campoTiempoPalabra);
        ///////////////////////////////////////////////////////////
        campoIntentos = findViewById(R.id.campoCantidadIntentos);
        campoTiempoTotal = findViewById(R.id.campoTiempoPorPalabra);
        ///////////////////////////////////////////////////////////
        arrayTipo = new ArrayList();
        arrayTipo.add("Seleccione un tipo de juego");
        arrayTipo.add("Por intentos");
        arrayTipo.add("Por tiempo");

        listaTipo = findViewById(R.id.spinnerTipoJuego);
        ArrayAdapter<CharSequence> adapterTipo = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arrayTipo);
        listaTipo.setAdapter(adapterTipo);
        listaTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    tipo = arrayTipo.get(position).toString();
                    valida = true;
                }else {
                    valida = false;
                }

                switch (position){
                    case 1:
                        campoIntentos.setVisibility(View.VISIBLE);
                        campoTiempoTotal.setVisibility(View.INVISIBLE);
                        break;

                    case 2:
                        campoTiempoTotal.setVisibility(View.VISIBLE);
                        campoIntentos.setVisibility(View.INVISIBLE);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                valida = false;
            }
        });
    }
}
