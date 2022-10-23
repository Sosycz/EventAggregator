package com.example.eventapp.dto;

import java.util.Comparator;

public class EventComparator implements Comparator<EventDto> {
    @Override
    public int compare(EventDto o1, EventDto o2) {
        return o1.getStartingTime().compareTo(o2.getStartingTime());
    }
}
