package com.bolivia.verifica.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bolivia.verifica.R;
import com.bolivia.verifica.WpModel.Categories;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolderDatos> {
    /*
  {"id": 620,"name": "Apertura 2"},no
  {"id": 298,"name": "Apertura 3"},no
  {"id": 459,"name": "Apertura1"},no
  {"id": 8, "name": "Boletin BV"},no
  {"id": 3055,"name": "Boletín OPS"},no
  {"id": 11,"name": "Coronavirus"},si
  {"id": 3230, "name": "Debate en redes"},no
  {"id": 5,"name": "Discurso"},si
  {"id": 458,"name": "Elecciones"},si
  {"id": 6,"name": "Engañoso"},si
  {"id": 609,"name": "Entrevistas"},si
  {"id": 567,"name": "Explicador"},si
  {"id": 2,"name": "Falso"},si
  {"id": 267,"name": "Multimedia"}no,
  {"id": 9,"name": "No Verificable"},no
  {"id": 4020,"name": "Periodismo de Soluciones"},si
  {"id": 614,"name": "SinDuda"},si
  {"id": 3570,"name": "Tendencias"},no
  {"id": 4,"name": "Verdadero"},si
  {"id": 461,"name": "Verificación"},no
  {"id": 3,"name": "Virales"}no
  {"id": 4391,"name": "Discurso de odio"} si
  {"id": 4218,"name": "investigacion"}si

    * */

    List<Categories> categoriesList;
    MyCallBack myCallBack;

    public interface MyCallBack{

        void listenerMethod(int id);
    }

    public CategoriesAdapter(List<Categories>categoriesList,MyCallBack myCallBack){

        this.categoriesList = categoriesList;
        this.myCallBack = myCallBack;

    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_categories,parent,false);
        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.textView.setText(categoriesList.get(position).getName());

        switch(categoriesList.get(position).getId()) {
            case 298:
                holder.imageView.setImageResource(R.drawable.bolvertodo);
                holder.textView.setText(R.string.todas);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.falso);
                break;
            case 4:
                holder.imageView.setImageResource(R.drawable.verdadero);
                break;
            case 6:
                holder.imageView.setImageResource(R.drawable.enganoso);
                break;
            case 11:
                holder.imageView.setImageResource(R.drawable.coronavirus);
                break;
            case 567:
                holder.imageView.setImageResource(R.drawable.explicador);
                break;
            case 5:
                holder.imageView.setImageResource(R.drawable.discurso);
                break;
            case 614:
                holder.imageView.setImageResource(R.drawable.sinduda);
                break;
            case 458:
                holder.imageView.setImageResource(R.drawable.elecciones);
                break;
            case 4020:
                holder.imageView.setImageResource(R.drawable.soluciones);
                holder.textView.setText(R.string.soluciones);
                break;
            case 609:
                holder.imageView.setImageResource(R.drawable.entrevistas);
                break;
            case 4391:
                holder.imageView.setImageResource(R.drawable.odio);
                holder.textView.setText(R.string.odio);
                break;
            case 4218:
                holder.imageView.setImageResource(R.drawable.inves);
                break;
            case 3570:
                holder.imageView.setImageResource(R.drawable.tenden);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.bolvertodo);
                break;
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int  id = categoriesList.get(holder.getAdapterPosition()).getId();
                myCallBack.listenerMethod(id);


            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            imageView=(ImageView) itemView.findViewById(R.id.catImage);
            textView = (TextView) itemView.findViewById(R.id.peleas);

        }
    }
}
