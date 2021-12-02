package com.example.mydiary;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {

    EditText editTitle , editContent ;
    Button btnSave , btnImage , btnUpdate ;
    DBHelper dbHelper ;
    Intent intent = new Intent() ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        dbHelper = new DBHelper(getApplicationContext()) ;
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_write) ;

        editTitle = findViewById(R.id.editTitle) ;
        editContent = findViewById(R.id.editContent) ;
        btnSave = findViewById(R.id.btnSave) ;
        btnImage = findViewById(R.id.btnImage) ;
        btnUpdate = findViewById(R.id.btnUpdate) ;

        btnSave.setOnClickListener( v -> {
            DiaryVO diaryVO = new DiaryVO() ;
            diaryVO.setTitle(editTitle.getText().toString()) ;
            diaryVO.setContent(editContent.getText().toString()) ;
            DiaryDAO.insert(dbHelper , diaryVO);
            intent = new Intent(getApplicationContext(), MainActivity.class) ;
            startActivity(intent);
        });

        intent = getIntent();

        if (intent.getExtras() != null) {
            String id = intent.getExtras().getString("_id");
            String title = intent.getExtras().getString("title");
            String content = intent.getExtras().getString("content");
            String time = intent.getExtras().getString("time");

            editTitle.setText(title);
            editContent.setText(content);
        }

        btnUpdate.setOnClickListener( v -> {
            DiaryVO diaryVO = new DiaryVO() ;
            diaryVO.setId(intent.getExtras().getString("_id"));
            diaryVO.setTitle(editTitle.getText().toString()) ;
            diaryVO.setContent(editContent.getText().toString()) ;

            DiaryDAO.update(dbHelper , diaryVO) ;

            Intent intent = new Intent(getApplicationContext(), MainActivity.class) ;
            startActivity(intent);
        });
    }
}
