package com.example.easycooker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Select_ingredients extends AppCompatActivity {
    private ArrayList<Type_of_ingredients> general_ingredients=new ArrayList<>();
    private ExpandableListView list;
    private Button random_meal;
    private Button get_meal;
    private ArrayList<String> selected_ingredients = new ArrayList<>();
    private  BaseExpandableListAdapter p;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ingredients);
        list = findViewById(R.id.list_of_types);
        random_meal = findViewById(R.id.random_meal);
        get_meal = findViewById(R.id.get_meal);
        fillIngredientsList();
        p = new Expandable_ingredients_list(this, general_ingredients, list);
        list.setAdapter(p);
        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                addSelectedIngredients(parent,v,groupPosition,childPosition,id);
                return true;
            }
        });
        get_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String selected="";
                for(int i=0;i<list.getExpandableListAdapter().getGroupCount();i++){
                    for(int j=0;j<list.getExpandableListAdapter().getChildrenCount(i);j++){
                        list.getExpandableListAdapter().getChild(i,j).equals(false);
                    }
                }
                for(int i=0;i<selected_ingredients.size();i++){
                    selected+=selected_ingredients.get(i);
                    if(i!=selected_ingredients.size()-1)
                         selected+=",+";
                }

                Intent intent=new Intent(com.example.easycooker.Select_ingredients.this, List_of_meals.class);
                intent.putExtra("selected",selected);
                startActivity(intent);

            }
        });
        random_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    /**shows selected ingredients array list in a toast**/
    private void showSelectedIngredients(){
        String all="";
        for(int i=0;i<selected_ingredients.size();i++){
            all+=selected_ingredients.get(i);
            all+=" ";
        }
        Toast.makeText(getBaseContext(),all,Toast.LENGTH_LONG).show();
    }
    /**puts all ingredients that the user chooses from in ingredients list**/
    private void fillIngredientsList(){
        ArrayList<String> veg = new ArrayList<>();
        veg.add("tomato");
        veg.add("onion");
        veg.add("bell pepper");
        veg.add("corn");
        veg.add("lettuce");
        veg.add("carrots");
        ArrayList<String> spices = new ArrayList<>();
        spices.add("pepper");
        spices.add("salt");
        spices.add("oregano");
        spices.add("hot pepper");
        spices.add("cinnamon");
        spices.add("sugar");
        ArrayList<String> meats = new ArrayList<>();
        meats.add("chicken breasts");
        meats.add("meat cubes");
        meats.add("minced meats");
        meats.add("whole chicken");
        meats.add("ribs");
        meats.add("burgers");
        meats.add("kofta");
        ArrayList<String> diary = new ArrayList<>();
        diary.add("milk");
        diary.add("yogurt");
        diary.add("butter");
        diary.add("white cheese");
        diary.add("romi cheese");
        diary.add("mozzarella");
        diary.add("chedder");
        ArrayList<String> seafoods = new ArrayList<>();
        seafoods.add("fish fillet");
        seafoods.add("whole fish");
        seafoods.add("shrimps");
        seafoods.add("calimari");
        seafoods.add("tuna");
        ArrayList<String> sauces = new ArrayList<>();
        sauces.add("ketchup");
        sauces.add("mayonnaise");
        sauces.add("mustard");
        sauces.add("caesar");
        sauces.add("bbq");

        general_ingredients.add(new Type_of_ingredients("vegetables", veg));
        general_ingredients.add(new Type_of_ingredients("spices", spices));
        general_ingredients.add(new Type_of_ingredients("meats", meats));
        general_ingredients.add(new Type_of_ingredients("diary", diary));
        general_ingredients.add(new Type_of_ingredients("seafoods", seafoods));
        general_ingredients.add(new Type_of_ingredients("sauces", sauces));
    }
    /**this function is called each time child is selected to add or remove it from selcted ingredients**/
    private void addSelectedIngredients(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
        CheckBox ingredients_selection=list.getExpandableListAdapter().getChildView(groupPosition,childPosition,false,v,parent).findViewById(R.id.ingredients_checkbox);
        String name_of_ingredient=ingredients_selection.getText().toString();
        ingredients_selection.setClickable(true);
        ingredients_selection.setFocusable(true);
        if(ingredients_selection.isChecked()){
            ingredients_selection.setChecked(false);
            if(selected_ingredients.contains(name_of_ingredient)){
                selected_ingredients.remove(name_of_ingredient);
            }
            ingredients_selection.setClickable(false);
            ingredients_selection.setFocusable(false);

        }
        else if(!ingredients_selection.isChecked()){
            ingredients_selection.setChecked(true);
            if(!selected_ingredients.contains(name_of_ingredient))
                selected_ingredients.add(name_of_ingredient);
            ingredients_selection.setClickable(false);
            ingredients_selection.setFocusable(false);

        }
    }


}
