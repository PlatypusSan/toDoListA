package com.platy.todolist.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.platy.todolist.AddEventActivity;
import com.platy.todolist.Datable;
import com.platy.todolist.R;
import com.platy.todolist.entities.EntityService;
import com.platy.todolist.entities.Event;
import com.platy.todolist.ui.main.adapters.EventAdapter;

import java.util.List;


public class EventListFragment extends Fragment implements Datable {

    private OnFragmentInteractionListener mListener;
    private static final String ARG_DATE = "date";
    private static Context context;
    private EventAdapter eventAdapter;
    private EntityService entityService;
    private EventListFragment thisFragment;
    private Event putEvent;


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

        void onFragmentAdd(String link);
        void onFragmentPut(String link, Event event);
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
    public void addEventActivityStart() {
        mListener.onFragmentAdd(getArguments().getString(ARG_DATE));
    }
    public void putEventActivityStart() {
        mListener.onFragmentPut(getArguments().getString(ARG_DATE), putEvent);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        thisFragment = this;
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        entityService = EntityService.getInstance();
        List<Event> eventList = entityService.getEventsByDate(getArguments().getString(ARG_DATE));

        ListView eventListView;
        eventListView = (ListView) view.findViewById(R.id.eventList);
        eventAdapter = new EventAdapter(context, R.layout.event_list_element, eventList);
        eventListView.setAdapter(eventAdapter);
        eventListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id)
            {

                Event event = eventAdapter.getItem(position);
                EventDialogFragment dialog = new EventDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", event.getName());
                bundle.putLong("id", event.getId());

                dialog.setArguments(bundle);
                dialog.setFragment(thisFragment);
                dialog.setContainer(container);
                dialog.setInflater(inflater);
                dialog.setContext(context);
                dialog.show(getFragmentManager(), "custom");
                return false;
            }
        });

        FloatingActionButton button = (FloatingActionButton)view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEventActivityStart();
            }
        });

        return view;
    }

    @Override
    public void remove(long id) {
        eventAdapter.remove(entityService.getEvent(id));
        entityService.deleteEvent(id);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(thisFragment);
        ft.attach(thisFragment);
        ft.commit();
    }

    @Override
    public void edit(long id) {
        Event event = entityService.getEvent(id);
        eventAdapter.remove(event);
        putEvent = event;
        putEventActivityStart();


        /*final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(thisFragment);
        ft.attach(thisFragment);
        ft.commit();*/
    }
}