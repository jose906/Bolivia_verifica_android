package com.bolivia.verifica.Adapters;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bolivia.verifica.APIConnect;
import com.bolivia.verifica.Activitys.FullNewsActivity;
import com.bolivia.verifica.R;
import com.bolivia.verifica.WpModel.Media;
import com.bolivia.verifica.WpModel.Posts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderData> {

    List<Posts> posts;
    FirebaseUser user;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String uid;

    public RecyclerAdapter(List<Posts>posts){

        this.posts = posts;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user!=null) {
            uid = user.getUid();

        }



    }


    public void getSaved(){


    }



    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_adapter,parent,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {


         APIConnect.getRetrofit().getImageUrl(posts.get(position).getFeatured_media()).enqueue(new Callback<Media>() {
          @Override
          public void onResponse(Call<Media> call, Response<Media> response) {
              if(response.isSuccessful()&& response.body()!=null){
                  Log.e("respuesta",response.body().getMediaDetails().getFile());
                  Glide.with(holder.relativeLayout.getContext()).load(response.body().getMediaDetails().getSizes().getMedium().getSource_url()).into(new CustomTarget<Drawable>() {
                      @Override
                      public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                          holder.relativeLayout.setBackground(resource);

                      }

                      @Override
                      public void onLoadCleared(@Nullable Drawable placeholder) {

                      }
                  });


              }


          }

          @Override
          public void onFailure(Call<Media> call, Throwable t) {

              Log.e("fallo","Fallo");

          }
      });


        holder.titulo.setText(Html.fromHtml(posts.get(position).getTitle().getRendered()));
        holder.fecha.setText(dateFormatter(posts.get(position).getDate()));
        holder.author.setText(String.valueOf(posts.get(position).getAutor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FullNewsActivity.class);
                intent.putExtra("contenido",posts.get(holder.getAdapterPosition()).getContent().getRendered());
                view.getContext().startActivity(intent);

            }
        });

        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null){
                    databaseReference.child("data").child(uid).child("save").push().setValue(posts.get(holder.getAdapterPosition()).getId()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {



                        }
                    });

                }


            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Mira esto");
                intent.putExtra(Intent.EXTRA_TEXT,posts.get(holder.getAdapterPosition()).getLink().toString());
                view.getContext().startActivity(Intent.createChooser(intent,"Compartir por:"));


            }
        });

        if(user!=null){
            databaseReference.child("data").child(uid).child("save").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()) {

                        for (DataSnapshot ds : snapshot.getChildren()) {


                            if(Integer.parseInt(ds.getValue().toString())== posts.get(holder.getAdapterPosition()).getId()){

                                holder.saveButton.setImageResource(R.drawable.ic_baseline_bookmark_red_24);

                            }



                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }
    public  String dateFormatter(String date) {

        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm",new Locale("es"));
        String formattedDate = dateTime.format(dateFormatter);
        System.out.println(formattedDate);
        return formattedDate;
    }
    public void sharebtn(String link){



    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public  class ViewHolderData extends RecyclerView.ViewHolder{
        TextView titulo,fecha,author;
        RelativeLayout relativeLayout;
        ImageButton saveButton,share;


        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.titulo);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.image);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            author = (TextView) itemView.findViewById(R.id.autor);
            saveButton = (ImageButton) itemView.findViewById(R.id.saveButton);
            share = (ImageButton) itemView.findViewById(R.id.sharebtn);
        }
    }
}
