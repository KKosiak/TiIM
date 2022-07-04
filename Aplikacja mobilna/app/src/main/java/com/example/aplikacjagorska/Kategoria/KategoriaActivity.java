package com.example.aplikacjagorska.Kategoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.aplikacjagorska.MySingleton;
import com.example.aplikacjagorska.Produkt.DodajProduktActivity;
import com.example.aplikacjagorska.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KategoriaActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sm;
    int catNum = 0;
    Button btn_cat_delete, btn_cat_add;
    TextView editText1;
    TextView listView1;

    public KategoriaActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        btn_cat_delete = findViewById(R.id.btn_cat_delete);
        btn_cat_add = findViewById(R.id.btn_cat_add);
        editText1 = findViewById(R.id.editText1);
        listView1 = findViewById(R.id.listView1);
        getCategory();

        editText1.setText(editText1.getText() + "\n\t      ID" + "\t\t        Kategoria" );

        btn_cat_delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String elo = editText1.getText().toString();
                int number = Integer.parseInt(elo);
                deleteCategory(number);
            }
        });

        btn_cat_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openDodajKateogorieActivity(view);
            }
        });

    }

    public void getCategory(){

        String url = "http://192.168.8.121:8080/categories/";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                String categoryID= "";
                String category_name= "";
                try {
                    for(int i=0; i< response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        categoryID = object.getString("categoryID");
                        category_name = object.getString("name");

                        listView1.setText(listView1.getText() + "\t       " + categoryID + ".\t\t        " + category_name + "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(KategoriaActivity.this, categoryID + category_name, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KategoriaActivity.this, "That didnt work" + error, Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(KategoriaActivity.this).addToRequestQueue(request);

    }

    public void deleteCategory(int cat){

        String url = "http://192.168.8.121:8080/categories/"+cat;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KategoriaActivity.this, "That didnt work" + error, Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(KategoriaActivity.this).addToRequestQueue(request);
        Toast.makeText(KategoriaActivity.this, "Usunięto pomyślnie!", Toast.LENGTH_SHORT).show();
    }

    public void onAccuracyChanged(Sensor arg0, int arg1){
        // TODO Auto-generated method stub
    }

    public void onSensorChanged(SensorEvent event) {
    }

    private void openDodajKateogorieActivity(View v) {
        Intent intent = new Intent(this, DodajKateogorieActivity.class);
        startActivity(intent);
    }



}
