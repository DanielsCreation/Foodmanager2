package com.android.foodmanager2.food;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.foodmanager2.R;
import com.android.foodmanager2.main.SpinnerHelper;

public class AddEditFoodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//intent components
    public static final String EXTRA_ID =
            "com.android.foodmanager2.food.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.android.foodmanager2.food.EXTRA_NAME";
    public static final String EXTRA_BRAND =
            "com.android.foodmanager2.food.EXTRA_BRAND";
    public static final String EXTRA_CONTENTS =
            "com.android.foodmanager2.food.EXTRA_CONTENTS";
    public static final String EXTRA_UNIT =
            "com.android.foodmanager2.food.EXTRA_UNIT";
    public static final String EXTRA_QUANTITY =
            "com.android.foodmanager2.food.EXTRA_QUANTITY";
    public static final String EXTRA_KCAL =
            "com.android.foodmanager2.food.EXTRA_KCAL";
    public static final String EXTRA_PROTEIN =
            "com.android.foodmanager2.food.EXTRA_PROTEIN";
    public static final String EXTRA_CARB =
            "com.android.foodmanager2.food.EXTRA_CARB ";
    public static final String EXTRA_FAT =
            "com.android.foodmanager2.food.EXTRA_FAT";
    public static final String EXTRA_SUGAR =
            "com.android.foodmanager2.food.EXTRA_SUGAR";
    public static final String EXTRA_SATURATED =
            "com.android.foodmanager2.food.EXTRA_SATURATED";
    public static final String EXTRA_STORAGE =
            "com.android.foodmanager2.food.EXTRA_STORAGE";
    public static final String EXTRA_VEGETARIAN =
            "com.android.foodmanager2.food.EXTRA_VEGETARIAN";
    public static final String EXTRA_VEGAN =
            "com.android.foodmanager2.food.EXTRA_VEGAN";
    public static final String EXTRA_GLUTENFREE =
            "com.android.foodmanager2.food.EXTRA_GLUTENFREE";
    public static final String EXTRA_LAKTOFREE =
            "com.android.foodmanager2.food.EXTRA_LAKTOFREE";

    private EditText name_input, brand_input, contents_input, kcal_input, protein_input, carb_input, fat_input, sugar_input, saturated_input;
    private Spinner unit_input, storage_input;
    private CheckBox vegetarian_CheckBox, vegan_CheckBox, glutenfree_CheckBox, laktofree_CheckBox;

//displays the new layout
//initializes the edit text values (they can be changed by user input)
//checks if id exists --> if yes, EditText is set with intent extras and displayed
//-----------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        name_input = findViewById(R.id.name_input);
        brand_input = findViewById(R.id.brand_input);
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

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Bearbeiten");

            name_input.setText(intent.getStringExtra(EXTRA_NAME));
            brand_input.setText(intent.getStringExtra(EXTRA_BRAND));
            contents_input.setText(intent.getStringExtra(EXTRA_CONTENTS));
            unit_input.setSelection(SpinnerHelper.helpUnit(intent.getStringExtra(EXTRA_UNIT)));
            kcal_input.setText(intent.getStringExtra(EXTRA_KCAL));
            protein_input.setText(intent.getStringExtra(EXTRA_PROTEIN));
            carb_input.setText(intent.getStringExtra(EXTRA_CARB));
            fat_input.setText(intent.getStringExtra(EXTRA_FAT));
            sugar_input.setText(intent.getStringExtra(EXTRA_SUGAR));
            saturated_input.setText(intent.getStringExtra(EXTRA_SATURATED));
            storage_input.setSelection(SpinnerHelper.helpStorage(intent.getStringExtra(EXTRA_STORAGE)));
            vegetarian_CheckBox.setChecked((intent.getBooleanExtra(EXTRA_VEGETARIAN, false)));
            vegan_CheckBox.setChecked((intent.getBooleanExtra(EXTRA_VEGAN, false)));
            glutenfree_CheckBox.setChecked((intent.getBooleanExtra(EXTRA_GLUTENFREE, false)));
            laktofree_CheckBox.setChecked((intent.getBooleanExtra(EXTRA_LAKTOFREE, false)));
        } else {
            setTitle("Hinzufügen");
        }
    }


//gets triggered when save in the toolbar gets clicked
//input gets assigned to local variables, trimmed and tested if empty
//local variables are put into new intent "data"
//id gets incremented with the creation of data, checked and set with extra
//setResult RESULT_OK and "data" and Activity gets closed --> return to the MainActivity
//-----------------------------------------------------------------------------------------------------

    private void saveFood() {
        String name = name_input.getText().toString();
        String brand = brand_input.getText().toString();
        String contents =contents_input.getText().toString();
        String unit = unit_input.getSelectedItem().toString();
        float quantity = 1.0f;
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

        if (name.trim().isEmpty() || unit.trim().isEmpty() || storage.trim().isEmpty())  {
            Toast.makeText(this, "Eingabe unvollständig", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_BRAND, brand);
        data.putExtra(EXTRA_CONTENTS, contents);
        data.putExtra(EXTRA_UNIT, unit);
        data.putExtra(EXTRA_QUANTITY, quantity);
        data.putExtra(EXTRA_KCAL, kcal);
        data.putExtra(EXTRA_PROTEIN, protein);
        data.putExtra(EXTRA_CARB, carb);
        data.putExtra(EXTRA_FAT, fat);
        data.putExtra(EXTRA_SUGAR, sugar);
        data.putExtra(EXTRA_SATURATED, saturated);
        data.putExtra(EXTRA_STORAGE, storage);
        data.putExtra(EXTRA_VEGETARIAN, vegetarian);
        data.putExtra(EXTRA_VEGAN, vegan);
        data.putExtra(EXTRA_GLUTENFREE, glutenfree);
        data.putExtra(EXTRA_LAKTOFREE, laktofree);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

//overrides toolbar
//implements save button in the top right hand corner
//-----------------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_food_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.save_food:
                saveFood();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
//-----------------------------------------------------------------------------------------------------
}
