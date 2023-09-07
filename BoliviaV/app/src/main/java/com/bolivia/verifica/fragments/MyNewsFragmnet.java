package com.bolivia.verifica.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bolivia.verifica.APIConnect;
import com.bolivia.verifica.Adapters.RecyclerAdapter;
import com.bolivia.verifica.R;
import com.bolivia.verifica.WpModel.Posts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyNewsFragmnet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyNewsFragmnet extends Fragment {


    FirebaseAuth auth;
    DatabaseReference database;
    String uid;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Posts> posts = new ArrayList<>();
    RecyclerAdapter adapter;
    int a [];
    List<Integer> c =  new ArrayList<>();




    public MyNewsFragmnet() {

        auth = FirebaseAuth.getInstance();
        if(auth.getUid()!=null){
        uid = auth.getUid();
        database = FirebaseDatabase.getInstance().getReference();
        }
        else{




        }


        // Required empty public constructor
    }


    public static MyNewsFragmnet newInstance() {
        MyNewsFragmnet fragment = new MyNewsFragmnet();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_mynews);
        //progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter(posts);
        recyclerView.setAdapter(adapter);

        if(auth.getUid()!=null){

            getIDnews();
        }else{

            AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
            alert.setMessage("Necesita iniciar sesion para acceder a esta funcion!").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {



                }
            });
            AlertDialog dialog=alert.create();
            dialog.setTitle("Usuario no encontrado!");
            dialog.show();
        }

    }
    public void getIDnews(){

        database.child("data").child(uid).child("save").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    a = new int[(int) snapshot.getChildrenCount()];
                    int b = 0;
                    for (DataSnapshot ds:snapshot.getChildren()){

                        a[b] = Integer.parseInt(ds.getValue().toString());
                        c.add(Integer.parseInt(ds.getValue().toString()));
                        b++;

                    }
                    getposts(a);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    public void getposts(int a[]){




        APIConnect.getRetrofit().getMyPosts(c.toString().replace(" ", "").replace("[","").replace("]","")).enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(response.isSuccessful() && response.body()!=null){

                    Log.e("response",String.valueOf(response.body().size()));

                    posts.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_news, container, false);
    }
}