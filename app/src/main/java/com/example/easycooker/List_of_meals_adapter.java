package com.example.easycooker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class List_of_meals_adapter extends RecyclerView.Adapter<List_of_meals_adapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Meal item);
    }

    ArrayList<Meal> meals;
    private LayoutInflater mInflater;
    private final OnItemClickListener listener;


    public List_of_meals_adapter(ArrayList<Meal> meals, OnItemClickListener listener) {
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_meals_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull List_of_meals_adapter.ViewHolder holder, int position) {
        holder.bind(meals.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mealimage;
        TextView mealname;
        TextView missingingredients;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealimage = itemView.findViewById(R.id.mealimage);
            mealname = itemView.findViewById(R.id.mealname);
            missingingredients = itemView.findViewById(R.id.missingingredientstextview);
        }

        public void bind(final Meal item, final OnItemClickListener listener) {
            String missing = Integer.toString(item.getMissing_ingredients_count());
            mealname.setText(item.getTitle());
            missingingredients.setText(missing);
            Picasso.get().load(item.getImage_url()).into(mealimage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
