package com.example.wordlskills.colorito1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wordlskills.colorito1.R;
import com.example.wordlskills.colorito1.entidades.PuntajesVo;

import java.util.ArrayList;

public class ProyectosAdapter extends RecyclerView.Adapter<ProyectosAdapter.ProyectosHolder>{


    ArrayList<PuntajesVo> listaPuntajes;
    @Override
    public ProyectosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_adapter_proyectos,null,false);
        return new ProyectosHolder(view);
    }

    @Override
    public void onBindViewHolder(ProyectosHolder holder, int position) {
        holder.correctas.setText(listaPuntajes.get(position).getCorrectas());
        holder.incorrectas.setText(listaPuntajes.get(position).getIncorrectas());
        holder.desplegadas.setText(listaPuntajes.get(position).getDesplegadas());
    }

    @Override
    public int getItemCount() {
        return listaPuntajes.size();
    }

    public class ProyectosHolder extends RecyclerView.ViewHolder {
        TextView correctas,incorrectas,desplegadas;

        public ProyectosHolder(View itemView) {
            super(itemView);
            correctas = itemView.findViewById(R.id.campoCorrectas);
            incorrectas = itemView.findViewById(R.id.campoIncorrectas);
            desplegadas = itemView.findViewById(R.id.campoDesplegadas);
        }


    }
}
