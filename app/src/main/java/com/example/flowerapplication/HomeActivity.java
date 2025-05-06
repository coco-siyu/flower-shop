package com.example.flowerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Button classify, add, flowerGallery, tip;
    private EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        classify = (Button) findViewById(R.id.classify);
        add = (Button) findViewById(R.id.addData);
        tip = (Button) findViewById(R.id.tips);
        flowerGallery = (Button) findViewById(R.id.gallery);

        username = (EditText) findViewById(R.id.userName_home);

        flowerGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Go to classify", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FlowerGallery.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });
        classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Go to classify", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ClassifyFlower.class);
                startActivity(intent);
            }
        });
    }
}