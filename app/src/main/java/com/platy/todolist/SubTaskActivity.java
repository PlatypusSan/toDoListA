package com.platy.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.SubTask;
import com.platy.todolist.entities.Task;
import com.platy.todolist.ui.main.adapters.SubTaskAdapter;

public class SubTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_task);

        final EntityService entityService = EntityService.getInstance();
        final Task task = entityService.getTask(getIntent().getExtras().getString("name"));
        setTitle(task.getName());


        ListView subtaskListview = (ListView) findViewById(R.id.subtask_listview);
        Button button = (Button) findViewById(R.id.okSubtaskButton);
        Button addButton = (Button) findViewById(R.id.add_subtask_button);
        final EditText editText = (EditText) findViewById(R.id.add_subtask_edit);

        final SubTaskAdapter adapter = new SubTaskAdapter(this,
                R.layout.subtask_element, task.getSubTasks(), task.getName());
        subtaskListview.setAdapter(adapter);

        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubTask subTask = new SubTask(editText.getText().toString(), "", false);
                entityService.addSubTask(task.getName(), subTask);
                adapter.add(subTask);

            }
        });
    }
}