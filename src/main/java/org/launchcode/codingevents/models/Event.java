package org.launchcode.codingevents.models;

import java.util.Objects;

public class Event {

    private int id;
    private static int nextId = 1; //this ID is static; so, it is shared by all instances of the class!


    private String name;

    private String description;



//    public Event(String name) {
//        this.name = name;
//    } now we delete this constructor because we do not need to have it. however, maybe we want to use it... however,
    // using overwriting method Java will use only that constructor that has enough parameters

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() { //we are not going to create setter for id because we do not want anybody give a chance to change id
        return id;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
