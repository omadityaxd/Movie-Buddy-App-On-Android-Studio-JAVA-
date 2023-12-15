package com.example.moviebuddyfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CinemaActivity extends AppCompatActivity
{
    private Button confirm;
    private TextView id;
    private TextView name;
    private TextView location;
    private Button cancel;

    @Override



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.cinema);

        confirm=findViewById(R.id.confirmButton);
        id=findViewById(R.id.id_text);
        name=findViewById(R.id.name_text);
        location=findViewById(R.id.location);
        cancel=findViewById(R.id.cancel2);



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 =name.getText().toString();
                String id1 = id.getText().toString();
                String location1 = location.getText().toString();

                // Check if any of the fields is empty
                if (name1.isEmpty() || id1.isEmpty() || location1.isEmpty()) {
                    Toast.makeText(CinemaActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // All fields are filled
                    Toast.makeText(CinemaActivity.this, "Confirmation Successful", Toast.LENGTH_SHORT).show();
                    // Perform your desired action here
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CinemaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}