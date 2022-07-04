package com.example.aplikacjagorska;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplikacjagorska.Produkt.ProduktActivity;
import com.example.aplikacjagorska.logowanie.LoginActivity;
import com.example.aplikacjagorska.Kategoria.KategoriaActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MainActivityAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button dark = findViewById(R.id.dark1);
        final Button kier_geo = findViewById(R.id.kier_geo);
        final Button kat_nach = findViewById(R.id.kat_nach);
        final Button wyloguj = findViewById(R.id.logout);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn= sharedPreferences.getBoolean("isDarkModeOn", false);
        final int[] kolor = {0};
        LoginActivity loginActivity = new LoginActivity();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL apiEndPoint = new URL("https://localhost:3000/");
                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) apiEndPoint.openConnection();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
            }
        });
        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isDarkModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean(
                            "isDarkModeOn", true);
                    editor.apply();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean(
                            "isDarkModeOn", false);
                    editor.apply();
                }
            }
        });

        kier_geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKierunekGeoActivity(v);
            }
        });

        kat_nach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNachylenieActivity(v);
            }
        });

        wyloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Wylogowano pomyślnie!", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                openWMainActivity(v);
            }
        });

    }

    private void openWMainActivity(View v) {
        Intent intent_wyloguj = new Intent(this, MainActivity.class);
        startActivity(intent_wyloguj);
    }

    private void openNachylenieActivity(View v) {
        Intent intent_nachylenie = new Intent(this, KategoriaActivity.class);
        startActivity(intent_nachylenie);
    }

    private void openKierunekGeoActivity(View v) {
        Intent intent_geo = new Intent(this, ProduktActivity.class);
        startActivity(intent_geo);
    }
}