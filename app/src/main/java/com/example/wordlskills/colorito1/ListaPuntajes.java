package com.example.wordlskills.colorito1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.wordlskills.colorito1.adapter.ProyectosAdapter;
import com.example.wordlskills.colorito1.entidades.PuntajesVo;

import java.util.ArrayList;

public class ListaPuntajes extends AppCompatActivity {

    RecyclerView recyclerUsuarios;
    ProyectosAdapter adapter;
    ArrayList <PuntajesVo> listaPuntajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_puntajes);


        recyclerUsuarios = findViewById(R.id.RecylerPuntajes);


        PuntajesVo puntajesVo = new PuntajesVo();
        listaPuntajes = new ArrayList<>();

        for (int i = 0; i< 10; i++ ){
            puntajesVo.setCorrectas("4");
            puntajesVo.setIncorrectas("4");
            puntajesVo.setDesplegadas("4");

            listaPuntajes.add(puntajesVo);
        }
        adapter = new ProyectosAdapter(listaPuntajes);
        recyclerUsuarios.setAdapter(adapter);

    }
}
