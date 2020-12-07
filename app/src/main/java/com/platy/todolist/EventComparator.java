package com.platy.todolist;

import com.platy.todolist.entities.Event;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

    @Override
    public int compare(Event event1, Event event2) {
        return event1.getDate().compareTo(event2.getDate());
    }
}
