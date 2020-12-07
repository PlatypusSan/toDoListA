package com.platy.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.SubTask;
import com.platy.todolist.entities.Task;
import com.platy.todolist.ui.main.adapters.SubTaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    private static String[] colors = {"#33ee55", "#ee9944", "#34ebde" , "#888888",
            "#fcba03", "#ff5c6c", "#c972f7", "#7284f7", "#d7fc72"};
    private static int selection = 3;

    private EntityService entityService = EntityService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setTitle("Add Task");

        final EditText editTextName = (EditText) findViewById(R.id.add_task_name);
        final EditText editTextDes = (EditText) findViewById(R.id.add_task_description);
        ListView subtaskListview = (ListView) findViewById(R.id.subtask_listview_to_add);
        Button addButton = (Button) findViewById(R.id.add_subtask_button_to_add);
        final EditText editText = (EditText) findViewById(R.id.add_subtask_edit_to_add);

        final List<SubTask> subTaskList = new ArrayList<>();
        final List<SubTask> subTaskToSave = new ArrayList<>();

        final SubTaskAdapter adapter = new SubTaskAdapter(this,
                R.layout.subtask_element, subTaskList);
        subtaskListview.setAdapter(adapter);


        Button button = (Button) findViewById(R.id.commit_task);
        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextName.getText().toString().length() <= 1) {
                    return;
                }
                Task task = new Task(
                        editTextName.getText().toString(),
                        editTextDes.getText().toString(),
                        colors[selection],
                        subTaskToSave,
                        false
                );
                entityService.addTask(task);

                if(colors.length - 1 <= selection) {
                    selection = 0;
                }
                selection++;
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubTask subTask = new SubTask(editText.getText().toString(), "", false);
                subTaskToSave.add(subTask);
                adapter.add(subTask);
                editText.setText("");

            }
        });
    }
}