package org.launchcode.codingevents.controllers;


import org.hibernate.Session;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;

import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    // this means that Spring will have to fulfill this class for us. It will be automonitored

    @Autowired
    private EventCategoryRepository eventCategoryRepository;


    //private static List<Event> events = new ArrayList<>(); now we remove this after we created EventData because now we gonna store all events in EventData


    @GetMapping()
    public String displayAllEvents(@RequestParam(required=false) Integer categoryId, Model model) { //@RequestParam will grab this categoryId from url line... (required=false) means this parameter is not required so if no parameter, controller still works...
        //Integer categoryId is parameter that is created by query request at url created by hrefs!!
        if(categoryId==null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result =  eventCategoryRepository.findById(categoryId);
           //the idea of this Optional is to return even something when there is nothing to return - this could create exception.
            //however, having this Optional we have either empty or full object Optional
           if(result.isEmpty()) {
               model.addAttribute("title", "Invalid category ID: " + categoryId);
           } else {
               EventCategory category = result.get();
               model.addAttribute("title", "Events in category: " + category.getName() );
               model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create event");
        model.addAttribute("event", new Event()); // we could do model.addAttribute(new Event()); - in this case TH
        //would create label "event"
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";

    }

    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) { //so, this idea is about that @ModelAttribute will find necessary parameters for
        //creating new Event newEvent and create this newEvent and after push it in EventData list. Do not forget that variables in html in this case should have the same names as variables in Event class
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("categories", eventCategoryRepository.findAll());
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
        model.addAttribute("events", eventRepository.findAll()); //this is going to be arrayList!!!
        System.out.println("stop");
        return "events/delete";
    }

    @PostMapping("delete") // this is the way to call this controller is when user hits Submit button
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) { // looks like this required means that it is not required to have eventIds during running of this controller
//eventsIds is array that was created by check boxes at Thymeleaf
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
        model.addAttribute("event", eventRepository.findById(eventId).get()); //looks like we have to use get() because findById returns kind of hasmap with key and value, where value is the Object!
       System.out.println("stop");
        model.addAttribute("title", "Edit event with Name:"+eventRepository.findById(eventId).get().getName()+", ID: "+eventRepository.findById(eventId).get().getId()+".");
        System.out.println("stop");

        return "events/edit";
    }

    @PostMapping("edit") //this guy will re-save name and description under the same ID... it will edit event
    public String processEditForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model, int eventId) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("categories", eventCategoryRepository.findAll());
//
            return "events/create";
        }
        System.out.println("stop");
        Event temp = eventRepository.findById(eventId).get(); // this is a great example how to save in way to UPDATE save(entity)
        temp.setName(newEvent.getName()); //HERE YOU DEFINITELY SEE THAT OUR EDIT NETHOD DEALS ONLY WITH CHANGING OF NAME. IF YOU WANT TO CHANGE ALL OTHER FIELDS - USE SETTER AND CHANGE OTHER FIELDS IN TEMP ENTITY AND AFTER RESAVE IT USING ENTITY SAVE
        System.out.println("stop");

        //eventRepository.deleteById(eventId);
        eventRepository.save(temp);

        
        return "redirect:";
    }

}
