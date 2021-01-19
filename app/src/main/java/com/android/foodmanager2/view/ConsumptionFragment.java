package com.android.foodmanager2.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import com.android.foodmanager2.viewmodel.ConsumptionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsumptionFragment extends Fragment {
    public static final int ADD_CONSUMPTION_REQUEST = 1;
    public static final int EDIT_CONSUMPTION_REQUEST = 2;
    private static final int RESULT_OK = -1;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    String currentDate = simpleDateFormat.format(calendar.getTime());
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH); // month between 0 and 11 --> increase by one later and concatenate "0" + month if not divisible by 10
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    private ConsumptionViewModel consumptionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_consumption, container, false);
        FloatingActionButton add_button = (FloatingActionButton) root.findViewById(R.id.add_button);
        ImageButton button_right = (ImageButton) root.findViewById(R.id.keyboard_arrow_right);
        ImageButton button_left = (ImageButton) root.findViewById(R.id.keyboard_arrow_left);
        TextView date = root.findViewById(R.id.text_consumption_date);
        consumptionViewModel =
                new ViewModelProvider(this).get(ConsumptionViewModel.class);
        consumptionViewModel.setDate(currentDate).observe(getViewLifecycleOwner(), new Observer<String>() {
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
                consumptionViewModel.setDate(currentDate);
            }
        });
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                currentDate = simpleDateFormat.format(calendar.getTime());
                consumptionViewModel.setDate(currentDate);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
        inflater.inflate(R.menu.main_consumption, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchButton = menu.findItem(R.id.search_consumption);
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
                        consumptionViewModel.setDate(currentDate);

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
