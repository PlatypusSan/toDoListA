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

    private int mode = 0;
    private Bundle arguments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        setTitle("Add Event");
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePickerFrom);
        timePicker.setIs24HourView(true);
        TimePicker timePicker2 = (TimePicker) findViewById(R.id.timePickerTo);
        timePicker2.setIs24HourView(true);
        TextView date = (TextView) findViewById(R.id.date);
        arguments = getIntent().getExtras();
        date.setText(arguments.getString("date"));
        mode = arguments.getInt("mode");

        if (mode == 1) {
            EditText name = (EditText) findViewById(R.id.add_event_name);
            name.setText(arguments.getString("name"));

            EditText description = (EditText) findViewById(R.id.add_event_description);
            description.setText(arguments.getString("description"));

            TimePicker timeFrom = (TimePicker) findViewById(R.id.timePickerFrom);
            timeFrom.setHour(arguments.getInt("fromHour"));
            timeFrom.setMinute(arguments.getInt("fromMinute"));

            TimePicker timeTo = (TimePicker) findViewById(R.id.timePickerTo);

            int q = arguments.getInt("toHour");
            int p = arguments.getInt("toMinute");
            timeTo.setHour(q);
            timeTo.setMinute(p);



        }

    }


    public void onAddClick(View v) {

        EntityService entityService = EntityService.getInstance();

        Date todayDate = new Date();
        String today = (todayDate.getYear() + 1900) + "-" + todayDate.getMonth() + "-" + todayDate.getDate();

        String name = ((EditText) findViewById(R.id.add_event_name)).getText().toString();
        String description = ((EditText) findViewById(R.id.add_event_description)).getText().toString();

        TimePicker timeFrom = (TimePicker) findViewById(R.id.timePickerFrom);
        TimePicker timeTo = (TimePicker) findViewById(R.id.timePickerTo);
        System.out.println(timeFrom.getHour());

        int duration = (timeTo.getHour() - timeFrom.getHour()) * 60 + (timeTo.getMinute() - timeFrom.getMinute());

        try {
            Event event = new Event(duration, name, description,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(
                            arguments.getString("date") + " " + timeFrom.getHour() + ":" +
                                    timeFrom.getMinute()), null);
            if (mode == 0) {
                entityService.addEvent(event);
            } else {
                entityService.putEvent(arguments.getLong("id"), event);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}