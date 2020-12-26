package com.platy.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.Event;
import com.platy.todolist.ui.main.EventListFragment;
import com.platy.todolist.ui.main.SectionsPagerAdapter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements EventListFragment.OnFragmentInteractionListener {

    Calendar dateAndTime = Calendar.getInstance();
    private Datable datable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        EntityService entityService = EntityService.getInstance();
        entityService.getEvents();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(1).select();


    }


    public void onCalendarButtonClick(View v) {
        new DatePickerDialog(MainActivity.this, R.style.DialogTheme, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Intent intent = new Intent(getApplicationContext(), ViewEventListActivity.class);

            intent.putExtra("date", year + "-" + monthOfYear + "-" + dayOfMonth);
            startActivity(intent);

        }
    };

    public void onFragmentAdd(String link) {

        Intent intent = new Intent(this, AddEventActivity.class);

        intent.putExtra("date", link);
        intent.putExtra("mode", 0);

        startActivity(intent);
    }

    @Override
    public void onFragmentPut(String link, Event event) {
        Intent intent = new Intent(this, AddEventActivity.class);

        intent.putExtra("date", link);
        intent.putExtra("mode", 1);
        intent.putExtra("name", event.getName());
        intent.putExtra("description", event.getDescription());

        intent.putExtra("fromHour", event.getDate().getHours());
        intent.putExtra("fromMinute", event.getDate().getMinutes());

        int q = event.getDate().getHours() + ((event.getDate().getMinutes() + event.getDuration()) / 60);
        int p = ((event.getDuration() + event.getDate().getMinutes()) % 60);
        intent.putExtra("toHour", q);
        intent.putExtra("toMinute", p);
        intent.putExtra("id", event.getId());
        if(event.getTask() != null){
            intent.putExtra("taskName", event.getTask().getName());
        }

        startActivity(intent);
    }
}