package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class Event extends AbstractEntity {




    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "its too long")
    private String description;

    @NotBlank
    @Email(message = "Invalid email. Try again")
    private String contactEmail;

    @NotBlank(message = "Location must be set up")
    @NotNull(message = "cannot be null")
    private String location;


    @AssertTrue(message = "It should be true") //here when we click checkbox it becomes true. This is how checkbox works.
//    //AssertTrue supposes that you click checkbox and it will become true and true equals true and as the result it does not throw error
    private boolean mustRegister;


    @Min(1)
    public int quantityOfAttendees;

    @ManyToOne
    @NotNull(message="category is required")
    private EventCategory eventCategory;

//    public Event(String name) {
//        this.name = name;
//    } now we delete this constructor because we do not need to have it. however, maybe we want to use it... however,
    // using overwriting method Java will use only that constructor that has enough parameters

    public Event(String name, String description, String contactEmail, int quantityOfAttendees, boolean mustRegister, EventCategory eventCategory) {
        //this(); // this guy will call itself (constructor) that does not have any arguments - basically it will create id and change
        //nextId plus one in the main class (basically it will change nextId which is shared by all instances of this class cause its static  property

        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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
