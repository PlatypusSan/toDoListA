package com.platy.todolist.ui.main.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.platy.todolist.R;
import com.platy.todolist.entities.Event;

import java.util.Date;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    private LayoutInflater inflater;
    private int layout;
    private List<Event> events;


    public EventAdapter(Context context, int resource, List<Event> states) {
        super(context, resource, states);
        this.events = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView dateView = (TextView) view.findViewById(R.id.event_date);
        TextView nameView = (TextView) view.findViewById(R.id.event_name);
        TextView descriptionView = (TextView) view.findViewById(R.id.event_description);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.event_element);

        Event event = events.get(position);

        if (position % 2 == 1) {
            linearLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
        }

        Date toDate = new Date(event.getDate().toString());
        toDate.setMinutes(toDate.getMinutes() + event.getDuration());


        dateView.setText(event.getDate().getHours() + ":" + (event.getDate().getMinutes() < 10
                ? "0" + event.getDate().getMinutes() : event.getDate().getMinutes()) + "\n" +
                toDate.getHours() + ":" + (toDate.getMinutes() < 10 ? "0" + toDate.getMinutes() :
                toDate.getMinutes()));
        nameView.setText(event.getName());
        descriptionView.setText(event.getDescription());

        return view;
    }
}
