package org.launchcode.codingevents.data;

// this class will be responsible for storing event objects... will contain static methods and properties. All static because we do not want to create instances of this class


import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {

    // need a place to put events
    private static final Map<Integer, Event> events = new HashMap<>(); //this is the place to keep events. // also we input final which
    //adds additional safety. He said that this property will not be able to be changed after its initiated

    // should be able to get all events
    public static Collection<Event> getAll() {
        return events.values(); // will return list of all events available in Map
    }

    //should be able to get a single event

    public static Event getById(int id) {
        return events.get(id); // will return event object searched by id
    }

    //add an event in collection
    public static void add(Event event) { //so, here we add event type Event into HasMap using key as iD of that event and value "event"

        events.put(event.getId(), event); //key = event.getId();   value = event. So, it means put object event in HashMap and give key that equals ID shown in that object
    }

    // remove an event

    public static void remove(int id) {
        events.remove(id);
    }



}
