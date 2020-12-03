package com.platy.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

    public void onFragmentInteraction(String link) {
        /*Snackbar.make(findViewById(android.R.id.content).getRootView(), link, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/
        Intent intent = new Intent(this, AddEventActivity.class);

        intent.putExtra("date", link);
        startActivity(intent);
    }
}