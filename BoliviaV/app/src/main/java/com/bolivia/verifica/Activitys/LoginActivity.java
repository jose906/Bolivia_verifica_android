package com.bolivia.verifica.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bolivia.verifica.MainActivity;
import com.bolivia.verifica.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView registro;
    Button inicio;
    EditText correo,contra;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        correo =  (EditText)findViewById(R.id.correo);
        contra = (EditText)findViewById(R.id.contra);


        registro = (TextView) findViewById(R.id.navigateSignup);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(intent);
            }
        });
        inicio = (Button)findViewById(R.id.btn_log);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correo.getText().toString().trim()!=""|| contra.getText().toString().trim()!="" ) {
                    auth.signInWithEmailAndPassword(correo.getText().toString(), contra.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                AlertDialog.Builder alert=new AlertDialog.Builder(getApplicationContext());
                                alert.setMessage("Inicio de sesion correcto").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);


                                    }
                                });
                                AlertDialog dialog=alert.create();
                                dialog.setTitle("Bienvenido");
                                dialog.show();


                            } else {

                                AlertDialog.Builder alert=new AlertDialog.Builder(getApplicationContext());
                                alert.setMessage("Hubo un error al iniciar sesion").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);


                                    }
                                });
                                AlertDialog dialog=alert.create();
                                dialog.setTitle("ERROR");
                                dialog.show();

                            }

                        }
                    });
                }else{

                    AlertDialog.Builder alert=new AlertDialog.Builder(getApplicationContext());
                    alert.setMessage("Debe ingresar TODOS los campos").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {




                        }
                    });
                    AlertDialog dialog=alert.create();
                    dialog.setTitle("Error");
                    dialog.show();
                }



            }
        });
    }
}