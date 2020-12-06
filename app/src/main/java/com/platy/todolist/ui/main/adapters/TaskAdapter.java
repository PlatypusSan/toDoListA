package com.platy.todolist.ui.main.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.platy.todolist.R;
import com.platy.todolist.entities.Event;
import com.platy.todolist.entities.Task;

import java.util.Date;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private LayoutInflater inflater;
    private int layout;
    private List<Task> taskList;

    public TaskAdapter(@NonNull Context context, int resource, List<Task> taskList) {
        super(context, resource, taskList);
        this.taskList = taskList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.task_name);
        TextView descriptionView = (TextView) view.findViewById(R.id.task_description);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.task_element);

        Task task = taskList.get(position);

        linearLayout.setBackgroundColor(Color.parseColor(task.getColour()));


        nameView.setText(task.getName());
        descriptionView.setText(task.getDescription());

        return view;
    }
}
