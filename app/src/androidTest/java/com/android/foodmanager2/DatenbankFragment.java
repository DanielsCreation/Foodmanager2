/***
package com.android.foodmanager2.ui.datenbank;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodmanager2.main.MainActivity;
import com.android.foodmanager2.R;
import com.android.foodmanager2.ui.datenbank.DatenbankViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DatenbankFragment extends Fragment {

    private DatenbankViewModel datenbankViewModel;

    CustomAdapter customAdapter;
    MyDatabaseHelper myDB;
    ArrayList<String> food_id, food_name, food_brand, food_unit, food_quantity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        datenbankViewModel =
                new ViewModelProvider(this).get(DatenbankViewModel.class);
        View root = inflater.inflate(R.layout.fragment_datenbank, container, false);
        final TextView textView = root.findViewById(R.id.text_datenbank);
        FloatingActionButton add_button = (FloatingActionButton)root.findViewById(R.id.add_button);
        RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);
        datenbankViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

}




public class DatenbankFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    CustomAdapter customAdapter;

    MyDatabaseHelper myDB;
    ArrayList<String> food_id, food_name, food_brand, food_unit, food_quantity;

    public DatenbankFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_datenbank, container, false);
        add_button = (FloatingActionButton)view.findViewById(R.id.add_button);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(getActivity(), food_id, food_name, food_brand, food_unit, food_quantity);
        recyclerView.setAdapter(customAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        add_button.setOnClickListener(this);

        myDB = new MyDatabaseHelper(getActivity());
        food_id = new ArrayList<>();
        food_name = new ArrayList<>();
        food_brand = new ArrayList<>();
        food_unit = new ArrayList<>();
        food_quantity = new ArrayList<>();

        storeDataInArrays();

        return view;
        }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), AddFoodActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "Keine Einträge vorhanden.", Toast.LENGTH_SHORT).show();
        }else {

            while (cursor.moveToNext()) {
                food_id.add(cursor.getString(0));
                food_name.add(cursor.getString(1));
                food_brand.add(cursor.getString(2));
                food_unit.add(cursor.getString(3));
                food_quantity.add(cursor.getString(4));
            }
        }
    }
}
*/

