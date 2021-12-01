package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button) ; // 조회
        Button button2 = findViewById(R.id.button2) ; // 등록
        Button button3 = findViewById(R.id.button3) ; // 삭제
        Button button4 = findViewById(R.id.button4) ; // 전체조회
        TextView textView = findViewById(R.id.txtName) ;
        TextView editTextTextPersonName = findViewById(R.id.editTextTextPersonName) ;

        RequestQueue queue = Volley.newRequestQueue(this) ;
        Gson gson = new Gson() ;

        button2.setOnClickListener( v -> {
            String url = "http://10.0.2.2/insertUser" ;
            StringRequest request = new StringRequest(Request.Method.POST , url ,
                    s->{
                        textView.setText(s) ;
                    } ,
                    e->{
                        Log.d("" , e.toString()) ;
                    }) {
                                @Override
                                protected  Map<String , String> getParams() throws AuthFailureError {
                                    Map<String , String> map = new HashMap<String , String>() ;
                                    map.put("id" , "chichi") ;
                                    map.put("name" , "홍길동") ;
                                    map.put("password" , "222") ;
                                    map.put("role" , "Admin") ;
                                    return map ;
                                }
                            } ;
            queue.add(request) ;
        });

        button.setOnClickListener( v -> {
            //String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20211130" ;
            String id = editTextTextPersonName.getText().toString() ;
            String url = "http://10.0.2.2/getUser?id=" + id ;
            StringRequest request = new StringRequest(url,
                    s->{
                        Map<String , Object> map = gson.fromJson(s , Map.class) ;
                        textView.setText(
                                "이름 : " + map.get("name").toString() +
                                " 아이디 : " + map.get("id").toString()
                        ) ;
                    },
                    e->{
                        Log.d("" , e.toString()) ;
                    }
            ) ;
            queue.add(request) ;
        });

        button4.setOnClickListener( v -> {
            String url = "http://10.0.2.2/userList" ;
            StringRequest request = new StringRequest(url ,
                    s->{
                        textView.setText(s) ;
                    },
                    e->{
                        Log.d("" , e.toString()) ;
                    }
            ) ;
            queue.add(request) ;
        });

        button3.setOnClickListener( v -> {
            String id = editTextTextPersonName.getText().toString() ;
            String url = "http://10.0.2.2/deleteUser?id=" + id ;
            StringRequest request = new StringRequest(url ,
                    s->{
                        textView.setText(s) ;
                    } ,
                    e->{
                        Log.d("" , e.toString()) ;
                    }
            ) ;
            queue.add(request) ;
        });
    }
}