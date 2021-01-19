package com.android.foodmanager2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodmanager2.R;
import com.android.foodmanager2.main.MainActivity;
import com.android.foodmanager2.model.Food;
import com.android.foodmanager2.adapter.FoodAdapter;
import com.android.foodmanager2.viewmodel.FoodViewModel;

import java.util.List;


public class AddPurchaseActivity extends AppCompatActivity {
    private FoodViewModel foodViewModel;
    FoodAdapter adapter = new FoodAdapter();

    public static final String EXTRA_PURCHASEID =
            "com.android.foodmanager2.purchase.EXTRA_PURCHASE";
    public static final String EXTRA_QUANTITY =
            "com.android.foodmanager2.purchase.EXTRA_QUANTITY";
    public static final String EXTRA_BBD =
            "com.android.foodmanager2.purchase.EXTRA_BBD";
    public static final String EXTRA_PURCHASEDATE =
            "com.android.foodmanager2.purchase.EXTRA_PURCHASEDATE";
    public static final String EXTRA_BOUGHT =
            "com.android.foodmanager2.purchase.EXTRA_BOUGHT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        foodViewModel.getAllFoods().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> foods) {
                adapter.submitList(foods);
            }
        });

        //the goal is to enable the user to add purchase items fast
        //default values (and other values) can be added later after selecting them in PurchaseFragment

        getSupportActionBar();
        setTitle("Einkauf hinzufügen");
        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Food food) {
            //Toast.makeText(AddPurchaseActivity.this, "geht", Toast.LENGTH_SHORT);
            Intent intent = getIntent();
            intent.putExtra(AddEditFoodActivity.EXTRA_FOODID, food.getFoodId());
            intent.putExtra(AddEditFoodActivity.EXTRA_NAME, food.getName());
            intent.putExtra(AddEditFoodActivity.EXTRA_BRAND, food.getBrand());
            intent.putExtra(AddEditFoodActivity.EXTRA_STORE, food.getStore());
            intent.putExtra(AddEditFoodActivity.EXTRA_PRICE, food.getPrice());
            intent.putExtra(AddEditFoodActivity.EXTRA_CONTENTS, food.getContents());
            intent.putExtra(AddEditFoodActivity.EXTRA_UNIT, food.getUnit());
            intent.putExtra(AddEditFoodActivity.EXTRA_KCAL, food.getKcal());
            intent.putExtra(AddEditFoodActivity.EXTRA_PROTEIN, food.getProtein());
            intent.putExtra(AddEditFoodActivity.EXTRA_CARB, food.getCarb());
            intent.putExtra(AddEditFoodActivity.EXTRA_FAT, food.getFat());
            intent.putExtra(AddEditFoodActivity.EXTRA_SUGAR, food.getSugar());
            intent.putExtra(AddEditFoodActivity.EXTRA_SATURATED, food.getSaturated());
            intent.putExtra(AddEditFoodActivity.EXTRA_STORAGE, food.getStorage());
            intent.putExtra(AddEditFoodActivity.EXTRA_VEGETARIAN, food.getVegetarian());
            intent.putExtra(AddEditFoodActivity.EXTRA_VEGAN, food.getVegan());
            intent.putExtra(AddEditFoodActivity.EXTRA_GLUTENFREE, food.getGlutenfree());
            intent.putExtra(AddEditFoodActivity.EXTRA_LAKTOFREE, food.getLaktofree());

            //default values
            intent.putExtra(EXTRA_PURCHASEDATE, "");
            intent.putExtra(EXTRA_BBD, "");
            intent.putExtra(EXTRA_QUANTITY, 1.0f);
            intent.putExtra(EXTRA_BOUGHT, false);


            int purchaseId = getIntent().getIntExtra(EXTRA_PURCHASEID, -1);
            if (purchaseId != -1) {
                intent.putExtra(EXTRA_PURCHASEID, purchaseId);
            }
            setResult(RESULT_OK, intent);
            showToast(1, food.getName());
        }
        });
    }

    private Toast mToast;
    protected void showToast(int value, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(AddPurchaseActivity.this, text+" hinzugefügt", value);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(AddPurchaseActivity.this, text+" hinzugefügt", value);
        }
        mToast.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_add_purchase, menu);
        MenuItem searchItem = menu.findItem(R.id.search_purchase);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && newText.length() > 0) {
                    newText = newText.toLowerCase().trim();
                    foodViewModel.getFoodsByName(newText).observe(AddPurchaseActivity.this, new Observer<List<Food>>() {
                        @Override
                        public void onChanged(@Nullable List<Food> foods) {
                            adapter.submitList(foods);
                        }
                    });
                    return false;

                } else {
                    foodViewModel.getAllFoods().observe(AddPurchaseActivity.this, new Observer<List<Food>>() {
                        @Override
                        public void onChanged(@Nullable List<Food> foods) {
                            adapter.submitList(foods);
                        }
                    });
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
