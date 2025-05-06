package com.example.flowerapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlowerRecyclerViewAdapter extends RecyclerView.Adapter<FlowerRecyclerViewAdapter.ViewHolder> {
    private ArrayList<FlowerModel> flowerModelArrayList;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;
    public FlowerRecyclerViewAdapter(Context context, ArrayList<FlowerModel> flowerModelArrayList, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.flowerModelArrayList = flowerModelArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate the layout
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_row, viewGroup, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerRecyclerViewAdapter.ViewHolder holder, int position) {
        // assign values to the views
        holder.f_name.setText(flowerModelArrayList.get(position).getFlower_Name());
        holder.f_symbolism.setText(flowerModelArrayList.get(position).getFlower_Symbolism());
        holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.img.setImageResource(flowerModelArrayList.get(position).getImg());
        holder.img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    // to use recycler view, it needs to know how many number of items to be displayed
    public int getItemCount() {
        return flowerModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        // retrieve the views from recycler_view_row layout
        private TextView f_name, f_symbolism;
        private ImageView img;
        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface){
            super(view);
            f_name = (TextView) view.findViewById(R.id.flowerName);
            img = (ImageView) view.findViewById(R.id.flowerPic);
            f_symbolism = (TextView) view.findViewById(R.id.flowerSymbolism);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // check if the interface is null
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        // make sure the position is valid
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
