package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity //this creates table in MySQL
public class Event extends AbstractEntity {




    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;



    @NotBlank(message = "Location must be set up")
    @NotNull(message = "cannot be null")
    private String location;


    @AssertTrue(message = "It should be true") //here when we click checkbox it becomes true. This is how checkbox works.
//    //AssertTrue supposes that you click checkbox and it will become true and true equals true and as the result it does not throw error
    private boolean mustRegister;


    @Min(1)
    public int quantityOfAttendees;

    @ManyToOne // so, now Event knows about EventCategory. This creates relationship between Event and EventCategory; as I understand it creates foreign keys
    @NotNull(message="category is required")
    private EventCategory eventCategory;

    @OneToOne(cascade = {CascadeType.ALL}) //we might exclude EventDetails from Event constructor and it would help to get rid of this cascade error when one object holding another object cannot be created until the second object is created
    @Valid //this annotation will prevent validation from checking if email or description are valid; it will only check if eventDetails is present
    //@NotNull(message = "details are required")
    private EventDetails eventDetails;

//    public Event(String name) {
//        this.name = name;
//    } now we delete this constructor because we do not need to have it. however, maybe we want to use it... however,
    // using overwriting method Java will use only that constructor that has enough parameters

    public Event(String name, EventDetails eventDetails, int quantityOfAttendees, boolean mustRegister, EventCategory eventCategory) {
        //this(); // this guy will call itself (constructor) that does not have any arguments - basically it will create id and change
        //nextId plus one in the main class (basically it will change nextId which is shared by all instances of this class cause its static  property

        this.name = name;
        this.eventDetails = eventDetails;
        this.quantityOfAttendees = quantityOfAttendees;
        this.mustRegister = mustRegister;
        this.eventCategory = eventCategory;
        this.location = location;

    }

    public Event() {

//        this.id = nextId;
//        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getMustRegister() {
        return mustRegister;
    }

    public void setMustRegister(boolean mustRegister) {
        this.mustRegister = mustRegister;
    }

    public int getQuantityOfAttendees() {
        return quantityOfAttendees;
    }

    public void setQuantityOfAttendees(int quantityOfAttendees) {
        this.quantityOfAttendees = quantityOfAttendees;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    @Override
    public String toString() {
        return name;
    }


}
