package com.platy.todolist.ui.main.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.platy.todolist.R;
import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.Event;
import com.platy.todolist.entities.SubTask;
import com.platy.todolist.ui.main.EventListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class EventAdapter extends ArrayAdapter<Event> {

    private LayoutInflater inflater;
    private int layout;
    private List<Event> events;
    private Context context;
    private List<SubTask> subTaskList;
    private EntityService entityService;
    public static EventListFragment eventListFragment;
    public static FragmentTransaction ft;


    public EventAdapter(Context context, int resource, List<Event> states) {
        super(context, resource, states);
        this.context = context;
        this.events = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);
        entityService = EntityService.getInstance();

        TextView dateView = (TextView) view.findViewById(R.id.event_date);
        TextView nameView = (TextView) view.findViewById(R.id.event_name);
        TextView descriptionView = (TextView) view.findViewById(R.id.event_description);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.event_element);
        LinearLayout taskSpinner = (LinearLayout) view.findViewById(R.id.task_spinner);

        final Event event = events.get(position);
        if (event.getTask() != null) {
            //final Event eventToUpdate = entityService.getEvent(event.getId());
            Drawable border = ContextCompat.getDrawable(view.getContext(), R.drawable.customborder);
            border.setTint(Color.parseColor(event.getTask().getColour()));
            taskSpinner.setBackground(border);
            TextView spinnerText = (TextView) view.findViewById(R.id.task_name_spinner);
            spinnerText.setText(event.getTask().getName());

            subTaskList = event.getTask().getSubTasks();
            List<String> nameList = new ArrayList<>();
            for (SubTask s: subTaskList) {
                if (!s.isCompleted()){
                    nameList.add(s.getName());
                }
            }
            if (nameList.size() == 0) {
                nameList.add("All is done!");
            }

            final String[] nameArray = new String[nameList.size()];
            nameList.toArray(nameArray);


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, nameArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

            spinner.setAdapter(adapter);

            AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    System.out.println(parent.getItemAtPosition(position));
                    for (int i = 0; i < subTaskList.size(); i++) {
                        if (subTaskList.get(i).getName().equals(parent.getItemAtPosition(position))) {
                            subTaskList.get(i).setCompleted(true);
                            entityService.putSubTask(event.getTask().getName(), subTaskList.get(i));
                            //ft = getFragmentManager().beginTransaction();
                            ft.detach(eventListFragment);
                            ft.attach(eventListFragment);
                            ft.commit();
                        }
                    }
                    System.out.println(subTaskList.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            };
            spinner.setOnItemSelectedListener(itemSelectedListener);
        } else {
            LinearLayout eventInfo = (LinearLayout) view.findViewById(R.id.even_info);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 0);
            eventInfo.setLayoutParams(params);
            taskSpinner.setVisibility(View.GONE);
        }


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
