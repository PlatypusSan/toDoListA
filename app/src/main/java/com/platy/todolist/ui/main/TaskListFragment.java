package com.platy.todolist.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.platy.todolist.R;
import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.Task;
import com.platy.todolist.ui.main.adapters.EventAdapter;
import com.platy.todolist.ui.main.adapters.TaskAdapter;

import java.util.List;
import java.util.zip.Inflater;


public class TaskListFragment extends Fragment {


    private TaskAdapter taskAdapter;
    private EntityService entityService;
    private static Context context;

    public TaskListFragment() {

    }

    public static TaskListFragment newInstance(Context c) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        context = c;

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        entityService = EntityService.getInstance();
        List<Task> taskList = entityService.getTasks();

        ListView taskListView = (ListView) view.findViewById(R.id.taskListView);

        taskAdapter = new TaskAdapter(context, R.layout.task_list_element, taskList);
        taskListView.setAdapter(taskAdapter);

        return view;

    }
}