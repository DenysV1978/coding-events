package org.launchcode.codingevents.controllers;


import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    // this means that Spring will have to fulfill this class for us. It will be automonitored


    //private static List<Event> events = new ArrayList<>(); now we remove this after we created EventData because now we gonna store all events in EventData


    @GetMapping()
    public String displayAllEvents(Model model) {

        model.addAttribute("events", eventRepository.findAll()); //now we change how we get values using EventData!!!!! the thing is that this is a class that will never be instantiated
        System.out.println("stop");
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create event");
        model.addAttribute("event", new Event()); // we could do model.addAttribute(new Event()); - in this case TH
        //would create label "event"
        model.addAttribute("types", EventType.values());
        return "events/create";

    }

    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) { //so, this idea is about that @ModelAttribute will find necessary parameters for
        //creating new Event newEvent and create this newEvent and after push it in EventData list. Do not forget that variables in html in this case should have the same names as variables in Event class
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("type", EventType.values());
//
            return "events/create";
        }
        //System.out.println("stop");
        eventRepository.save(newEvent); // this basically means that we push object in the List instead of just Strings. So,
        //every time we have new event thrown from TH we create new object Event and push it in List events!!!
        return "redirect:"; //this means it sends to a method that handles this level of url. In our case it is /events (this is when there is nothing after : . If I
        //wanted to return it to the method handling (controlling) url events/create, I would type redirect:create. And it would run again method
        //createEvent...


    }

    //the next two controller responsible for deletion

    @GetMapping("delete") //this guy will send info to th: html. Will be triggered by url .../delete
    public String displayDeleteEventForm(Model model) {

        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete") // this is the way to call this controller is when user hits Submit button
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) { // looks like this required means that it is not required to have eventIds during running of this controller

       // System.out.println("Stop");

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    //the next two are responsible for edition

    //this guy is triggered by simple submission button at <a th:href="@{/events/edit/{id}(id=${event.id})}">Edit</a> at events/index
    //this button is located against each event. So, when the user hits specific button edit, it returns url  events/edit/id, which always triggers
    //this controller, because this controller has flexible route!!! BTW this is why we have @PathVariable - it gives variable that could be
    //sent to url route for this controller!
    @GetMapping(value = "edit/{eventId}") //so, this get mapping catches flexible URL that depends on eventId variable..
    public String displayEditForm(Model model, @PathVariable int eventId) {
        model.addAttribute("event", eventRepository.findById(eventId).get());
       System.out.println("stop");
        model.addAttribute("title", "Edit event with Name:"+eventRepository.findById(eventId).get().getName()+", ID: "+eventRepository.findById(eventId).get().getId()+".");
        System.out.println("stop");

        return "events/edit";
    }

    @PostMapping("edit") //this guy will re-save name and description under the same ID... it will edit event
    public String processEditForm(int eventId, String name, String description) {
        eventRepository.findById(eventId);
        eventRepository.findById(eventId).get().setDescription(description);

        return "redirect:";
    }

}
