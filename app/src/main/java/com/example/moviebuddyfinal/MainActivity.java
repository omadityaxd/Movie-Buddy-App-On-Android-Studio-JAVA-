package com.example.moviebuddyfinal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    private EditText uid;
    private EditText title;
    private EditText directors;
    private EditText casts;
    private EditText releaseDate;
    private Button addButton;
    private Button viewRecords;

    private Button cancel;

    private ImageView imageView;

    private Button upload;
    private DBhelper dbHelper;

    private Button cinema;


    private final int req_code=100;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SplashScreen.installSplashScreen(MainActivity.this);
        setContentView(R.layout.activity_main);


        uid = findViewById(R.id.edit_text_id);
        title = findViewById(R.id.edit_text_title);
        directors = findViewById(R.id.edit_text_directors);
        casts = findViewById(R.id.edit_text_casts);
        releaseDate = findViewById(R.id.edit_text_releaseDate);
        addButton = findViewById(R.id.button1);
        viewRecords = findViewById(R.id.button3);
        cancel=findViewById(R.id.button2);
        imageView=findViewById(R.id.imgGallery);
        upload=findViewById(R.id.poster);
        cinema=findViewById(R.id.cinema1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper=new DBhelper(MainActivity.this);
                // Get user input
                String movieTitle = title.getText().toString();
                String movieDirectors = directors.getText().toString();
                String movieCasts = casts.getText().toString();
                String movieReleaseDate = releaseDate.getText().toString();

                // Insert the data into the database and check if insertion was successful
                boolean isInsertSuccessful = dbHelper.addOne(movieTitle, movieDirectors, movieCasts, movieReleaseDate);

                if (isInsertSuccessful) {
                    // The insertion was successful
                    Toast.makeText(MainActivity.this, "Movie added to the database", Toast.LENGTH_SHORT).show();
                } else {
                    // The insertion failed
                    Toast.makeText(MainActivity.this, "Failed to add the movie", Toast.LENGTH_SHORT).show();
                }

                // Clear the input fields
                title.getText().clear();
                directors.getText().clear();
                casts.getText().clear();
                releaseDate.getText().clear();
            }
        });


        viewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ViewRecordsActivity.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid.setText("");
                title.setText("");
                directors.setText("");
                casts.setText("");
                releaseDate.setText("");
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromGallery();

            }
        });


        cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CinemaActivity.class);
                startActivity(intent);
            }
        });

    }
    public void selectImageFromGallery() {
        // Create an intent to open the gallery.
        Intent galleryIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivityForResult(galleryIntent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Toast.makeText(MainActivity.this,"UPLOADED SUCCESSFULLY!" , Toast.LENGTH_SHORT).show();
            // Get the selected image URI from the gallery.
            Uri selectedImageUri = data.getData();


            // You can now do something with the selected image URI, such as displaying it in an ImageView or uploading it to a server.
        }
    }
}