package com.example.easycooker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class Meals_Details extends AppCompatActivity {
    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_details);
        makemeal();


    }
    private void makemeal(){
        String mealname=getIntent().getStringExtra("mealname");
        int missingIngredientsCount=getIntent().getIntExtra("missingIngredientsCount",0);
        int usedIngredientsCount=getIntent().getIntExtra("usedIngredientsCount",0);
        String instructions=getIntent().getStringExtra("instructions");
        String imageurl=getIntent().getStringExtra("imageurl");
        ArrayList<String>missingIngredients=getIntent().getStringArrayListExtra("missingIngredients");
        meal=new Meal(mealname,imageurl,missingIngredientsCount,usedIngredientsCount,missingIngredients,instructions);
    }
}
