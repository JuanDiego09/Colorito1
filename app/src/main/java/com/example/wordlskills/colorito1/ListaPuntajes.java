package com.example.wordlskills.colorito1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.wordlskills.colorito1.adapter.ProyectosAdapter;
import com.example.wordlskills.colorito1.entidades.PuntajesVo;
import com.example.wordlskills.colorito1.utilidades.Conexion;
import com.example.wordlskills.colorito1.utilidades.Utilidades;

import java.util.ArrayList;

public class ListaPuntajes extends AppCompatActivity {

    RecyclerView recyclerUsuarios;
    ProyectosAdapter adapter;
    ArrayList<PuntajesVo> listaPuntajes;
    Conexion conn;
    SQLiteDatabase bd;
    PuntajesVo puntajesVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_puntajes);

        conn = new Conexion(getApplicationContext(), "puntajes", null, 1);
        recyclerUsuarios = findViewById(R.id.RecylerPuntajes);


        puntajesVo = new PuntajesVo();
        listaPuntajes = new ArrayList<>();

        consulta();


    }

    private void consulta() {
        bd = conn.getReadableDatabase();

            Cursor cursor = bd.rawQuery("SELECT * FROM " + Utilidades.TABLA_PUNTAJES + " ORDER BY " + Utilidades.DESPLEGADAS + " DESC ", null);

        int numero = 0;

        while (cursor.moveToNext()) {
            puntajesVo = new PuntajesVo();
            puntajesVo.setDesplegadas(cursor.getString(0));
            puntajesVo.setCorrectas(cursor.getString(1));
            puntajesVo.setIncorrectas(cursor.getString(2));
            puntajesVo.setIntentos("3");
            if (numero <= 3) {
                listaPuntajes.add(puntajesVo);
            }
            numero++;
        }
        adapter = new ProyectosAdapter(listaPuntajes);
        recyclerUsuarios.setAdapter(adapter);
    }
}
