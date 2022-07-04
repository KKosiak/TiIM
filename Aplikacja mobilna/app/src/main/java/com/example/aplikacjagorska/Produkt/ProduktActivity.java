package com.example.aplikacjagorska.Produkt;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.aplikacjagorska.Kategoria.KategoriaActivity;
import com.example.aplikacjagorska.MainActivity;
import com.example.aplikacjagorska.MySingleton;
import com.example.aplikacjagorska.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProduktActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView imageView1;
    private SensorManager mSensorManager;
    Button btn_cat_add, btn_cat_delete, btn_cat_edit, button4;
    TextView prod_dataInput, textView3;
    EditText editTextNumber4, editTextNumber5;
    QR_ProductActivity qr_productActivity = new QR_ProductActivity();
    String URLAdressQR = qr_productActivity.getURLaddress();
    String url, lol;

    public ProduktActivity(){

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        btn_cat_edit = findViewById(R.id.btn_cat_edit);
        button4 = findViewById(R.id.button4);
        btn_cat_add = findViewById(R.id.btn_cat_add);
        btn_cat_delete = findViewById(R.id.btn_cat_delete);
        prod_dataInput = findViewById(R.id.prod_dataInput);
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        editTextNumber5 = findViewById(R.id.editTextNumber5);
        textView3 = findViewById(R.id.textView3);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        getProduct();
        textView3.setText(textView3.getText() + "\tID" + ".\t    Nazwa" +  "\t     Cena" + "\t     Ilość" + "\t    ID Kategorii"  + "\n");




        btn_cat_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openDodajProduktActivity(view);
            }
        });

        btn_cat_delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String cat = editTextNumber5.getText().toString();
                String prod = editTextNumber4.getText().toString();
                deleteProduct(cat, prod);
            }
        });

        btn_cat_edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openQRActivity(view);
            }
        });

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               lol = null;
                openProductActivity(view);

            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void getProduct(){



        lol = qr_productActivity.getURLaddress();

    //    Toast.makeText(ProduktActivity.this, "Elooo"+ URLAdressQR, Toast.LENGTH_SHORT).show();

        if(lol != null){
            url = lol;
        }
        else {
            url = "http://192.168.8.121:8080/product/";
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                String productID= "";
                String name= "";
                String price= "";
                String amount= "";
                String categoryID= "";
                try {
                    for(int i=0; i< response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        productID = object.getString("productID");
                        name = object.getString("name");
                        price = object.getString("price");
                        amount = object.getString("amount");
                        categoryID = object.getString("categoryID");



                        prod_dataInput.setText(prod_dataInput.getText() + "\t" + productID + ".\t   " + name +  "\t     " + price + "\t     " + amount + "\t    " + categoryID + "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(KategoriaActivity.this, categoryID + category_name, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProduktActivity.this, "That didnt work" + error, Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(ProduktActivity.this).addToRequestQueue(request);
    }

    public void deleteProduct(String cat, String prod){

        String url = "http://192.168.8.121:8080/categories/"+cat+"/product/"+prod;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProduktActivity.this, "That didnt work" + error, Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(ProduktActivity.this).addToRequestQueue(request);
        Toast.makeText(ProduktActivity.this, "Usunięto pomyślnie!", Toast.LENGTH_SHORT).show();
    }

    private void openProductActivity(View view) {
        Intent intent = new Intent(this, ProduktActivity.class);
        startActivity(intent);
    }

    private void openQRActivity(View view) {
        Intent intent = new Intent(this, QR_ProductActivity.class);
        startActivity(intent);
    }

    private void openDodajProduktActivity(View v) {
        Intent intent = new Intent(this, DodajProduktActivity.class);
        startActivity(intent);
    }
}

