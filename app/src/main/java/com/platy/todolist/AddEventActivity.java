package com.platy.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.Event;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddEventActivity extends AppCompatActivity {

    Bundle arguments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        setTitle("Add Event");
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePickerFrom);
        timePicker.setIs24HourView(true);
        timePicker = (TimePicker) findViewById(R.id.timePickerTo);
        timePicker.setIs24HourView(true);
        TextView date = (TextView) findViewById(R.id.date);
        arguments = getIntent().getExtras();
        date.setText(arguments.getString("date"));

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onAddClick(View v) {

        EntityService entityService = EntityService.getInstance();

        Date todayDate = new Date();
        String today = (todayDate.getYear() + 1900) + "-" + todayDate.getMonth() + "-" + todayDate.getDate();

        String name = ((EditText) findViewById(R.id.add_event_name)).getText().toString();
        String description = ((EditText) findViewById(R.id.add_event_description)).getText().toString();

        TimePicker timeFrom = (TimePicker) findViewById(R.id.timePickerFrom);
        TimePicker timeTo = (TimePicker) findViewById(R.id.timePickerTo);

        int duration = (timeTo.getHour() - timeFrom.getHour()) * 60 + (timeTo.getMinute() - timeFrom.getMinute());
        try {
            entityService.addEvent(new Event(duration, name, description,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm z").parse(
                            arguments.getString("date") + " " + timeFrom.getHour() + ":" +
                                    timeFrom.getMinute() + " GMT+00:00"), null));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}