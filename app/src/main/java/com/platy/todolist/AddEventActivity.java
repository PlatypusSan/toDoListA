package com.platy.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.Event;
import com.platy.todolist.entities.SubTask;
import com.platy.todolist.entities.Task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private EntityService entityService;
    private List<String> taskNameLists;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        entityService = EntityService.getInstance();


        setTitle("Add Event");
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePickerFrom);
        timePicker.setIs24HourView(true);
        TimePicker timePicker2 = (TimePicker) findViewById(R.id.timePickerTo);
        timePicker2.setIs24HourView(true);
        //TextView date = (TextView) findViewById(R.id.date);
        arguments = getIntent().getExtras();
        //date.setText(arguments.getString("date"));
        mode = arguments.getInt("mode");

        Spinner spinner = (Spinner) findViewById(R.id.task_choise_spinner);

        taskNameLists = new ArrayList<>();
        taskList = entityService.getTasks();
        taskNameLists.add("<None>");
        for (Task t: taskList) {
            taskNameLists.add(t.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, taskNameLists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (mode == 1) {
            EditText name = (EditText) findViewById(R.id.add_event_name);
            name.setText(arguments.getString("name"));

            EditText description = (EditText) findViewById(R.id.add_event_description);
            description.setText(arguments.getString("description"));

            TimePicker timeFrom = (TimePicker) findViewById(R.id.timePickerFrom);
            timeFrom.setHour(arguments.getInt("fromHour"));
            timeFrom.setMinute(arguments.getInt("fromMinute"));
            int selection = 0;
            for (int i = 0; i < taskNameLists.size(); i++) {
                if (arguments.getString("taskName") != null && arguments.getString("taskName").equals(taskNameLists.get(i))){
                    selection = i;
                }
            }

            spinner.setSelection(selection);

            TimePicker timeTo = (TimePicker) findViewById(R.id.timePickerTo);

            int q = arguments.getInt("toHour");
            int p = arguments.getInt("toMinute");
            timeTo.setHour(q);
            timeTo.setMinute(p);



        }

    }


    public void onAddClick(View v) {


        Date todayDate = new Date();
        String today = (todayDate.getYear() + 1900) + "-" + todayDate.getMonth() + "-" + todayDate.getDate();

        String name = ((EditText) findViewById(R.id.add_event_name)).getText().toString();
        String description = ((EditText) findViewById(R.id.add_event_description)).getText().toString();

        TimePicker timeFrom = (TimePicker) findViewById(R.id.timePickerFrom);
        TimePicker timeTo = (TimePicker) findViewById(R.id.timePickerTo);

        Spinner spinner = (Spinner) findViewById(R.id.task_choise_spinner);



        int duration = (timeTo.getHour() - timeFrom.getHour()) * 60 + (timeTo.getMinute() - timeFrom.getMinute());
        Task task = null;
        if (!spinner.getSelectedItem().toString().equals("<None>")) {
            task = entityService.getTask(spinner.getSelectedItem().toString());
        }
        try {
            Event event = new Event(duration, name, description,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(
                            arguments.getString("date") + " " + timeFrom.getHour() + ":" +
                                    timeFrom.getMinute()), task);
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