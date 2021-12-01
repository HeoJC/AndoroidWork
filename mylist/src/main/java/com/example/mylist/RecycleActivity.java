package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecycleActivity extends AppCompatActivity {
    RecyclerView recycleView ;
    ArrayList<Map<String , String>> list = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        Map<String , String> map = new HashMap<String , String>() ;
        map.put("name" , "홍길동") ; map.put("addr" , "대구") ;
        list.add(map) ;
        map = new HashMap<String , String>() ;
        map.put("name" , "김길동") ; map.put("addr" , "부산") ;
        list.add(map) ;
        map = new HashMap<String , String>() ;
        map.put("name" , "박길동") ; map.put("addr" , "서울") ;
        list.add(map) ;
        map = new HashMap<String , String>() ;
        map.put("name" , "박길동") ; map.put("addr" , "서울") ;
        list.add(map) ;

        recycleView = findViewById(R.id.recycleView) ;
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL, false);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(new MyRecycleAdapter(list)) ;

    }
}