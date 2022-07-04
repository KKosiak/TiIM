package com.example.aplikacjagorska.Produkt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplikacjagorska.Kategoria.DodajKateogorieActivity;
import com.example.aplikacjagorska.MySingleton;
import com.example.aplikacjagorska.Produkt.ProduktActivity;
import com.example.aplikacjagorska.logowanie.LoginActivity;
import com.example.aplikacjagorska.Kategoria.KategoriaActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.example.aplikacjagorska.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DodajProduktActivity extends AppCompatActivity{

    TextView textView2;
    EditText editTextNumber2, editTextNumber3, editTextTextPersonName2, editTextTextPersonName3, editTextTextPersonName4;
    Button button3;
    String sproductID, sname, sprice, samount, scategory_id;
//    Context context;

    public DodajProduktActivity(){
//        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodajprodukt);
        button3 = findViewById(R.id.button3);
        textView2 = findViewById(R.id.textView2);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextNumber3 = findViewById(R.id.editTextNumber3);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);

        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                sproductID = editTextNumber2.getText().toString().trim();
                sname = editTextTextPersonName2.getText().toString().trim();
                sprice = editTextTextPersonName3.getText().toString().trim();
                samount = editTextTextPersonName4.getText().toString().trim();
                scategory_id = editTextNumber3.getText().toString().trim();
                dodajProdukt(sproductID, sname, sprice, samount, scategory_id);
            }
        });



        }
    private void dodajProdukt(String productID, String name, String price, String amount, String category_id){
        String url = "http://192.168.8.121:8080/categories/1/product";

        RequestQueue queue = Volley.newRequestQueue(DodajProduktActivity.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respObj = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(DodajProduktActivity.this, "That didnt work" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<>();

                params.put("productID:", productID);
                params.put("name:", name);
                params.put("price:", price);
                params.put("amount:", amount);
                params.put("category_id:", category_id);

                return params;

            }
        };
            queue.add(request);
            Toast.makeText(DodajProduktActivity.this, "Dodano pomyślnie!", Toast.LENGTH_SHORT).show();

    }
}
