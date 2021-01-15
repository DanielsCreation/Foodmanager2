/*
package com.android.foodmanager2.ui.datenbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodmanager2.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private ArrayList food_id, food_name, food_brand, food_unit, food_quantity;

    CustomAdapter(Context context,
                  ArrayList food_id,
                  ArrayList food_name,
                  ArrayList food_brand,
                  ArrayList food_unit,
                  ArrayList food_quantity) {
        this.context = context;
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_brand = food_brand;
        this.food_unit = food_unit;
        this.food_quantity = food_quantity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.food_id_txt.setText(String.valueOf(food_id.get(position)));
        holder.food_name_txt.setText(String.valueOf(food_name.get(position)));
        holder.food_brand_txt.setText(String.valueOf(food_brand.get(position)));
        holder.food_unit_txt.setText(String.valueOf(food_unit.get(position)));
        holder.food_quantity_txt.setText(String.valueOf(food_quantity.get(position)));
    }

    @Override
    public int getItemCount() {
        return food_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView food_id_txt, food_name_txt, food_brand_txt, food_unit_txt, food_quantity_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            food_id_txt = itemView.findViewById(R.id.food_id_txt);
            food_name_txt = itemView.findViewById(R.id.food_name_txt);
            food_brand_txt = itemView.findViewById(R.id.food_brand_txt);
            food_unit_txt = itemView.findViewById(R.id.food_unit_txt);
            food_quantity_txt = itemView.findViewById(R.id.food_quantity_txt);
        }
    }
}
*/