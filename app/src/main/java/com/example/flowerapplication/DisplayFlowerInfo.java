package com.example.flowerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayFlowerInfo extends AppCompatActivity {

    Button backGallery, backHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_flower_info);

        String name = getIntent().getStringExtra("NAME");
        String story = getIntent().getStringExtra("STORY");
        String symbolism = getIntent().getStringExtra("SYMBOLISM");
        int img = getIntent().getIntExtra("IMAGE", 0);

        TextView flowername = findViewById(R.id.name);
        TextView flowersymbolism = findViewById(R.id.nameSymbolism);
        TextView flowerstory = findViewById(R.id.flowerStory);
        ImageView flowerImg = findViewById(R.id.flowerPic);

        flowerstory.setText(story);
        flowername.setText(name);
        flowersymbolism.setText(symbolism);
        flowerImg.setImageResource(img);

        backGallery = findViewById(R.id.button3);
        backHome = findViewById(R.id.button4);

        backGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), FlowerGallery.class);
                startActivity(intent);
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}