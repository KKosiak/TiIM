package com.example.aplikacjagorska.Kategoria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplikacjagorska.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DodajKateogorieActivity extends AppCompatActivity {

    Button button2;
    EditText editTextNumber, editTextTextPersonName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodajkategorie);
        button2 = findViewById(R.id.button2);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);

        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String cat_id = editTextNumber.getText().toString();
                String cat_name = editTextTextPersonName.getText().toString();
                addCategory(cat_id, cat_name);
            }
        });

    }

    public void addCategory(String categoryID, String name){
        String url = "http://192.168.8.121:8080/categories";

        RequestQueue queue = Volley.newRequestQueue(DodajKateogorieActivity.this);

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
                Toast.makeText(DodajKateogorieActivity.this, "That didnt work" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<>();

                params.put("categoryID:", categoryID);
                params.put("name:", name);

                return params;

            }
        };
        queue.add(request);
        Toast.makeText(DodajKateogorieActivity.this, "Dodano pomyślnie!", Toast.LENGTH_SHORT).show();

    }
}
