package com.example.mylist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecycleActivity extends AppCompatActivity {
    RecyclerView recycleView ;
    ArrayList<MemoVO> list = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        list = new ArrayList<MemoVO>() ;
        MemoVO memoVO = new MemoVO() ;
        memoVO.setTitle("java") ; memoVO.setContent("java content") ;
        list.add(memoVO) ;
        memoVO = new MemoVO() ;
        memoVO.setTitle("spring") ; memoVO.setContent("spring content") ;
        list.add(memoVO) ;
        memoVO = new MemoVO() ;
        memoVO.setTitle("android") ; memoVO.setContent("android content") ;
        list.add(memoVO) ;

        recycleView = findViewById(R.id.recycleView) ;
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false) ;
        recycleView.setLayoutManager(layoutManager) ;
        recycleView.setAdapter(new MyRecycleAdapter(list)) ;
        recycleView.setOnClickListener( view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("아이템이 선택됨").create() ;
        });

    }
}