package com.example.myhttp;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello") ;
        Gson gson = new Gson() ;
        EmpVO vo = new EmpVO() ;
        vo.setAge(10) ;
        vo.setFname("heo") ;
        String result = gson.toJson(vo) ;
        System.out.println(result) ;

        String response = "{\"fname\":\"홍길동\",\"age\":20}" ;
        EmpVO emp = gson.fromJson(response , EmpVO.class) ;
        System.out.println(emp.getFname()) ;

        Map<String , Object> map = gson.fromJson(response , Map.class) ;
        System.out.println(map.get("fname")) ;
        System.out.println(map.get("age")) ;

        response = "{\"data\":[{\"fname\":\"김유신\",\"age\":10}]}" ;
        Map<String , Object> map2 = gson.fromJson(response , Map.class) ;
        // object에서 key로 꺼내서 list형태로 변환
        List list = (List)map2.get("data") ;
        // list에서 0번째 꺼내서 map형태로 변환
        Map std = (Map)list.get(0) ;
        // map에서 key로 꺼냄
        System.out.println(std.get("fname")) ;

        System.out.println("listEmp-----");

        ListEmp listEmp = gson.fromJson(response , ListEmp.class) ;
        System.out.println(listEmp.getClass()); // ListEmp
        System.out.println(listEmp.data.getClass()); // ArrayList
        System.out.println(listEmp.data.get(0).getClass()); // EmpVO
        System.out.println(listEmp.data.get(0).getFname().getClass()); // String

    }
}
