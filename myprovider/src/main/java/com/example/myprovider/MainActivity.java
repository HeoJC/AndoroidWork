package com.example.myprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    Button button , button2 , button3 ;
    EditText editTextTextMultiLine , editTextTextPersonName , editTextTextPersonName2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 권한
//        ActivityCompat.requestPermissions(this,
//                new String[]{Manifest.permission.READ_CALL_LOG ,
//                             Manifest.permission.READ_CONTACTS } , MODE_PRIVATE) ;
        AutoPermissions.Companion.loadAllPermissions(this,101);

        button = findViewById(R.id.button) ;
        button2 = findViewById(R.id.button2) ;
        button3 = findViewById(R.id.button3) ;
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine) ;
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName) ;
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2) ;

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { editTextTextMultiLine.setText(getCallhistory()) ; }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { editTextTextMultiLine.setText(getContacts()); }
        });

        button3.setOnClickListener( v -> { addContact() ; });
    }

    public String getCallhistory() {
        String[] callSet = new String[]{CallLog.Calls.DATE,
                CallLog.Calls.TYPE, CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION}; // 보여줄 칼럼들
        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI ,
                callSet , null , null , null) ;

        if (c == null)
            return "통화기록 없음" ;

        StringBuffer callBuff = new StringBuffer() ;
        callBuff.append("\n날짜 : 구분 : 전화번호 : 통화시간\n\n") ;
        c.moveToFirst() ;
        do {
            // 통화기록에 들어가는 시간 형태만들기
            long callDate = c.getLong(0) ;
            SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd") ;
            String date_str = datePattern.format(new Date(callDate)) ;
            // 통화기록에 들어가는 시간 형태만들기
            callBuff.append(date_str + ":");
            if (c.getInt(1) == CallLog.Calls.INCOMING_TYPE)
                callBuff.append("착신 :");
            else
                callBuff.append("발신 :");
            callBuff.append(c.getString(2) + ":");
            callBuff.append(c.getString(3) + "초\n");
        } while (c.moveToNext());

        c.close();
        return callBuff.toString();
        }

    public String getContacts() {
        String[] contactSet = new String[]{
                ContactsContract.Contacts._ID ,
                ContactsContract.Contacts.LOOKUP_KEY ,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                        ContactsContract.Contacts.DISPLAY_NAME ,
        };
        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI ,
                contactSet , null , null , null) ;

        if (c == null)
            return "연락처 없음" ;

        StringBuffer callBuff = new StringBuffer() ;
        callBuff.append("\n id : 검색키 : 이름 \n\n") ;
        c.moveToFirst() ;
        do {
            callBuff.append(c.getString(0) + ":");
            callBuff.append(c.getString(1) + ":");
            callBuff.append(c.getString(2) + "\n");
        } while (c.moveToNext());

        c.close();
        return callBuff.toString();
    }

    public void addContact() {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        String name = editTextTextPersonName.getText().toString() ;
        String phone = editTextTextPersonName2.getText().toString() ;

        intent.putExtra(ContactsContract.Intents.Insert.NAME , name) ;
        intent.putExtra(ContactsContract.Intents.Insert.PHONE , phone) ;

        startActivity(intent) ;
    }



    @Override
    public void onDenied(int i, String[] strings) { }
    @Override
    public void onGranted(int i, String[] strings) { }
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this); }
}
