package com.android.foodmanager2.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodmanager2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FoodFragment extends Fragment {
    public static final int ADD_FOOD_REQUEST = 1;
    public static final int EDIT_FOOD_REQUEST = 2;
    private static final int RESULT_OK = -1;
    FoodViewModel foodViewModel;
    FoodAdapter adapter = new FoodAdapter();

    //inflates the layout with widgets and sets up an onChanged method to display life data supported by the view model
//-----------------------------------------------------------------------------------------------------
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_datenbank, container, false);
        FloatingActionButton add_button = (FloatingActionButton) root.findViewById(R.id.add_button);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        foodViewModel =
                new ViewModelProvider(this).get(FoodViewModel.class);
        foodViewModel.getAllFoods().observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> foods) {
                adapter.submitList(foods);

            }
        });
        recyclerView.smoothScrollToPosition(0);
//creates swipe functionality to delete entries
//-----------------------------------------------------------------------------------------------------
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                foodViewModel.delete(adapter.getFoodAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Lebensmittel gelöscht", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

//changes from MainActivity to AddEditFoodActivity...
//...with an empty intent and an ADD_REQUEST by clicking the add button
//...with an filled intent and an EDIT_REQUEST by clicking an item
//before the onActivityResult block (below this block) gets executed the layout changes to AddEditFoodActivity to process user input
//-----------------------------------------------------------------------------------------------------
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditFoodActivity.class);
                startActivityForResult(intent, ADD_FOOD_REQUEST);
            }
        });

        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Food food) {
                Intent intent = new Intent(getActivity(), AddEditFoodActivity.class);
                intent.putExtra(AddEditFoodActivity.EXTRA_ID, food.getId());
                intent.putExtra(AddEditFoodActivity.EXTRA_NAME, food.getName());
                intent.putExtra(AddEditFoodActivity.EXTRA_BRAND, food.getBrand());
                intent.putExtra(AddEditFoodActivity.EXTRA_CONTENTS, food.getContents());
                intent.putExtra(AddEditFoodActivity.EXTRA_UNIT, food.getUnit());
                intent.putExtra(AddEditFoodActivity.EXTRA_QUANTITY, food.getQuantity());
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
                startActivityForResult(intent, EDIT_FOOD_REQUEST);
            }
        });
        return root;
    }
    //processes intent
//creates new Food entry || checks if error || updates existing Food entry || passes if back gets clicked
//-----------------------------------------------------------------------------------------------------
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_FOOD_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditFoodActivity.EXTRA_NAME);
            String brand = data.getStringExtra(AddEditFoodActivity.EXTRA_BRAND);
            String contents = data.getStringExtra(AddEditFoodActivity.EXTRA_CONTENTS);
            String unit = data.getStringExtra(AddEditFoodActivity.EXTRA_UNIT);
            float quantity = data.getFloatExtra(AddEditFoodActivity.EXTRA_CONTENTS, 1.0f);
            String kcal = data.getStringExtra(AddEditFoodActivity.EXTRA_KCAL);
            String protein = data.getStringExtra(AddEditFoodActivity.EXTRA_PROTEIN);
            String carb = data.getStringExtra(AddEditFoodActivity.EXTRA_CARB);
            String fat = data.getStringExtra(AddEditFoodActivity.EXTRA_FAT);
            String sugar = data.getStringExtra(AddEditFoodActivity.EXTRA_SUGAR);
            String saturated = data.getStringExtra(AddEditFoodActivity.EXTRA_SATURATED);
            String storage = data.getStringExtra(AddEditFoodActivity.EXTRA_STORAGE);
            boolean vegetarian = data.getBooleanExtra(AddEditFoodActivity.EXTRA_VEGETARIAN, false);
            boolean vegan = data.getBooleanExtra(AddEditFoodActivity.EXTRA_VEGAN, false);
            boolean glutenfree = data.getBooleanExtra(AddEditFoodActivity.EXTRA_GLUTENFREE, false);
            boolean laktofree = data.getBooleanExtra(AddEditFoodActivity.EXTRA_LAKTOFREE, false);

            Food food = new Food(name, brand, contents, unit, quantity, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
            foodViewModel.insert(food);
            Toast.makeText(getActivity(), "hinzugefügt", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_FOOD_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditFoodActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(getActivity(), "fehlgeschlagen", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddEditFoodActivity.EXTRA_NAME);
            String brand = data.getStringExtra(AddEditFoodActivity.EXTRA_BRAND);
            String contents = data.getStringExtra(AddEditFoodActivity.EXTRA_CONTENTS);
            String unit = data.getStringExtra(AddEditFoodActivity.EXTRA_UNIT);
            float quantity = data.getFloatExtra(AddEditFoodActivity.EXTRA_CONTENTS, 1.0f);
            String kcal = data.getStringExtra(AddEditFoodActivity.EXTRA_KCAL);
            String protein = data.getStringExtra(AddEditFoodActivity.EXTRA_PROTEIN);
            String carb = data.getStringExtra(AddEditFoodActivity.EXTRA_CARB);
            String fat = data.getStringExtra(AddEditFoodActivity.EXTRA_FAT);
            String sugar = data.getStringExtra(AddEditFoodActivity.EXTRA_SUGAR);
            String saturated = data.getStringExtra(AddEditFoodActivity.EXTRA_SATURATED);
            String storage = data.getStringExtra(AddEditFoodActivity.EXTRA_STORAGE);
            boolean vegetarian = data.getBooleanExtra(AddEditFoodActivity.EXTRA_VEGETARIAN, false);
            boolean vegan = data.getBooleanExtra(AddEditFoodActivity.EXTRA_VEGAN, false);
            boolean glutenfree = data.getBooleanExtra(AddEditFoodActivity.EXTRA_GLUTENFREE, false);
            boolean laktofree = data.getBooleanExtra(AddEditFoodActivity.EXTRA_LAKTOFREE, false);

            Food food = new Food(name, brand, contents, unit, quantity, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
            food.setId(id);
            foodViewModel.update(food);
            Toast.makeText(getActivity(), "bearbeitet", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getActivity(), "nichts hinzugefügt", Toast.LENGTH_SHORT).show();
        }
    }

    //inflates the menu (3 dots in the top right hand corner) with option "Löschen" to delete all foods
//-----------------------------------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_foods:
                foodViewModel.deleteAllFoods();
                Toast.makeText(getActivity(), "gelöscht", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_food, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchItem = menu.findItem(R.id.search_foods);
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
                    foodViewModel.getFilteredFoods(newText).observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
                        @Override
                        public void onChanged(@Nullable List<Food> foods) {
                            adapter.submitList(foods);
                        }
                    });
                return false;

                } else {
                    foodViewModel.getAllFoods().observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
                        @Override
                        public void onChanged(@Nullable List<Food> foods) {
                            adapter.submitList(foods);
                        }
                    });
                }
                return false;
            }
    });
    }
}
