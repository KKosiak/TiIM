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

public class LoginActivity extends AppCompatActivity {
    private EditText password1;
    private EditText email1;
    Button loginButton;
    private FirebaseAuth auth;
    TextView zmiana2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        password1 = findViewById(R.id.etPassword);
        loginButton =     findViewById(R.id.button);
        email1 =    findViewById(R.id.email1);
        zmiana2 =    findViewById(R.id.zmianas);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            Toast.makeText(LoginActivity.this, "Jesteś już zalogowany", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivityAdmin.class));
            finish();
        }

        zmiana2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                if(inputPassword.length()<8){
                    password1.setError("Błędne hasło");
                }

                auth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Zalogowano", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivityAdmin.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginActivity.this, "Błąd" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void openMainActivity(View v) {
        Intent intent = new Intent(LoginActivity.this, MainActivityAdmin.class);
        startActivity(intent);
    }
    private void openMainActivityAdmin(View v) {
        Intent intent1 = new Intent(LoginActivity.this, MainActivityAdmin.class);
        startActivity(intent1);
    }
}