package com.example.easycooker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class Expandable_ingredients_list extends BaseExpandableListAdapter {
    private ArrayList<Type_of_ingredients> ingredients;
    private Context context;
    private ExpandableListView ingredients_list;

    public Expandable_ingredients_list(Context context, ArrayList<Type_of_ingredients> ingredients,ExpandableListView ingredients_list){
        this.ingredients = ingredients;
        this.context = context;
        this.ingredients_list=ingredients_list;
    }

    @Override
    public int getGroupCount() {
        return ingredients.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return ingredients.get(groupPosition).getIngredients().size();
    }

    @Override
    public Type_of_ingredients getGroup(int groupPosition){
        return ingredients.get(groupPosition);
    }

    @Override
    public String  getChild(int groupPosition, int childPosition) {
        return ingredients.get(groupPosition).getIngredients().get(childPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
         Type_of_ingredients type=getGroup(groupPosition);
        TextView type_name;
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_general_ingredients_items,null);
        }
        type_name=(TextView)convertView.findViewById(R.id.typename);
        type_name.setText(type.getType());
        final ImageButton expand_button=convertView.findViewById(R.id.extend);
        expand_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isExpanded){
                     ingredients_list.expandGroup(groupPosition);
                     expand_button.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                }
                else{
                    ingredients_list.collapseGroup(groupPosition);
                    expand_button.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);

                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        final String ingredient_item=(String)getChild(groupPosition,childPosition);

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.ingredients_list_items,null);

        }
        CheckBox ingredient=convertView.findViewById(R.id.ingredients_checkbox);
        ingredient.setText(ingredient_item);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }
}
