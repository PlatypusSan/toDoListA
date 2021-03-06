package com.platy.todolist.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.platy.todolist.Datable;
import com.platy.todolist.MainActivity;
import com.platy.todolist.R;

import java.util.zip.Inflater;

public class EventDialogFragment extends DialogFragment {

    private EventListFragment fragment;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Context context;


    public void setContext(Context context) {
        this.context = context;
    }

    public void setFragment(EventListFragment fragment) {
        this.fragment = fragment;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setContainer(ViewGroup container) {
        this.container = container;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String[] menu = {"Edit", "Delete"};

        final ArrayAdapter<String> adapter = new ArrayAdapter(context,
                android.R.layout.simple_list_item_1, menu);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme);
        return builder
                .setTitle(getArguments().getString("name"))
                .setIcon(R.drawable.icon)
                .setView(R.layout.dialog)
                .setAdapter(adapter, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i) {
                            case 0:{
                                fragment.edit(getArguments().getLong("id"));
                                break;
                            }
                            case 1:{
                                fragment.remove(getArguments().getLong("id"));
                                break;
                            }
                        }
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();


    }
}