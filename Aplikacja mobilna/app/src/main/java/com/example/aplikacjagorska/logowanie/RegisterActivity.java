package com.example.aplikacjagorska.logowanie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikacjagorska.MainActivity;
import com.example.aplikacjagorska.MainActivityAdmin;
import com.example.aplikacjagorska.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText name;
    private EditText password1;
    private EditText email1;
    Button loginButton;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        name =      findViewById(R.id.etName);
        password1 = findViewById(R.id.etPassword);
        loginButton =     findViewById(R.id.button);
        email1 =    findViewById(R.id.email1);
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            Toast.makeText(RegisterActivity.this, "Jesteś już zarejestrowany", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivityAdmin.class));
            finish();
        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName =      name.getText().toString().trim();
                String inputPassword =  password1.getText().toString().trim();
                String inputEmail =     email1.getText().toString().trim();

                if(TextUtils.isEmpty(inputEmail)){
                    email1.setError("Email jest wymagany");
                    return;
                }
                if(TextUtils.isEmpty(inputPassword)){
                    email1.setError("Hasło jest wymagane");
                    return;
                }
                if(TextUtils.isEmpty(inputName)){
                    email1.setError("Nazwa jest wymagana");
                    return;
                }
                if(inputPassword.length()<8){
                    password1.setError("Hasło musi mieć conajmniej 8 znaków");
                }


                auth.createUserWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Utworzono użytkownika", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivityAdmin.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Błąd" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


    private void updateUI(FirebaseUser currentUser) {
    }

}