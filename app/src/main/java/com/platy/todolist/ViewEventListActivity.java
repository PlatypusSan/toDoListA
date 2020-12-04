package com.platy.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.platy.todolist.entities.Event;
import com.platy.todolist.ui.main.EventListFragment;

public class ViewEventListActivity extends AppCompatActivity implements EventListFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_list);
        Bundle arguments = getIntent().getExtras();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.one_event_list, EventListFragment.newInstance(arguments.getString("date"), this))
                    .commit();
        }
    }

    public void onFragmentAdd(String link) {

        Intent intent = new Intent(this, AddEventActivity.class);

        intent.putExtra("date", link);
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

        startActivity(intent);
    }
}