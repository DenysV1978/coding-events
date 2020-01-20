package org.launchcode.codingevents.models;

public enum EventType {

    CONFERENCE("Conference"), //so, here, when we type like this we call for Constructor below automatically
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social");

    private final String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
