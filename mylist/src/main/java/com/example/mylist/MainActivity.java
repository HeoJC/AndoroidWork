package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv) ;

        String[] data = getResources().getStringArray(R.array.city) ; //new String[]{"","",""} ;
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext() ,
                android.R.layout.simple_list_item_1 , data) ;
        lv.setAdapter(adapter) ;
        lv.setOnItemClickListener((adapterView , view , i , l) -> {
            Toast.makeText(getApplicationContext() , data[i] , Toast.LENGTH_LONG).show() ;
        }) ;
    }
}