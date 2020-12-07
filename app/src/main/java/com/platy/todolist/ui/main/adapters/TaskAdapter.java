package com.platy.todolist.ui.main.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.platy.todolist.R;
import com.platy.todolist.entities.Event;
import com.platy.todolist.entities.SubTask;
import com.platy.todolist.entities.Task;

import java.util.Date;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private LayoutInflater inflater;
    private int layout;
    private List<Task> taskList;
    private Context context;

    public TaskAdapter(@NonNull Context context, int resource, List<Task> taskList) {
        super(context, resource, taskList);
        this.taskList = taskList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.task_name);
        TextView descriptionView = (TextView) view.findViewById(R.id.task_description);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.task_element);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        Task task = taskList.get(position);

        Drawable border = ContextCompat.getDrawable(view.getContext(), R.drawable.customborder_task);
        border.setTint(Color.parseColor(task.getColour()));
        linearLayout.setBackground(border);

        nameView.setText(task.getName());
        descriptionView.setText(task.getDescription());

        float progress = 0;
        if(task.getSubTasks().size() != 0) {

            for (SubTask subTask : task.getSubTasks()){
                if(subTask.isCompleted()) progress++;
            }
            progress /= task.getSubTasks().size();
        }

        progress *= 100;
        progressBar.setProgress((int)progress);
        return view;
    }
}
