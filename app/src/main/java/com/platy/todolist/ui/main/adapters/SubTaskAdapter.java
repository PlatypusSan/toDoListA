package com.platy.todolist.ui.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.platy.todolist.R;
import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.SubTask;

import java.util.List;

public class SubTaskAdapter extends ArrayAdapter<SubTask> {

    private LayoutInflater inflater;
    private int layout;
    private List<SubTask> subTaskList;
    private String taskName;

    public SubTaskAdapter(@NonNull Context context, int resource, List<SubTask> subTaskList, String taskName) {
        super(context, resource, subTaskList);
        this.subTaskList = subTaskList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.taskName = taskName;
    }
    public SubTaskAdapter(@NonNull Context context, int resource, List<SubTask> subTaskList) {
        super(context, resource, subTaskList);
        this.subTaskList = subTaskList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.subtask_name);
        final CheckBox checkbox = (CheckBox) view.findViewById(R.id.subtask_checkbox);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.subtask_element);

        final SubTask subTask = subTaskList.get(position);

        //linearLayout.setBackgroundColor(Color.parseColor(task.getColour()));


        nameView.setText(subTask.getName());
        checkbox.setChecked(subTask.isCompleted());

        final EntityService entityService = EntityService.getInstance();
        checkbox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (checkbox.isChecked()) {
                    subTask.setCompleted(true);
                    entityService.putSubTask(taskName, subTask);
                } else {
                    subTask.setCompleted(false);
                    entityService.putSubTask(taskName, subTask);
                }
            }
        });

        return view;
    }
}

