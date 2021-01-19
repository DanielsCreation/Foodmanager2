package com.android.foodmanager2.view;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.android.foodmanager2.R;
import com.android.foodmanager2.model.Food;
import com.android.foodmanager2.adapter.FoodAdapter;
import com.android.foodmanager2.util.SpinnerHelper;
import com.android.foodmanager2.viewmodel.FoodViewModel;

import java.util.List;

public class EditPurchaseActivity extends AppCompatActivity {

    private EditText quantity_input, bbd_input, purchaseDate_input, name_input, brand_input, store_input, price_input, contents_input, kcal_input, protein_input, carb_input, fat_input, sugar_input, saturated_input;
    private Spinner unit_input, storage_input;
    private CheckBox vegetarian_CheckBox, vegan_CheckBox, glutenfree_CheckBox, laktofree_CheckBox, bought_CheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_purchase);

        quantity_input = findViewById(R.id.quantity_input);
        bbd_input = findViewById(R.id.bbd_input);
        purchaseDate_input = findViewById(R.id.purchaseDate_input);
        bought_CheckBox = findViewById(R.id.bought_CheckBox);
        name_input = findViewById(R.id.name_input);
        brand_input = findViewById(R.id.brand_input);
        store_input = findViewById(R.id.store_input);
        price_input = findViewById(R.id.price_input);
        contents_input = findViewById(R.id.contents_input);
        unit_input = findViewById(R.id.unit_input);
        kcal_input = findViewById(R.id.kcal_input);
        protein_input = findViewById(R.id.protein_input);
        carb_input = findViewById(R.id.carb_input);
        fat_input = findViewById(R.id.fat_input);
        storage_input = findViewById(R.id.storage_input);
        sugar_input = findViewById(R.id.sugar_input);
        saturated_input = findViewById(R.id.saturated_input);
        vegetarian_CheckBox = findViewById(R.id.vegetarian_CheckBox);
        vegan_CheckBox = findViewById(R.id.vegan_CheckBox);
        glutenfree_CheckBox = findViewById(R.id.glutenfree_CheckBox);
        laktofree_CheckBox = findViewById(R.id.laktofree_CheckBox);

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        ArrayAdapter<CharSequence> adapter_unit = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_dropdown_item);
        unit_input.setAdapter(adapter_unit);
        adapter_unit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter_storage = ArrayAdapter.createFromResource(this, R.array.storage, android.R.layout.simple_spinner_dropdown_item);
        storage_input.setAdapter(adapter_storage);
        adapter_storage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Intent intent = getIntent();

        if (intent.hasExtra(AddPurchaseActivity.EXTRA_PURCHASEID)) {
            getSupportActionBar();
            setTitle("Einkauf bearbeiten");

            quantity_input.setText(String.valueOf(intent.getFloatExtra(AddPurchaseActivity.EXTRA_QUANTITY, 1.0f)));
            bbd_input.setText(intent.getStringExtra(AddPurchaseActivity.EXTRA_BBD));
            purchaseDate_input.setText(intent.getStringExtra(AddPurchaseActivity.EXTRA_PURCHASEDATE));
            bought_CheckBox.setChecked(intent.getBooleanExtra(AddPurchaseActivity.EXTRA_BOUGHT, false));

            name_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_NAME));
            brand_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_BRAND));
            store_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_STORE));
            price_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_PRICE));
            contents_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_CONTENTS));
            unit_input.setSelection(SpinnerHelper.helpUnit(intent.getStringExtra(AddEditFoodActivity.EXTRA_UNIT)));
            kcal_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_KCAL));
            protein_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_PROTEIN));
            carb_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_CARB));
            fat_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_FAT));
            sugar_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_SUGAR));
            saturated_input.setText(intent.getStringExtra(AddEditFoodActivity.EXTRA_SATURATED));
            storage_input.setSelection(SpinnerHelper.helpStorage(intent.getStringExtra(AddEditFoodActivity.EXTRA_STORAGE)));
            vegetarian_CheckBox.setChecked((intent.getBooleanExtra(AddEditFoodActivity.EXTRA_VEGETARIAN, false)));
            vegan_CheckBox.setChecked((intent.getBooleanExtra(AddEditFoodActivity.EXTRA_VEGAN, false)));
            glutenfree_CheckBox.setChecked((intent.getBooleanExtra(AddEditFoodActivity.EXTRA_GLUTENFREE, false)));
            laktofree_CheckBox.setChecked((intent.getBooleanExtra(AddEditFoodActivity.EXTRA_LAKTOFREE, false)));
        }
    }
    private void savePurchase() {
        int purchaseId = getIntent().getIntExtra(AddPurchaseActivity.EXTRA_PURCHASEID, -1);
        int foodId = getIntent().getIntExtra(AddEditFoodActivity.EXTRA_FOODID, -1);

        String quantity_string = quantity_input.getText().toString();
        String bbd = bbd_input.getText().toString();
        String purchaseDate = purchaseDate_input.getText().toString();
        boolean bought = bought_CheckBox.isChecked();

        String name = name_input.getText().toString();
        String brand = brand_input.getText().toString();
        String store = store_input.getText().toString();
        String price = price_input.getText().toString();
        String contents = contents_input.getText().toString();
        String unit = unit_input.getSelectedItem().toString();
        String kcal = kcal_input.getText().toString();
        String protein = protein_input.getText().toString();
        String carb = carb_input.getText().toString();
        String fat = fat_input.getText().toString();
        String sugar = sugar_input.getText().toString();
        String saturated = saturated_input.getText().toString();
        String storage = storage_input.getSelectedItem().toString();
        boolean vegetarian = vegetarian_CheckBox.isChecked();
        boolean vegan = vegan_CheckBox.isChecked();
        boolean glutenfree = glutenfree_CheckBox.isChecked();
        boolean laktofree = laktofree_CheckBox.isChecked();

        if (quantity_string.trim().isEmpty() || name.trim().isEmpty() || unit.trim().isEmpty() || storage.trim().isEmpty())  {
            Toast.makeText(this, "Eingabe unvollständig", Toast.LENGTH_SHORT).show();
            return;
        }

        Float quantity = Float.parseFloat(quantity_string);

        Intent data = new Intent();

        data.putExtra(AddPurchaseActivity.EXTRA_PURCHASEID, purchaseId);
        data.putExtra(AddPurchaseActivity.EXTRA_QUANTITY, quantity);
        data.putExtra(AddPurchaseActivity.EXTRA_BBD, bbd);
        data.putExtra(AddPurchaseActivity.EXTRA_PURCHASEDATE, purchaseDate);
        data.putExtra(AddPurchaseActivity.EXTRA_BOUGHT, bought);

        data.putExtra(AddEditFoodActivity.EXTRA_FOODID, foodId);
        data.putExtra(AddEditFoodActivity.EXTRA_NAME, name);
        data.putExtra(AddEditFoodActivity.EXTRA_BRAND, brand);
        data.putExtra(AddEditFoodActivity.EXTRA_STORE, store);
        data.putExtra(AddEditFoodActivity.EXTRA_PRICE, price);
        data.putExtra(AddEditFoodActivity.EXTRA_CONTENTS, contents);
        data.putExtra(AddEditFoodActivity.EXTRA_UNIT, unit);
        data.putExtra(AddEditFoodActivity.EXTRA_KCAL, kcal);
        data.putExtra(AddEditFoodActivity.EXTRA_PROTEIN, protein);
        data.putExtra(AddEditFoodActivity.EXTRA_CARB, carb);
        data.putExtra(AddEditFoodActivity.EXTRA_FAT, fat);
        data.putExtra(AddEditFoodActivity.EXTRA_SUGAR, sugar);
        data.putExtra(AddEditFoodActivity.EXTRA_SATURATED, saturated);
        data.putExtra(AddEditFoodActivity.EXTRA_STORAGE, storage);
        data.putExtra(AddEditFoodActivity.EXTRA_VEGETARIAN, vegetarian);
        data.putExtra(AddEditFoodActivity.EXTRA_VEGAN, vegan);
        data.putExtra(AddEditFoodActivity.EXTRA_GLUTENFREE, glutenfree);
        data.putExtra(AddEditFoodActivity.EXTRA_LAKTOFREE, laktofree);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.save:
                savePurchase();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}