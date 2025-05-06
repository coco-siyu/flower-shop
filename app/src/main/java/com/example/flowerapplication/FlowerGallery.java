package com.example.flowerapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class FlowerGallery extends AppCompatActivity implements RecyclerViewInterface{

    ArrayList<FlowerModel> flowerModels = new ArrayList<>();
    int[] flowerImages = {
        R.drawable.rose, R.drawable.rose2,
        R.drawable.rose3,
            R.drawable.sunflower, R.drawable.daisy, R.drawable.tulip, R.drawable.calla_lily,
            R.drawable.carnation, R.drawable.cornflower, R.drawable.daffodil, R.drawable.iris,
            R.drawable.lavender, R.drawable.magnolia, R.drawable.orchid, R.drawable.peony
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_gallery);

        // call in recycler view
        RecyclerView recyclerView = findViewById(R.id.gallery);
        // set up flower data
        setUpFlowerModels();

        FlowerRecyclerViewAdapter flowerRecyclerViewAdapter = new FlowerRecyclerViewAdapter(this, flowerModels, this);
        recyclerView.setAdapter(flowerRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpFlowerModels(){
        String[] flowerNames = getResources().getStringArray(R.array.flower_name);
        String[] flowerSymbolism = getResources().getStringArray(R.array.flower_symbolism);
        String[] flowerStory = getResources().getStringArray(R.array.flower_story);
        // get string item from strings.xml
        for(int i =0; i< flowerNames.length; i++){
            flowerModels.add(new FlowerModel(flowerNames[i],
                    flowerSymbolism[i],
                    flowerStory[i],
                    flowerImages[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DisplayFlowerInfo.class);

        intent.putExtra("NAME", flowerModels.get(position).getFlower_Name());
        intent.putExtra("SYMBOLISM", flowerModels.get(position).getFlower_Symbolism());
        intent.putExtra("STORY", flowerModels.get(position).getFlower_Story());
        intent.putExtra("IMAGE", flowerModels.get(position).getImg());

        startActivity(intent);
    }
}