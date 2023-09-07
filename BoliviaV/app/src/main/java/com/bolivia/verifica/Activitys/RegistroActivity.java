package com.bolivia.verifica.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bolivia.verifica.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    EditText mail, password, name,city;
    Button create;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String nombre,correo,contra,ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mail = (EditText) findViewById(R.id.correo);
        password = (EditText) findViewById(R.id.contra);
        name = (EditText)findViewById(R.id.nombre);
        city = (EditText)findViewById(R.id.ciudad);
        create = (Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createUser();

            }
        });
    }

    public void createUser(){


        correo = mail.getText().toString();
        contra = password.getText().toString();
        Log.e("correo",correo);
        Log.e("contra",contra);

        firebaseAuth.createUserWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    saveDataUser(user.getUid());

                }else {

                    Log.e("response","fallo");
                    Log.e("response",task.getException().toString());
                }


            }
        });

    }



    public void saveDataUser(String user){



        Map<String,String>map = new HashMap<>();
        if(name.getText()!=null){

            nombre  = name.getText().toString();
            map.put("nombre",nombre);
        }
        if(city.getText() != null){
            ciudad = city.getText().toString();
            map.put("ciudad",ciudad);
        }
        map.put("correo",correo);
        databaseReference.child("data").child(user).child("datos").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    AlertDialog.Builder alert=new AlertDialog.Builder(RegistroActivity.this);
                    alert.setMessage("su registro fue exitoso").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {




                        }
                    });
                    AlertDialog dialog=alert.create();
                    dialog.setTitle("Exito");
                    dialog.show();

                }

            }
        });


    }
}