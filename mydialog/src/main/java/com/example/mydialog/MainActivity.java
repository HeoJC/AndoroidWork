package com.example.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button , button2 , button3 , button4 ;
        EditText editTextTextMultiLine ;

        button = findViewById(R.id.button) ;
        button2 = findViewById(R.id.button2) ;
        button3 = findViewById(R.id.button3) ;
        button4 = findViewById(R.id.button4) ;
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine) ;
        String[] city = new String[]{"대구","서울","부산"} ;

        button.setOnClickListener( v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
            builder.setTitle("제목")
                    .setMessage("alert")
                    .setPositiveButton("수정" , (dialogInterface , i) -> {
                        Log.d("alert" , "수정버튼" ) ;
                    })
                    .setNegativeButton( "취소" , (dialogInterface , i) -> {
                        Log.d("alert" , "취소버튼" ) ;
                    })
                    .create()
                    .show() ;
        });
        ArrayList selectedItems = new ArrayList<Integer>() ;
        button2.setOnClickListener( v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
            builder.setMultiChoiceItems(city , null,
                    ( dialog , which , isChecked ) -> {
                        if (isChecked) {
                            selectedItems.add(which) ;
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(which) ;
                        }
                    })
                    .setPositiveButton("OK" , (dialogInterface , i) -> {
                        //city.stream().filter().map(System.out::print) ;
                        for (Object o : selectedItems) {
                            int pos = ((Integer)o).intValue() ;
                            Log.d("alert" , city[pos]) ;
                        }
                    })
                    .create()
                    .show() ;
        });
        button3.setOnClickListener( v -> {
            customModal() ;
        });
        button4.setOnClickListener( v -> {});
    }

    private void customModal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(R.layout.activity_login)
                .create()
                .show() ;
    }
}