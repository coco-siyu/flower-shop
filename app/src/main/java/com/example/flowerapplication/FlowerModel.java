package com.example.flowerapplication;

public class FlowerModel {
    String flower_Name;
    String flower_Symbolism, flower_Story;
    int img;

    public FlowerModel(String flower_Name, String flower_Symbolism, String flower_Story, int img) {
        this.flower_Name = flower_Name;
        this.flower_Symbolism = flower_Symbolism;
        this.flower_Story = flower_Story;
        this.img = img;
    }

    public String getFlower_Name() {
        return flower_Name;
    }

    public String getFlower_Story() {
        return flower_Story;
    }

//    public void setFlower_Name(String flower_Name) {
//        this.flower_Name = flower_Name;
//    }

    public String getFlower_Symbolism() {
        return flower_Symbolism;
    }

//    public void setFlower_Symbolism(String flower_Symbolism) {
//        this.flower_Symbolism = flower_Symbolism;
//    }

    public int getImg() {
        return img;
    }

//    public void setImg(int img) {
//        this.img = img;
//    }
}
