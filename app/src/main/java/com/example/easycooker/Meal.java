package com.example.easycooker;

import java.util.ArrayList;

public class Meal {
    private String title;
    private String image_url;
    private int missing_ingredients_count;
    private int used_ingredients_count;
    private ArrayList<String> missing_ingredients;
    private String instructions;

    public Meal(String title, String image_url, int missing_ingredients_count, int used_ingredients_count, ArrayList<String> missing_ingredients) {
        this.title = title;
        this.image_url = image_url;
        this.missing_ingredients_count = missing_ingredients_count;
        this.used_ingredients_count = used_ingredients_count;
        this.missing_ingredients = missing_ingredients;
        instructions ="";
    }

    public Meal(String title, String image_url, int missing_ingredients_count, int used_ingredients_count, ArrayList<String> missing_ingredients,String instructions) {
        this.title = title;
        this.image_url = image_url;
        this.missing_ingredients_count = missing_ingredients_count;
        this.used_ingredients_count = used_ingredients_count;
        this.missing_ingredients = missing_ingredients;
        this.instructions =instructions;
    }
    public Meal(){
        this.title = "";
        this.image_url = "";
        this.missing_ingredients_count = 0;
        this.used_ingredients_count = 0;
        this.instructions = "";
        missing_ingredients=new ArrayList<>();
    }

    public ArrayList<String> getMissing_ingredients() {
        return missing_ingredients;
    }

    public void setMissing_ingredients(ArrayList<String> missing_ingredients) {
        this.missing_ingredients = missing_ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getMissing_ingredients_count() {
        return missing_ingredients_count;
    }

    public void setMissing_ingredients_count(int missing_ingredients_count) {
        this.missing_ingredients_count = missing_ingredients_count;
    }

    public int getUsed_ingredients_count() {
        return used_ingredients_count;
    }

    public void setUsed_ingredients_count(int used_ingredients_count) {
        this.used_ingredients_count = used_ingredients_count;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
