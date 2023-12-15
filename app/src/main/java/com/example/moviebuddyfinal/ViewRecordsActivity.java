package com.example.moviebuddyfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ViewRecordsActivity extends AppCompatActivity {


    private DBhelper dBhelper;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_records);
        textView=findViewById(R.id.displayTable);
        dBhelper=new DBhelper(ViewRecordsActivity.this);
        List<Movie> movieList = dBhelper.getAllData();

        // Initialize a StringBuilder to build the content
        StringBuilder content = new StringBuilder();
        for (Movie movie : movieList) {
            // Append movie information to the StringBuilder
            content.append("Title: ").append(movie.getTitle()).append("\n");
            content.append("Directors: ").append(movie.getDirectors()).append("\n");
            content.append("Casts: ").append(movie.getCasts()).append("\n");
            content.append("Release Date: ").append(movie.getReleaseDate()).append("\n\n");
        }

        // Set the content to the TextView
        textView.setText(content.toString());

    }


}