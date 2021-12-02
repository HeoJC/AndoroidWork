package com.example.mydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper ;
    Button btnWriteForm ;
    ListView listDiary ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(getApplicationContext()) ;
        ArrayList<DiaryVO> list = DiaryDAO.selectList(dbHelper) ;
        btnWriteForm = findViewById(R.id.btnWriteForm) ;
        listDiary = findViewById(R.id.listDiary) ;
        MyAdapter adapter = new MyAdapter(list) ;
        // listview 초기화 -> adapter 지정 -> 아이템클릭 이벤트
        listDiary.setAdapter(adapter) ;

        btnWriteForm.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(), WriteActivity.class) ;
            startActivity(intent);
        });

        listDiary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("수정/삭제")
                        .setNegativeButton("삭제", (dialogInterface, i) -> {
                            DiaryDAO.delete(dbHelper , list.get(position).getId());
                            list.remove(position) ;
                            ((MyAdapter)listDiary.getAdapter()).notifyDataSetChanged() ;
                        })
                        .setPositiveButton("수정", (dialogInterface, i) -> {
                            Intent intent = new Intent(getApplicationContext(), WriteActivity.class) ;

                            intent.putExtra("_id" , list.get(position).getId()) ;
                            intent.putExtra("title" , list.get(position).getTitle()) ;
                            intent.putExtra("content" , list.get(position).getContent()) ;
                            intent.putExtra("time" , list.get(position).getTime()) ;
                            startActivity(intent);
                        })
                        .create()
                        .show();
            }
        });

        // 아이템클릭 이벤트 : 수정 , 삭제
        
        // 쓰기버튼 이벤트 지정 : writeActivity로 이동
    }

}