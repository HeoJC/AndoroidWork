package com.example.mydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME = "hr.db" ;
    public static int VERSION = 1 ;

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate 호출됨") ;
        String sql = "create table if not exists diary("
                + " _id integer PRIMARY KEY autoincrement, "
                + " title text, "
                + " content integer, "
                + " time text)" ;
        db.execSQL(sql) ;
        sql = "insert into diary (title , content , time) values ('t1' , 'content1' , '2021-12-02')" ;
        db.execSQL(sql) ;
        sql = "insert into diary (title , content , time) values ('t2' , 'content2' , '2021-12-02')" ;
        db.execSQL(sql) ;
        sql = "insert into diary (title , content , time) values ('t3' , 'content3' , '2021-12-02')" ;
        db.execSQL(sql) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
