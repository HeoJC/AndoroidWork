package com.example.mydiary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    EditText editTitle , editContent ;
    Button btnSave , btnImage , button2 ;
    DBHelper dbHelper ;
    ImageView imageDiary ;
    Intent intent = new Intent() ;
    String currentPhotoPath;
    Uri photoURI ;
    File photoFile ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        dbHelper = new DBHelper(getApplicationContext()) ;
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_write) ;

        editTitle = findViewById(R.id.editTitle) ;
        editContent = findViewById(R.id.editContent) ;
        btnSave = findViewById(R.id.btnSave) ;
        btnImage = findViewById(R.id.btnImage) ;
        imageDiary = findViewById(R.id.imageDiary) ;
        button2 = findViewById(R.id.button2) ;

        btnSave.setOnClickListener( v -> {

            if (intent.getExtras() != null) {
                DiaryVO diaryVO = new DiaryVO() ;
                diaryVO.setId(intent.getExtras().getString("_id"));
                diaryVO.setTitle(editTitle.getText().toString()) ;
                diaryVO.setContent(editContent.getText().toString()) ;
                diaryVO.setImg(photoURI.toString()) ;
                DiaryDAO.update(dbHelper , diaryVO) ;

                Intent intent = new Intent(getApplicationContext(), MainActivity.class) ;
                startActivity(intent);
            } else {
                DiaryVO diaryVO = new DiaryVO() ;
                diaryVO.setTitle(editTitle.getText().toString()) ;
                diaryVO.setContent(editContent.getText().toString()) ;
                diaryVO.setImg(photoURI.toString()) ;
                DiaryDAO.insert(dbHelper , diaryVO);

                intent = new Intent(getApplicationContext(), MainActivity.class) ;
                startActivity(intent);
            }
        });

        intent = getIntent();

        if (intent.getExtras() != null) {
            String id = intent.getExtras().getString("_id");
            String title = intent.getExtras().getString("title");
            String content = intent.getExtras().getString("content");
            String time = intent.getExtras().getString("time");

            if (intent.getExtras().getString("img") != null) {
                String img = intent.getExtras().getString("img") ;
                Uri photoURI = Uri.parse(img) ;
                imageDiary.setImageURI(photoURI);
            }

            editTitle.setText(title);
            editContent.setText(content) ;

        }

        btnImage.setOnClickListener( v -> {
            dispatchTakePictureIntent() ;
        });

        button2.setOnClickListener( v -> {
            getPhoto() ;
        });
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    static final int REQUEST_PHOTO_SELECTION = 3;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

        photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {

        }
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(this,
                    "com.example.mydiary",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
        //}
    }

    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK ,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI) ;
        startActivityForResult(intent, REQUEST_PHOTO_SELECTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode , data) ;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageDiary.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            imageDiary.setImageURI(photoURI) ;
        } else if (requestCode == REQUEST_PHOTO_SELECTION && resultCode == RESULT_OK) {
            photoURI = data.getData() ;
            System.out.println(data.getData().getClass());
            imageDiary.setImageURI(photoURI) ;
        }
    }
}
