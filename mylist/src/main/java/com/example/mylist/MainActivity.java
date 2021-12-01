package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv) ;

        ArrayList<Map<String , String>> list = new ArrayList<>() ;

        String[] data = new String[]{""} ;

        Map<String , String> map = new HashMap<String , String>() ;
        map.put("name" , "홍길동") ; map.put("addr" , "대구") ;
        list.add(map) ;
        map = new HashMap<String , String>() ;
        map.put("name" , "김길동") ; map.put("addr" , "부산") ;
        list.add(map) ;
        map = new HashMap<String , String>() ;
        map.put("name" , "박길동") ; map.put("addr" , "서울") ;
        list.add(map) ;

        MyAdapter adapter = new MyAdapter(list) ;
        lv.setAdapter(adapter) ;

        lv.setOnItemClickListener((adapterView , view , i , l) -> {
            Toast.makeText(getApplicationContext() , list.get(i).get("name") , Toast.LENGTH_LONG).show() ;
        }) ;
    }
}