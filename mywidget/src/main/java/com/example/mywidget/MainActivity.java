package com.example.mywidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1 , btn2 , btn3 , btn4 ;
    EditText txtNum1 , txtNum2 ;
    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        btn1 = findViewById(R.id.button17) ; // 더하기
        btn2 = findViewById(R.id.button4) ; // 빼기
        btn3 = findViewById(R.id.button8) ; // 곱하기
        btn4 = findViewById(R.id.button11) ; // 나누기
        txtNum1 = findViewById(R.id.editTextTextPersonName) ;
        txtNum2 = findViewById(R.id.editTextTextPersonName2) ;
        tv = findViewById(R.id.textView6) ;
        // 익명클래스 ( 클래스 선언과 생성을 동시에 )
        // 람다식 ( 하나의 추상메소드만 있는 인터페이스인 경우 )
//        btn1.setOnClickListener( v -> {
//            int one = Integer.parseInt(txtNum1.getText().toString()) ;
//            int two = Integer.parseInt(txtNum2.getText().toString()) ;
//            tv.setText(String.valueOf(one + two));
//        }) ;
//        btn2.setOnClickListener( v -> {
//            int one = Integer.parseInt(txtNum1.getText().toString()) ;
//            int two = Integer.parseInt(txtNum2.getText().toString()) ;
//            tv.setText(String.valueOf((one - two)));
//        }) ;
//        btn3.setOnClickListener( v -> {
//            int one = Integer.parseInt(txtNum1.getText().toString()) ;
//            int two = Integer.parseInt(txtNum2.getText().toString()) ;
//            tv.setText(String.valueOf((one * two)));
//        }) ;
//        btn4.setOnClickListener( v -> {
//            int one = Integer.parseInt(txtNum1.getText().toString()) ;
//            int two = Integer.parseInt(txtNum2.getText().toString()) ;
//            tv.setText(String.valueOf((one / two)));
//        }) ;

        View.OnClickListener handler = v -> {
            int one = Integer.parseInt(txtNum1.getText().toString()) ;
            int two = Integer.parseInt(txtNum2.getText().toString()) ;
            double result = 0 ;
            switch(v.getId()) {
                case R.id.button17: result = one + two ; break ;
                case R.id.button4: result = one - two ; break ;
                case R.id.button8: result = one * two ; break ;
                case R.id.button11: if(one != 0 && two != 0) {result = (double)one / (double)two ; break ;} else { break ; }
            }
            txtNum1.setText("");
            txtNum2.setText("");
            tv.setText(String.valueOf(result)) ;
        } ;
        btn1.setOnClickListener(handler) ;
        btn2.setOnClickListener(handler) ;
        btn3.setOnClickListener(handler) ;
        btn4.setOnClickListener(handler) ;
    }
}

//class ClickHandler implements View.OnClickListener {
//    @Override
//    public void onClick(View view) {
//        Toast.makeText(null,"클릭됨",Toast.LENGTH_LONG).show() ;
//    }
//}