package com.example.myfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    Button btnRead , btnWrite ;
    DatePicker dp ;
    EditText edtDiary ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead) ;
        btnWrite = findViewById(R.id.btnWrite) ;
        dp = findViewById(R.id.datePicker1) ;
        edtDiary = findViewById(R.id.edtDiary) ;

        dp.init(2021,11,2,(DatePicker, i, i1, i2) -> {
            // 년월일로 파일명을 만들고 20211205.txt
            // 파일을 읽어서 editview에 보이도록 함
            // 없으면 toast로 없다고 출력
            String fileName = String.valueOf(i)+
                                String.valueOf(i1+1)+
                                String.valueOf(i2)+".txt" ;
            System.out.println(fileName);
            fileRead(fileName) ;
        }) ;

        btnWrite.setOnClickListener( v -> {
            try {
                String fileName = String.valueOf(dp.getYear()) + String.valueOf(dp.getMonth()+1) + String.valueOf(dp.getDayOfMonth()) + ".txt" ;
                FileOutputStream outFs = openFileOutput(fileName , Context.MODE_PRIVATE) ;
                String str = edtDiary.getText().toString() ;
                outFs.write(str.getBytes()) ;
                outFs.close() ;
                Toast.makeText(getApplicationContext() , "파일이 생성됨" ,
                        Toast.LENGTH_SHORT).show() ;
            } catch (IOException e) {

            }
        });

        btnRead.setOnClickListener( v -> {
            try {
                FileInputStream inFs = openFileInput("file.txt") ;
                byte[] txt = new byte[30] ;
                inFs.read(txt) ;
                String str = new String(txt) ;
                Toast.makeText(getApplicationContext(), str , Toast.LENGTH_SHORT).show() ;
                inFs.close() ;
            } catch (IOException e) {

            }
        });
    }

    private void fileRead(String filename) {
        try {
            FileInputStream inFs = openFileInput(filename) ;
            byte[] txt = new byte[30] ;
            inFs.read(txt) ;
            String str = new String(txt) ;
            edtDiary.setText(str) ;
            Toast.makeText(getApplicationContext(), str , Toast.LENGTH_SHORT).show() ;
            inFs.close() ;
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "파일없음" , Toast.LENGTH_SHORT).show() ;
        }
    }
}