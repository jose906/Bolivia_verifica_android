package com.bolivia.verifica.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bolivia.verifica.Activitys.ConfWebActivity;
import com.bolivia.verifica.Activitys.LoginActivity;
import com.bolivia.verifica.Activitys.MetodologiaActivity;
import com.bolivia.verifica.Activitys.Transparencia;
import com.bolivia.verifica.PrivacidadActivity2;
import com.bolivia.verifica.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   TextView sesion,mail,name;
   FirebaseAuth auth;
   FirebaseUser user;
   DatabaseReference database;
   String uid;
   Button quienes,transparencia,metodologia,privacidad;


    // TODO: Rename and change types of parameters


    public SettingsFragment() {
        // Required empty public constructor
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user!=null){

            uid = user.getUid();
        }
        database = FirebaseDatabase.getInstance().getReference();

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
    ment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = (TextView)view.findViewById(R.id.set_name);
        sesion = (TextView) view.findViewById(R.id.start_secion);
        quienes = (Button)view.findViewById(R.id.quienes);
        quienes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConfWebActivity.class);
                startActivity(intent);
            }
        });
        transparencia = (Button)view.findViewById(R.id.transparencia);
        transparencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Transparencia.class);
                startActivity(intent);
            }
        });
        metodologia = (Button)view.findViewById(R.id.metodologia);
        metodologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MetodologiaActivity.class);
                startActivity(intent);
            }
        });

        privacidad = (Button)view.findViewById(R.id.privacidad);
        privacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PrivacidadActivity2.class);
                startActivity(intent);
            }
        });




        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user!=null){

                    auth.signOut();
                    mail.setText("User not Found");
                    name.setText("");
                    sesion.setText("Iniciar seseion");



                }else {

                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);

                }


            }
        });
        mail = (TextView) view.findViewById(R.id.set_mail);
        if (user!=null) {
            mail.setText(auth.getCurrentUser().getEmail());
            sesion.setText("Cerrar sesion");
            database.child("data").child(uid).child("datos").child("nombre").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){

                        name.setText(snapshot.getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}