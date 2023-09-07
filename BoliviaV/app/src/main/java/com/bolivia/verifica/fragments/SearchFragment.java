package com.bolivia.verifica.fragments;

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
import android.widget.EditText;
import android.widget.ImageButton;

import com.bolivia.verifica.APIConnect;
import com.bolivia.verifica.Adapters.RecyclerAdapter;
import com.bolivia.verifica.R;
import com.bolivia.verifica.WpModel.Posts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    FirebaseAuth auth;
    DatabaseReference database;
    EditText bucador;
    ImageButton buscar;
    String uid;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Posts> posts = new ArrayList<>();
    RecyclerAdapter adapter;
    int a [];
    List<Integer> c =  new ArrayList<>();


    public SearchFragment() {

        auth = FirebaseAuth.getInstance();
        if(auth.getUid()!=null){
            uid = auth.getUid();
            database = FirebaseDatabase.getInstance().getReference();
        }
        else{




        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.src_recycler);
        //progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter(posts);
        recyclerView.setAdapter(adapter);
        bucador = (EditText) view.findViewById(R.id.src_edit);
        buscar = (ImageButton) view.findViewById(R.id.src_btn);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bucador.getText().toString().trim()!=""){

                    posts.clear();
                    String a = bucador.getText().toString();
                    getposts(a);


                }
            }
        });

    }
    public void getposts(String tag){




        APIConnect.getRetrofit().getsearch(tag).enqueue(new Callback<List<Posts>>() {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}