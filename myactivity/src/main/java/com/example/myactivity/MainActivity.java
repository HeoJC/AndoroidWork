package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDial , btnWeb , btnGoogle , btnSearch , btnSms , btnPhoto , btnLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDial = findViewById(R.id.btnDial) ;
        btnWeb = findViewById(R.id.btnWeb) ;
        btnGoogle = findViewById(R.id.btnGoogle) ;
        btnSearch = findViewById(R.id.btnSearch) ;
        btnSms = findViewById(R.id.btnSms) ;
        btnPhoto = findViewById(R.id.btnPhoto) ;
        btnLife = findViewById(R.id.btnLife) ;

        btnLife.setOnClickListener( v-> {
            Intent intent = new Intent(getApplicationContext() , LifeActivity.class) ;
            startActivity(intent) ;
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:010-1234-5678") ;
                Intent intent = new Intent(Intent.ACTION_DIAL , uri) ;
                startActivity(intent) ;
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.hanb.co.kr") ;
                Intent intent = new Intent(Intent.ACTION_VIEW , uri) ;
                startActivity(intent) ;
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO) ;
                intent.putExtra("sms_body" , "안녕하세요") ;
                intent.setData(Uri.parse("smsto:" + Uri.encode("010-1234-5678"))) ;
                startActivity(intent) ;
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent() ;
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE) ;
                startActivity(intent) ;
            }
        });

    }
}