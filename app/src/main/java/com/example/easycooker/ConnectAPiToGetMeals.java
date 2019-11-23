package com.example.easycooker;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ConnectAPiToGetMeals {
    private static ArrayList<Meal> meals;
    private static String instructions;
    private static boolean lock = false;

     public static ArrayList<Meal> execute(String selected){
        meals =new ArrayList<>();
        instructions = "";
        String url = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=28c107bdfb18450fa22d1b45c885d5e0&number=20&ingredients=" + selected;
        SearchmealsByIngredientsClass get = new SearchmealsByIngredientsClass();
        get.execute(url);
        while (!lock) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return meals;
    }
    private static String connect(String url) {
        String text = "";
        URL urll;
        try {
            urll = new URL(url);

            HttpURLConnection urlConnection = (HttpURLConnection) urll.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bureader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = bureader.readLine()) != null) {
                text += line;
            }

            in.close();
        } catch (MalformedURLException e) {
            Log.e("ERROR", e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * search meals by ingredients class executes the api to get meals
     **/
    public static class SearchmealsByIngredientsClass extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {
            String text = connect(strings[0]);
            String mealname;
            String imagesource;
            String id;
            Meal meal;
            int usedingredientscount;
            int missedingredientscount;
            ArrayList<String> missedingredients = new ArrayList<>();
            JSONArray arrJson;
            JSONArray arrofmissedingredients;
            try {
                arrJson = new JSONArray(text);
                //arrJson = jsonObj.getJSONArray("");
                for (int i = 0; i < arrJson.length(); i++) {

                    JSONObject mealobj = arrJson.getJSONObject(i);
                    id = mealobj.getString("id");
                    mealname = mealobj.getString("title");
                    imagesource = mealobj.getString("image");
                    usedingredientscount = mealobj.getInt("usedIngredientCount");
                    missedingredientscount = mealobj.getInt("missedIngredientCount");
                    arrofmissedingredients = mealobj.getJSONArray("missedIngredients");
                    for (int j = 0; j < arrofmissedingredients.length(); j++) {
                        JSONObject objj = arrofmissedingredients.getJSONObject(j);
                        missedingredients.add(objj.getString("name"));
                    }
                    meal = new Meal(id,mealname, imagesource, missedingredientscount, usedingredientscount, missedingredients);
                    String url2 = "https://api.spoonacular.com/recipes/" + id + "/analyzedInstructions?apiKey=28c107bdfb18450fa22d1b45c885d5e0";
                    GetRecipeInstructions instruction = new GetRecipeInstructions();
                    instruction.onPostExecute(instruction.doInBackground(url2));
                    meal.setInstructions(instructions);
                    meals.add(meal);
                }
                lock = true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return text;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

        }

        /**
         * get recipe instructions class is used to use api in getting the steps of each searched meal
         **/
        static class GetRecipeInstructions extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
               return connect(strings[0]);
            }

            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                instructions = "";
                try {
                    JSONArray arrjson = new JSONArray(response);
                    if (arrjson.length() == 0) {
                        System.out.println("no response");
                        return;
                    }
                    JSONObject obj = arrjson.getJSONObject(0);
                    JSONArray steps = obj.getJSONArray("steps");
                    for (int i = 0; i < steps.length(); i++) {
                        JSONObject step = steps.getJSONObject(i);
                        int number_of_step = step.getInt("number");
                        String inst = step.getString("step");
                        instructions += number_of_step + "-" + inst + "\n";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
