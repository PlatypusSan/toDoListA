package com.platy.todolist.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.platy.todolist.R;
import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.Event;
import com.platy.todolist.ui.main.adapters.EventAdapter;

import java.util.Date;
import java.util.List;


public class EventListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static final String ARG_DATE = "date";
    private static Context context;

    public EventListFragment() {}

    public static EventListFragment newInstance(String date, Context c) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, date);
        fragment.setArguments(args);
        context = c;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
    public void updateDetail() {
        mListener.onFragmentInteraction(getArguments().getString(ARG_DATE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        EntityService entityService = EntityService.getInstance();
        List<Event> eventList = entityService.getEventsByDate(getArguments().getString(ARG_DATE));

        ListView eventListView;
        eventListView = (ListView) view.findViewById(R.id.eventList);
        eventListView.setAdapter(new EventAdapter(context, R.layout.event_list_element, eventList));

        FloatingActionButton button = (FloatingActionButton)view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });


        /*eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                EntityService service = EntityService.getInstance();

                Snackbar.make(container, service.getEventList().get(0).toString(), 10000)
                        .setAction("Action", null).show();
            }
        });*/

        return view;
    }
}