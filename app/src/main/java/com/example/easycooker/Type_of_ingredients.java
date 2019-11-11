package com.example.easycooker;

import java.util.ArrayList;

public class Type_of_ingredients {
    private String type;
    private ArrayList<String> ingredients;

    public Type_of_ingredients(String type, ArrayList<String> ingredients){
        this.type = type;
        this.ingredients = ingredients;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
