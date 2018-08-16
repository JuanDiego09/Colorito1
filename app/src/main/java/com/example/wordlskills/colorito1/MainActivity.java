package com.example.wordlskills.colorito1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button jugar,ajuste,lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugar=findViewById(R.id.btnjugar);
        ajuste=findViewById(R.id.btnAjuste);
        lista=findViewById(R.id.btnPuntajes);


        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,JuegoDefectoActivity.class);
                startActivity(intent);
            }
        });
    }
}
