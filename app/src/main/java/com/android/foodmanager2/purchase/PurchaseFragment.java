package com.android.foodmanager2.purchase;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.foodmanager2.R;
import com.android.foodmanager2.food.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PurchaseFragment extends Fragment {
    private DatePickerDialog.OnDateSetListener setListener;
    private PurchaseViewModel purchaseViewModel;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    String currentDate = simpleDateFormat.format(calendar.getTime());
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH); // month between 0 and 11 --> increase by one later and concatenate "0" + month if not divisible by 10
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_purchase, container, false);
        FloatingActionButton add_button = (FloatingActionButton) root.findViewById(R.id.add_button);
        ImageButton button_right = (ImageButton) root.findViewById(R.id.keyboard_arrow_right);
        ImageButton button_left = (ImageButton) root.findViewById(R.id.keyboard_arrow_left);
        TextView date = root.findViewById(R.id.text_purchase_date);
        purchaseViewModel =
                new ViewModelProvider(this).get(PurchaseViewModel.class);
        purchaseViewModel.setDate(currentDate).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                date.setText(s);
            }
        });
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                currentDate = simpleDateFormat.format(calendar.getTime());
                purchaseViewModel.setDate(currentDate);
                //if (simpleDateFormat.format(calendar.getTime()).equals(today)) {date.setText("heute");}
                //else {date.setText(simpleDateFormat.format(calendar.getTime()));}
            }
        });
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                currentDate = simpleDateFormat.format(calendar.getTime());
                purchaseViewModel.setDate(currentDate);
                //if (simpleDateFormat.format(calendar.getTime()).equals(today)) {date.setText("heute");}
                //else {date.setText(simpleDateFormat.format(calendar.getTime()));}
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add_purchase_activity
            }
        });
        return root;
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
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_purchase:
                break;
            case R.id.calendar_month:
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String s_day;
                        String s_month;

                        if (day < 10) {
                            s_day = "0" + day;
                        } else {s_day = String.valueOf(day);}

                        if ((month / 10) == 0) {
                        s_month = "0" + month;
                        } else {s_month = String.valueOf(month);}

                        currentDate = s_day + "." + s_month + "." + year;
                        purchaseViewModel.setDate(currentDate);

                        try {
                            calendar.setTime(simpleDateFormat.parse(currentDate));
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                    }, year, month, day);
                datePickerDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
