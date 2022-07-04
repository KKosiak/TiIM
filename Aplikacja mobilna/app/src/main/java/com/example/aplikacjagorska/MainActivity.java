package com.example.aplikacjagorska;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplikacjagorska.logowanie.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        final Button log_reg = findViewById(R.id.log_reg);
        fAuth= FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivityAdmin.class));
            finish();
        }

        log_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoggedActivity(v);
            }
        });
    }

    private void openLoggedActivity(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}