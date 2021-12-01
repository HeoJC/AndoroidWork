package com.example.myamemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editTextTextMultiLine ;
    Button button , button5 ;
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine) ;
        button = findViewById(R.id.button) ; // 전체조회
        button5 = findViewById(R.id.button5) ; // 등록으로 이동
        lv = findViewById(R.id.lv) ;

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()) ;

        List<String> result = new ArrayList<String>() ;
        List<Map<String , String>> list = new ArrayList<>() ;
        Map<String , String> map = new HashMap<String , String>() ;
        map.put("name" , "홍길동") ; map.put("addr" , "대구") ;
        list.add(map) ;
        map = new HashMap<String , String>() ;
        map.put("name" , "김길동") ; map.put("addr" , "부산") ;
        list.add(map) ;
        map = new HashMap<String , String>() ;
        map.put("name" , "박길동") ; map.put("addr" , "서울") ;
        list.add(map) ;

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext() ,
                list ,
                android.R.layout.simple_list_item_2 ,
                new String[]{"name","addr"} ,
                new int[]{android.R.id.text1,android.R.id.text2}) ;
        lv.setAdapter(adapter) ;
        lv.setOnItemClickListener((adapterView , view , i , l) -> {
            Toast.makeText(getApplicationContext() , list.get(i).get("name") , Toast.LENGTH_LONG).show() ;
            Toast.makeText(getApplicationContext() , list.get(i).get("addr") , Toast.LENGTH_LONG).show() ;
        });

        // -- 클릭하면 나오는거 --
//        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext() ,
//                android.R.layout.simple_list_item_1 , result) ;
//        lv.setAdapter(adapter) ;
//        lv.setOnItemClickListener((adapterView , view , i , l) -> {
//            Toast.makeText(getApplicationContext() , result.get(i) , Toast.LENGTH_LONG).show() ;
//        }) ;
        // -- 클릭하면 나오는거 --

        // 전체조회

        // -- 클릭하면 나오는거 --
        button.setOnClickListener( v -> {
            SQLiteDatabase db =dbHelper.getReadableDatabase() ; // DB
            String sql = "SELECT * FROM emp" ;
            Cursor cursor = db.rawQuery(sql , null) ;
            while (cursor.moveToNext()) {
                result.add(cursor.getString(1)) ;
            } ;
            lv.setAdapter(adapter);
        // -- 클릭하면 나오는거 --

        // -- 클릭하면 데이터베이스에서 불러오는거 --
//            SQLiteDatabase db=dbHelper.getReadableDatabase() ;
//            String sql = "SELECT * FROM emp" ;
//            Cursor cursor = db.rawQuery(sql,null) ;
//            String context = "" ;
//                while(cursor.moveToNext()) {
//                    context += "번호:" + cursor.getString(0) ;
//                    context += " 이름:" + cursor.getString(1) ;
//                    context += " 나이:" + cursor.getString(2) ;
//                    context += " 전화번호:" + cursor.getString(3) + "\n";
//                }
//            editTextTextMultiLine.setText(context);
        // -- 클릭하면 데이터베이스에서 불러오는거 --
       });

        button5.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(), InsertActivity.class) ;
            startActivityForResult(intent,0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data) {
        super.onActivityResult(requestCode , resultCode , data) ;
        if(resultCode == RESULT_OK) {
            String msg = data.getExtras().getString("msg") ;
            Toast.makeText(getApplicationContext() , msg , Toast.LENGTH_LONG).show() ;
        }
    }
}