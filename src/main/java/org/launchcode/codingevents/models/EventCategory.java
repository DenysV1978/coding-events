package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity {


    @OneToMany(mappedBy = "eventCategory") //this is persistence annotation...so, here we say that one category can have many Events and they should be connected (mapped) through eventCategory field that is located in event
    private final List<Event> events = new ArrayList<>();

    //the main idea to create this opposite way relationship was to be able to group Events by Categories. So, now EventCategories know about Events and those Events are stored depends on their categories in Lists


    @NotBlank(message = "cannot be blank")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
