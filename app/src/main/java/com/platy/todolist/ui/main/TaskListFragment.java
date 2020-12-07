package com.platy.todolist.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.platy.todolist.AddEventActivity;
import com.platy.todolist.AddTaskActivity;
import com.platy.todolist.R;
import com.platy.todolist.SubTaskActivity;
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
    private TaskListFragment thisFragment;

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
        thisFragment = this;
        entityService = EntityService.getInstance();
        final List<Task> taskList = entityService.getTasks();

        ListView taskListView = (ListView) view.findViewById(R.id.taskListView);

        taskAdapter = new TaskAdapter(context, R.layout.task_list_element, taskList);
        taskListView.setAdapter(taskAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, SubTaskActivity.class);
                intent.putExtra("name", taskList.get(i).getName());
                startActivity(intent);

            }
        });

        Button addButton = (Button) view.findViewById(R.id.add_task_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
    private void refresh() {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(thisFragment);
        ft.attach(thisFragment);
        ft.commit();
    }
}