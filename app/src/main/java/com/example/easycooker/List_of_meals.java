package com.example.easycooker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class List_of_meals extends AppCompatActivity {
    private static ArrayList<Meal> meals;
    List_of_meals_adapter adapter;
    RecyclerView list;
    LinearLayoutManager myLinearLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_meals);
        String selected="";
        selected = getIntent().getStringExtra("selected");
        meals=ConnectAPiToGetMeals.execute(selected);
        list = findViewById(R.id.list_of_meals);
        list.setLayoutManager(myLinearLayoutManger);
        List_of_meals_adapter.OnItemClickListener listener=new List_of_meals_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meal item) {
                Intent in=new Intent(List_of_meals.this,Meals_Details.class);
                in.putExtra("mealname",item.getTitle());
                in.putExtra("missingIngredientsCount",item.getMissing_ingredients_count());
                in.putExtra("usedIngredientsCount",item.getUsed_ingredients_count());
                in.putExtra("instructions",item.getInstructions());
                in.putExtra("imageurl",item.getImage_url());
                in.putExtra("missingIngredients",item.getMissing_ingredients());
                startActivity(in);
            }
        };
        adapter = new List_of_meals_adapter(meals, listener);
        list.setAdapter(adapter);


    }



}