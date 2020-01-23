package org.launchcode.codingevents.controllers;


import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping()
    public String displayAllEvents (Model model) {
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        model.addAttribute("title", "All Categories");
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm (Model model) {
        model.addAttribute("title", "Create New Category");
        model.addAttribute("eventCategory", new EventCategory());

        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newEventCategory, Errors errors, Model model) { //so, this idea is about that @ModelAttribute will find necessary parameters for
        //creating new Event newEvent and create this newEvent and after push it in EventData list. Do not forget that variables in html in this case should have the same names as variables in Event class
        if(errors.hasErrors()) {



            return "eventCategories/create";
        }
        //System.out.println("stop");
        eventCategoryRepository.save(newEventCategory); // this basically means that we push object in the List instead of just Strings. So,
        //every time we have new event thrown from TH we create new object Event and push it in List events!!!
        return "redirect:"; //this means it sends to a method that handles this level of url. In our case it is /events (this is when there is nothing after : . If I
        //wanted to return it to the method handling (controlling) url events/create, I would type redirect:create. And it would run again method
        //createEvent...


    }


}
