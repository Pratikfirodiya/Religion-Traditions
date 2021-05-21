package com.example.myapplication.Models;

public class FavouritesModel {
    private String imageUrl;
    private String steps;
    private String desc;

    public FavouritesModel(String imageUrl, String steps, String desc) {
        this.imageUrl = imageUrl;
        // Toast.makeText(context,"shvjds",Toast.LENGTH_SHORT).show();
        this.steps = steps;
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image) {
        this.imageUrl = imageUrl;
    }
     public  void setSteps(String steps){
        this.steps=steps;

     }
     public  String getSteps()
     {
         return  steps;
     }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
