package com.example.mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridLayout linear ;
    int startNum = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear = findViewById(R.id.linear) ;
        View.OnClickListener handler = v -> {
            if (Integer.parseInt(((Button)v).getText().toString()) == startNum) {
                ((Button)v).setVisibility(View.INVISIBLE) ;
                startNum++ ;
            }
            // Toast.makeText(this , "클릭됨" , Toast.LENGTH_LONG ).show() ;
            // 클릭한 버튼의 text(숫자값)을 읽어서 startNum 이랑 같다면
            // startNum은 ++ 하고 , 버튼의 text를 ""
        } ;

        // 1차원배열 16개의 임의의 순서로
        Random rd = new Random() ;
        int[] arr = new int[16] ;

        for(int i = 0 ; i < 16 ; i++) {
            arr[i] = i+1 ;
        }

        for (int i = 15 ; i > 0 ; i--) { // 전체크기변하면 오류나니까 뒤에서부터
            int j = rd.nextInt(i+1) ;

            int temp = arr[i] ;
            arr[i] = arr[j] ;
            arr[j] = temp ;
        }

        for(int i = 0 ; i < arr.length ; i++) {

            Button btn = new Button(this) ;
            btn.setText(String.valueOf(arr[i])) ;
            linear.addView(btn) ;
            btn.setOnClickListener(handler) ;
        } ;
    }
}