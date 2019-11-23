package com.example.easycooker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Meals_Details extends AppCompatActivity {
    Meal meal;
    ImageView mealimage;
    TextView mealnameText;
    TextView missingText;
    TextView instructionText;
    Button ordermissingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_details);
        mealimage=findViewById(R.id.meal_imageView);
        mealnameText=findViewById(R.id.mealname);
        missingText=findViewById(R.id.missingingredientstextview);
        instructionText=findViewById(R.id.mealinstruction);
        ordermissingButton=findViewById(R.id.order_button);
        makemeal();
        String missing_ingredients="";
        for(int i=0;i<meal.getMissing_ingredients().size();i++){
            missing_ingredients+=meal.getMissing_ingredients().get(i);
            if(i!=meal.getMissing_ingredients().size()-1){
                missing_ingredients+=",";
            }
        }
        Picasso.get().load(meal.getImage_url()).into(mealimage);
        mealnameText.setText(meal.getTitle());
        missingText.setText(missing_ingredients);
        instructionText.setText(meal.getInstructions());
        System.out.println(meal.getInstructions());
        ordermissingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
