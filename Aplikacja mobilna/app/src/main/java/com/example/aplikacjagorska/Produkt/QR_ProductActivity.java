package com.example.aplikacjagorska.Produkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.aplikacjagorska.R;
import com.google.zxing.Result;

public class QR_ProductActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private static String URLaddress;
//    ProduktActivity produktActivity = new ProduktActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(QR_ProductActivity.this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        URLaddress = result.getText();
//                        URLaddress = "http://192.168.8.121:8080/categories/1/product/1";
                        Toast.makeText(QR_ProductActivity.this, URLaddress, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
                openProduktActivity(view);


            }
        });
    }

//    public void QR_ProduktActivity(String URLaddress){
//        this.URLaddress = URLaddress;
//    }

    public String getURLaddress(){
        return URLaddress;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private void openProduktActivity(View v) {
        Intent intent = new Intent(this, ProduktActivity.class);
        startActivity(intent);
    }
}
