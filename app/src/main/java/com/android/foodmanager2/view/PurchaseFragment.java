package com.android.foodmanager2.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodmanager2.R;
import com.android.foodmanager2.adapter.PurchaseAdapter;
import com.android.foodmanager2.model.Food;
import com.android.foodmanager2.model.Purchase;
import com.android.foodmanager2.util.MyCalendar;
import com.android.foodmanager2.viewmodel.PurchaseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PurchaseFragment extends Fragment {
    public static final int ADD_PURCHASE_REQUEST = 1;
    public static final int EDIT_PURCHASE_REQUEST = 2;
    private static final int RESULT_OK = -1;

    private PurchaseViewModel purchaseViewModel;
    PurchaseAdapter adapter = new PurchaseAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_purchase, container, false);
        FloatingActionButton add_button = (FloatingActionButton) root.findViewById(R.id.add_button);
        ImageButton button_right = (ImageButton) root.findViewById(R.id.keyboard_arrow_right);
        ImageButton button_left = (ImageButton) root.findViewById(R.id.keyboard_arrow_left);
        TextView date = root.findViewById(R.id.text_purchase_date);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        purchaseViewModel =
                new ViewModelProvider(this).get(PurchaseViewModel.class);
        purchaseViewModel.setDate(MyCalendar.getCurrentDate()).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                date.setText(s);
            }
        });
        purchaseViewModel.getPurchasesByDate(MyCalendar.getCurrentDate()).observe(getViewLifecycleOwner(), new Observer<List<Purchase>>() {
            @Override
            public void onChanged(@Nullable List<Purchase> purchases) {
                adapter.submitList(purchases);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                purchaseViewModel.delete(adapter.getPurchaseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Einkauf gelöscht", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCalendar.calendar.add(Calendar.DAY_OF_MONTH, 1);
                MyCalendar.setCurrentDate(MyCalendar.simpleDateFormat.format(MyCalendar.calendar.getTime()));
                purchaseViewModel.setDate(MyCalendar.getCurrentDate());
                purchaseViewModel.getPurchasesByDate(MyCalendar.getCurrentDate()).observe(getViewLifecycleOwner(), new Observer<List<Purchase>>() {
                    @Override
                    public void onChanged(@Nullable List<Purchase> purchases) {
                        adapter.submitList(purchases);
                    }
                });
            }
        });
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCalendar.calendar.add(Calendar.DAY_OF_MONTH, -1);
                MyCalendar.setCurrentDate(MyCalendar.simpleDateFormat.format(MyCalendar.calendar.getTime()));
                purchaseViewModel.setDate(MyCalendar.getCurrentDate());
                purchaseViewModel.getPurchasesByDate(MyCalendar.getCurrentDate()).observe(getViewLifecycleOwner(), new Observer<List<Purchase>>() {
                    @Override
                    public void onChanged(@Nullable List<Purchase> purchases) {
                        adapter.submitList(purchases);
                    }
                });
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPurchaseActivity.class);
                startActivityForResult(intent, ADD_PURCHASE_REQUEST);
            }
        });
        adapter.setOnItemClickListener(new PurchaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Purchase purchase) {
                Food food = purchase.getFood();
                Intent intent = new Intent(getActivity(), EditPurchaseActivity.class);

                intent.putExtra(AddPurchaseActivity.EXTRA_PURCHASEID, purchase.getPurchaseId());
                intent.putExtra(AddPurchaseActivity.EXTRA_QUANTITY, purchase.getQuantity());
                intent.putExtra(AddPurchaseActivity.EXTRA_BBD, purchase.getBbd());
                intent.putExtra(AddPurchaseActivity.EXTRA_PURCHASEDATE, purchase.getPurchaseDate());
                intent.putExtra(AddPurchaseActivity.EXTRA_BOUGHT, purchase.getBought());

                intent.putExtra(AddEditFoodActivity.EXTRA_FOODID, food.getFoodId());
                intent.putExtra(AddEditFoodActivity.EXTRA_NAME, food.getName());
                intent.putExtra(AddEditFoodActivity.EXTRA_STORE, food.getStore());
                intent.putExtra(AddEditFoodActivity.EXTRA_PRICE, food.getPrice());
                intent.putExtra(AddEditFoodActivity.EXTRA_BRAND, food.getBrand());
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

                startActivityForResult(intent, EDIT_PURCHASE_REQUEST);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PURCHASE_REQUEST && resultCode == RESULT_OK) {

            String quantity = data.getStringExtra(AddPurchaseActivity.EXTRA_QUANTITY);
            String bbd = data.getStringExtra(AddPurchaseActivity.EXTRA_BBD);
            boolean bought = data.getBooleanExtra(AddPurchaseActivity.EXTRA_BOUGHT, false);

            int foodId = data.getIntExtra(AddEditFoodActivity.EXTRA_FOODID, -1);
            String name = data.getStringExtra(AddEditFoodActivity.EXTRA_NAME);
            String brand = data.getStringExtra(AddEditFoodActivity.EXTRA_BRAND);
            String store = data.getStringExtra(AddEditFoodActivity.EXTRA_STORE);
            String price = data.getStringExtra(AddEditFoodActivity.EXTRA_PRICE);
            String contents = data.getStringExtra(AddEditFoodActivity.EXTRA_CONTENTS);
            String unit = data.getStringExtra(AddEditFoodActivity.EXTRA_UNIT);
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
            Food food = new Food(name, brand, store, price, contents, unit, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
            food.setFoodId(foodId);

            Purchase purchase = new Purchase(quantity, bbd, MyCalendar.getCurrentDate(), bought, food);
            purchaseViewModel.insert(purchase);
        }

        if (requestCode == EDIT_PURCHASE_REQUEST && resultCode == RESULT_OK) {

            int purchaseId = data.getIntExtra(AddPurchaseActivity.EXTRA_PURCHASEID, -1);
            String quantity = data.getStringExtra(AddPurchaseActivity.EXTRA_QUANTITY);
            String bbd = data.getStringExtra(AddPurchaseActivity.EXTRA_BBD);
            String purchaseDate = data.getStringExtra(AddPurchaseActivity.EXTRA_PURCHASEDATE);
            boolean bought = data.getBooleanExtra(AddPurchaseActivity.EXTRA_BOUGHT, false);

            int foodId = data.getIntExtra(AddEditFoodActivity.EXTRA_FOODID, -1);
            String name = data.getStringExtra(AddEditFoodActivity.EXTRA_NAME);
            String brand = data.getStringExtra(AddEditFoodActivity.EXTRA_BRAND);
            String store = data.getStringExtra(AddEditFoodActivity.EXTRA_STORE);
            String price = data.getStringExtra(AddEditFoodActivity.EXTRA_PRICE);
            String contents = data.getStringExtra(AddEditFoodActivity.EXTRA_CONTENTS);
            String unit = data.getStringExtra(AddEditFoodActivity.EXTRA_UNIT);
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
            Food food = new Food(name, brand, store, price, contents, unit, kcal, protein, carb, fat, sugar, saturated, storage, vegetarian, vegan, glutenfree, laktofree);
            food.setFoodId(foodId);

            Purchase purchase = new Purchase(quantity, bbd, purchaseDate, bought, food);
            purchase.setPurchaseId(purchaseId);

            purchaseViewModel.update(purchase);
            Toast.makeText(getActivity(), name + " bearbeitet", Toast.LENGTH_SHORT);
        }
    }

    private Toast mToast;

    protected void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text + " hinzugefügt", Toast.LENGTH_SHORT);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(getActivity(), text + " hinzugefügt", Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_purchase, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchButton = menu.findItem(R.id.search_purchase);
        MenuItem calendarButton = menu.findItem(R.id.calendar_month);
        SearchView searchView = (SearchView) searchButton.getActionView();
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
                    purchaseViewModel.getPurchasesByName(newText).observe(getViewLifecycleOwner(), new Observer<List<Purchase>>() {
                        @Override
                        public void onChanged(@Nullable List<Purchase> purchases) {
                            adapter.submitList(purchases);
                        }
                    });
                    return false;

                } else {
                    purchaseViewModel.getPurchasesByDate(MyCalendar.currentDate).observe(getViewLifecycleOwner(), new Observer<List<Purchase>>() {
                        @Override
                        public void onChanged(@Nullable List<Purchase> purchases) {
                            adapter.submitList(purchases);
                        }
                    });
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_purchase:
                break;
            case R.id.calendar_month:
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), (DatePickerDialog.OnDateSetListener) (view, year, month, day) -> {
                    month = month + 1;
                    String s_day;
                    String s_month;

                    if (day < 10) {
                        s_day = "0" + day;
                    } else {
                        s_day = String.valueOf(day);
                    }

                    if ((month / 10) == 0) {
                        s_month = "0" + month;
                    } else {
                        s_month = String.valueOf(month);
                    }
                    MyCalendar.setCurrentDate(s_day + "." + s_month + "." + year);
                    purchaseViewModel.setDate(MyCalendar.getCurrentDate());
                    purchaseViewModel.getPurchasesByDate(MyCalendar.getCurrentDate()).observe(getViewLifecycleOwner(), new Observer<List<Purchase>>() {
                        @Override
                        public void onChanged(@Nullable List<Purchase> purchases) {
                            adapter.submitList(purchases);
                        }
                    });

                    try {
                        MyCalendar.calendar.setTime(MyCalendar.simpleDateFormat.parse(MyCalendar.getCurrentDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }, MyCalendar.getYear(), MyCalendar.getMonth(), MyCalendar.getDay());
                datePickerDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
