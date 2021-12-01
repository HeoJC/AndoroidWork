package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;
    RadioButton radioButton , radioButton2 , radioButton3 ;
    RadioGroup RadioGroup ;
    Button button ;
    ImageView imageView4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = findViewById(R.id.checkBox) ;
        radioButton = findViewById(R.id.radioButton) ;
        radioButton2 = findViewById(R.id.radioButton2) ;
        radioButton3 = findViewById(R.id.radioButton3) ;
        RadioGroup = findViewById(R.id.RadioGroup) ;
        button = findViewById(R.id.button) ;
        imageView4 = findViewById(R.id.imageView4) ;

        View.OnClickListener handler = v -> {
            switch (v.getId()) {
                case R.id.radioButton: imageView4.setImageResource(R.drawable.french) ; break ;
                case R.id.radioButton2: imageView4.setImageResource(R.drawable.golden) ; break ;
                case R.id.radioButton3: imageView4.setImageResource(R.drawable.scottish) ; break ;
            }
        } ;

        radioButton.setOnClickListener(handler) ;
        radioButton2.setOnClickListener(handler) ;
        radioButton3.setOnClickListener(handler) ;

        checkBox.setOnClickListener( v -> {
            if (checkBox.isChecked()) {
                RadioGroup.setVisibility(View.VISIBLE) ;
            } else {
                RadioGroup.setVisibility(View.INVISIBLE) ;
            }
        });
    }
}