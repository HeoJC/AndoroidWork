package com.example.myamemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {
    EditText editTextTextPersonName , editTextTextPersonName2 , editTextTextPersonName3 , editTextTextPersonName4 , editTextTextMultiLine ;
    Button button , button2 , button3 , button4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName) ; // 이름
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2) ; // 전화
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3) ; // 번호
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4) ; // 나이
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine) ;
        button = findViewById(R.id.button) ; // 수정
        button2 = findViewById(R.id.button2) ; // 등록
        button3 = findViewById(R.id.button3) ; // 조회
        button4 = findViewById(R.id.button4) ; // 삭제

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()) ;

        // 등록
        button2.setOnClickListener( v -> {
            SQLiteDatabase db=dbHelper.getWritableDatabase() ;
            String name = editTextTextPersonName.getText().toString() ;
            String age = editTextTextPersonName4.getText().toString() ;
            String phone = editTextTextPersonName2.getText().toString() ;
            String sql = "INSERT INTO emp "
                    + "(name, age, mobile) VALUES ( '"+ name +"' , '"+ age +"' , '"+ phone +"' )" ;
            db.execSQL(sql) ;

            Intent intent = new Intent(getApplicationContext() , MainActivity.class) ;
            intent.putExtra("msg" , "등록완료") ;
            setResult(RESULT_OK , intent);
            finish() ;
        });

        // 수정
        button.setOnClickListener( v -> {
            SQLiteDatabase db=dbHelper.getWritableDatabase() ;
            String no = editTextTextPersonName3.getText().toString() ;
            String name = editTextTextPersonName.getText().toString() ;
            String age = editTextTextPersonName4.getText().toString() ;
            String mobile = editTextTextPersonName2.getText().toString() ;
            String sql = "UPDATE emp SET name='" + name + "' , age='" + age + "' , mobile= '" + mobile + "' WHERE _id=" + no ;
            db.execSQL(sql) ;
        });

        // 단건조회
        button3.setOnClickListener( v -> {
            SQLiteDatabase db=dbHelper.getReadableDatabase() ;
            String no = editTextTextPersonName3.getText().toString() ;
            String sql = "SELECT * FROM emp WHERE _id =" + no ;
            Cursor cursor = db.rawQuery(sql,null) ;
            String context = "" ;
            String name = "" ;
            String age = "" ;
            String mobile = "" ;
            while(cursor.moveToNext()) {
                context += "번호:" + cursor.getString(0) ;
                context += " 이름:" + cursor.getString(1) ;
                context += " 나이:" + cursor.getString(2) ;
                context += " 전화번호:" + cursor.getString(3) + "\n";

                name = cursor.getString(1) ;
                age = cursor.getString(2) ;
                mobile = cursor.getString(3) ;
            }
            editTextTextMultiLine.setText(context) ;
            editTextTextPersonName.setText(name) ;
            editTextTextPersonName2.setText(mobile) ;
            editTextTextPersonName4.setText(age) ;
        });

        // 삭제
    //        button4.setOnClickListener( v -> {
    //            SQLiteDatabase db=dbHelper.getReadableDatabase() ;
    //            String no = editTextTextPersonName3.getText().toString() ;
    //            String sql = "DELETE FROM emp WHERE _id=" + no ;
    //            db.execSQL(sql) ;
    //        });

        // 삭제 (교수님)
        button4.setOnClickListener( v-> {
            SQLiteDatabase db = dbHelper.getWritableDatabase() ;
            String id = editTextTextPersonName3.getText().toString() ;
            db.delete("emp", "_id=?" , new String[]{id}) ;
        });
    }
}