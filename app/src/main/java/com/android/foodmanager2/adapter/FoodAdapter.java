package com.android.foodmanager2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodmanager2.R;
import com.android.foodmanager2.model.Food;

public class FoodAdapter extends ListAdapter<Food, FoodAdapter.FoodHolder> {
    public OnItemClickListener listener;

    public FoodAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Food> DIFF_CALLBACK = new DiffUtil.ItemCallback<Food>() {
        @Override
        public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
            return oldItem.getFoodId() == newItem.getFoodId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    (oldItem.getBrand().equals(newItem.getBrand()) || (oldItem.getBrand().isEmpty() && newItem.getBrand().isEmpty())) &&
                    (oldItem.getContents().equals(newItem.getContents()) || (oldItem.getContents().isEmpty() && newItem.getContents().isEmpty())) &&
                    (oldItem.getStore().equals(newItem.getStore()) || (oldItem.getStore().isEmpty() && newItem.getStore().isEmpty())) &&
                    (oldItem.getPrice().equals(newItem.getPrice()) || (oldItem.getPrice().isEmpty() && newItem.getPrice().isEmpty())) &&
                    (oldItem.getUnit().equals(newItem.getUnit()) || (oldItem.getUnit().isEmpty() && newItem.getUnit().isEmpty())) &&
                    (oldItem.getKcal().equals(newItem.getKcal()) || (oldItem.getKcal().isEmpty() && newItem.getKcal().isEmpty())) &&
                    (oldItem.getFat().equals(newItem.getFat()) || (oldItem.getFat().isEmpty() && newItem.getFat().isEmpty())) &&
                    (oldItem.getSaturated().equals(newItem.getSaturated()) || (oldItem.getSaturated().isEmpty() && newItem.getSaturated().isEmpty())) &&
                    (oldItem.getStorage().equals(newItem.getStorage()) || (oldItem.getStorage().isEmpty() && newItem.getStorage().isEmpty())) &&
                    (oldItem.getCarb().equals(newItem.getCarb()) || (oldItem.getCarb().isEmpty() && newItem.getCarb().isEmpty())) &&
                    (oldItem.getSugar().equals(newItem.getSugar()) || (oldItem.getSugar().isEmpty() && newItem.getSugar().isEmpty())) &&
                    (oldItem.getProtein().equals(newItem.getProtein()) || (oldItem.getProtein().isEmpty() && newItem.getProtein().isEmpty())) &&
                    oldItem.getVegetarian() == newItem.getVegetarian() &&
                    oldItem.getVegan() == newItem.getVegan() &&
                    oldItem.getGlutenfree() == newItem.getGlutenfree() &&
                    oldItem.getLaktofree() == newItem.getLaktofree();
        }
    };

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View binding = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);
        return new FoodHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        Food currentFood = getItem(position);
        holder.textViewId.setText(String.valueOf(currentFood.getFoodId()));
        holder.textViewName.setText(currentFood.getName());
        holder.textViewBrand.setText(currentFood.getBrand());
        holder.textViewStore.setText(currentFood.getStore());
        holder.textViewPrice.setText(currentFood.getPrice());
        holder.textViewContents.setText(currentFood.getContents());
        holder.textViewUnit.setText(currentFood.getUnit());
        if (currentFood.getKcal() != "") {holder.textViewKcal.setText("kcal: "+currentFood.getKcal());} else {holder.textViewKcal.setText("kcal: -");}
        if (currentFood.getFat() != "") {holder.textViewFat.setText("Fett: "+currentFood.getFat());} else {holder.textViewFat.setText("kcal: -");}
        if (currentFood.getSaturated() != "") {holder.textViewSaturated.setText("davon gesättigt: "+currentFood.getSaturated());} else {holder.textViewSaturated.setText("kcal: -");}
        if (currentFood.getCarb() != "") {holder.textViewCarb.setText("Kohlenhydrate: "+currentFood.getCarb());} else {holder.textViewCarb.setText("kcal: -");}
        if (currentFood.getSugar() != "") {holder.textViewSugar.setText("davon Zucker: "+currentFood.getSugar());} else {holder.textViewSugar.setText("kcal: -");}
        if (currentFood.getProtein() != "") {holder.textViewProtein.setText("Eiweiß: "+currentFood.getProtein());} else {holder.textViewProtein.setText("kcal: -");}
        if (currentFood.getVegetarian()) {holder.textViewVegetarian.setText("vegetarisch: ja");} else {holder.textViewVegetarian.setText("vegetarisch: nein");}
        if (currentFood.getVegan()) {holder.textViewVegan.setText("vegan: ja");} else {holder.textViewVegan.setText("vegan: nein");}
        if (currentFood.getGlutenfree()) {holder.textViewGlutenfree.setText("glutenfrei: ja");} else {holder.textViewGlutenfree.setText("glutenfrei: nein");}
        if (currentFood.getLaktofree()) {holder.textViewLaktofree.setText("laktosefrei: ja");} else {holder.textViewLaktofree.setText("laktosefrei: nein");}
    }

    //takes position, returns item
//-----------------------------------------------------------------------------------------------------
    public Food getFoodAt(int position) {
        return getItem(position);
    }

    //inner class
//-----------------------------------------------------------------------------------------------------
    class FoodHolder extends RecyclerView.ViewHolder {
        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewStore;
        private TextView textViewPrice;
        private TextView textViewBrand;
        private TextView textViewContents;
        private TextView textViewUnit;
        private TextView textViewKcal;
        private TextView textViewFat;
        private TextView textViewSaturated;
        private TextView textViewCarb;
        private TextView textViewSugar;
        private TextView textViewProtein;
        private TextView textViewVegetarian;
        private TextView textViewVegan;
        private TextView textViewGlutenfree;
        private TextView textViewLaktofree;


        public FoodHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.food_id_txt);
            textViewName = itemView.findViewById(R.id.food_name_txt);
            textViewBrand = itemView.findViewById(R.id.food_brand_txt);
            textViewStore = itemView.findViewById(R.id.food_store_txt);
            textViewPrice = itemView.findViewById(R.id.food_price_txt);
            textViewContents = itemView.findViewById(R.id.food_contents_txt);
            textViewUnit = itemView.findViewById(R.id.food_unit_txt);
            textViewKcal = itemView.findViewById(R.id.food_kcal_txt);
            textViewFat = itemView.findViewById(R.id.food_fat_txt);
            textViewSaturated = itemView.findViewById(R.id.food_saturated_txt);
            textViewCarb = itemView.findViewById(R.id.food_carb_txt);
            textViewSugar = itemView.findViewById(R.id.food_sugar_txt);
            textViewProtein = itemView.findViewById(R.id.food_protein_txt);
            textViewVegetarian = itemView.findViewById(R.id.food_vegetarian_txt);
            textViewVegan = itemView.findViewById(R.id.food_vegan_txt);
            textViewGlutenfree = itemView.findViewById(R.id.food_glutenfree_txt);
            textViewLaktofree = itemView.findViewById(R.id.food_laktofree_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = FoodHolder.this.getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }
    //click listener for recyclerview items
//-----------------------------------------------------------------------------------------------------
    public interface OnItemClickListener {
        void onItemClick(Food food);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}