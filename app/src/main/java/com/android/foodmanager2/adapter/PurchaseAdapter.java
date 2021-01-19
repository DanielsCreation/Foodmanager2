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
import com.android.foodmanager2.view.FoodFragment;
import com.android.foodmanager2.model.Purchase;

public class PurchaseAdapter extends ListAdapter<Purchase, PurchaseAdapter.PurchaseHolder> {
    public PurchaseAdapter.OnItemClickListener listener;

    public PurchaseAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Purchase> DIFF_CALLBACK = new DiffUtil.ItemCallback<Purchase>() {
        @Override
        public boolean areItemsTheSame(@NonNull Purchase oldItem, @NonNull Purchase newItem) {
            return oldItem.getPurchaseId() == newItem.getPurchaseId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Purchase oldItem, @NonNull Purchase newItem) {
            return  oldItem.getFood().getName().equals(newItem.getFood().getName()) &&
                    (oldItem.getFood().getBrand().equals(newItem.getFood().getBrand()) || (oldItem.getFood().getBrand().isEmpty() && newItem.getFood().getBrand().isEmpty())) &&
                    (oldItem.getFood().getContents().equals(newItem.getFood().getContents()) || (oldItem.getFood().getContents().isEmpty() && newItem.getFood().getContents().isEmpty())) &&
                    (oldItem.getFood().getStore().equals(newItem.getFood().getStore()) || (oldItem.getFood().getStore().isEmpty() && newItem.getFood().getStore().isEmpty())) &&
                    (oldItem.getFood().getPrice().equals(newItem.getFood().getPrice()) || (oldItem.getFood().getPrice().isEmpty() && newItem.getFood().getPrice().isEmpty())) &&
                    (oldItem.getFood().getUnit().equals(newItem.getFood().getUnit()) || (oldItem.getFood().getUnit().isEmpty() && newItem.getFood().getUnit().isEmpty())) &&
                    (oldItem.getFood().getKcal().equals(newItem.getFood().getKcal()) || (oldItem.getFood().getKcal().isEmpty() && newItem.getFood().getKcal().isEmpty())) &&
                    (oldItem.getFood().getFat().equals(newItem.getFood().getFat()) || (oldItem.getFood().getFat().isEmpty() && newItem.getFood().getFat().isEmpty())) &&
                    (oldItem.getFood().getSaturated().equals(newItem.getFood().getSaturated()) || (oldItem.getFood().getSaturated().isEmpty() && newItem.getFood().getSaturated().isEmpty())) &&
                    (oldItem.getFood().getStorage().equals(newItem.getFood().getStorage()) || (oldItem.getFood().getStorage().isEmpty() && newItem.getFood().getStorage().isEmpty())) &&
                    (oldItem.getFood().getCarb().equals(newItem.getFood().getCarb()) || (oldItem.getFood().getCarb().isEmpty() && newItem.getFood().getCarb().isEmpty())) &&
                    (oldItem.getFood().getSugar().equals(newItem.getFood().getSugar()) || (oldItem.getFood().getSugar().isEmpty() && newItem.getFood().getSugar().isEmpty())) &&
                    (oldItem.getFood().getProtein().equals(newItem.getFood().getProtein()) || (oldItem.getFood().getProtein().isEmpty() && newItem.getFood().getProtein().isEmpty())) &&
                    oldItem.getFood().getVegetarian() == newItem.getFood().getVegetarian() &&
                    oldItem.getFood().getVegan() == newItem.getFood().getVegan() &&
                    oldItem.getFood().getGlutenfree() == newItem.getFood().getGlutenfree() &&
                    oldItem.getFood().getLaktofree() == newItem.getFood().getLaktofree() &&
                    (oldItem.getPurchaseDate().equals(newItem.getPurchaseDate()) || (oldItem.getPurchaseDate().isEmpty() && newItem.getPurchaseDate().isEmpty())) &&
                    (oldItem.getBbd().equals(newItem.getBbd()) || (oldItem.getBbd().isEmpty() && newItem.getBbd().isEmpty())) &&
                    (oldItem.getBought() == (newItem.getBought()));
        }
    };

    @NonNull
    @Override
    public PurchaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View binding = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchase_item, parent, false);
        return new PurchaseHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull PurchaseHolder holder, int position) {
        Purchase currentPurchase = getItem(position);
        Food currentFood = currentPurchase.getFood();

        holder.textViewPurchaseId.setText(String.valueOf(currentPurchase.getPurchaseId()));
        holder.textViewQuantity.setText(String.valueOf(currentPurchase.getQuantity()));
        holder.textViewDate.setText(currentPurchase.getPurchaseDate());
        holder.textViewBbd.setText(currentPurchase.getBbd());
        if (currentPurchase.getBought()) {holder.textViewBought.setText("gekauft");} else {holder.textViewBought.setText("nicht gekauft");}

        holder.textViewFoodId.setText(String.valueOf(currentFood.getFoodId()));
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
    public Purchase getPurchaseAt(int position) {
        return getItem(position);
    }


    //inner class
//-----------------------------------------------------------------------------------------------------
    class PurchaseHolder extends RecyclerView.ViewHolder {
        private TextView textViewPurchaseId;
        private TextView textViewQuantity;
        private TextView textViewDate;
        private TextView textViewBbd;
        private TextView textViewBought;
        private TextView textViewFoodId;
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


        public PurchaseHolder(View itemView) {
            super(itemView);
            textViewPurchaseId = itemView.findViewById(R.id.purchase_id_txt);
            textViewQuantity = itemView.findViewById(R.id.purchase_quantity_txt);
            textViewDate = itemView.findViewById(R.id.purchase_date_txt);
            textViewBbd = itemView.findViewById(R.id.purchase_bbd_txt);
            textViewBought = itemView.findViewById(R.id.purchase_bought_txt);
            textViewFoodId = itemView.findViewById(R.id.food_id_txt);
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
                    int position = PurchaseHolder.this.getAdapterPosition();
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
        void onItemClick(Purchase purchase);
    }

    public void setOnItemClickListener(PurchaseAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}



