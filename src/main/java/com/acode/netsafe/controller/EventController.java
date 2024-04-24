package com.acode.netsafe.controller;

import com.acode.netsafe.entity.Category;
import com.acode.netsafe.entity.Event;
import com.acode.netsafe.entity.MyEventList;
import com.acode.netsafe.service.EventService;
import com.acode.netsafe.service.MyEventListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private MyEventListService myEventListService;

    @GetMapping("user")
    public String user() {
        return "user";
    }

    @GetMapping("/event_register")
    public String eventRegister() {
        return "eventRegister";
    }

    @GetMapping("/available_events")
    public ModelAndView getAllEvent() {
        List<Event> list=eventService.getAllEvent();
        return new ModelAndView("eventList","event",list);
    }

    @PostMapping("/save")
    public String addEvent(@ModelAttribute Event event) {
        eventService.save(event);
        return "redirect:/available_events";
    }

    @GetMapping("/my_events")
    public String getMyEvents(Model model) {
        List<MyEventList>list=myEventListService.getAllMyEvents();
        model.addAttribute("event",list);
        return "myEvents";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") Integer id) {
        Event event=eventService.getEventById(id);
        MyEventList myEvent=new MyEventList(event.getId(),event.getLocation(),event.getTown(),event.getDetails(),event.getMajorCategory(),event.getMinorCategory(),event.getDate(),event.getTime());
        myEventListService.saveMyEvents(myEvent);
        return "redirect:/my_events";
    }

    @RequestMapping("/editEvent/{id}")
    public String editEvent(@PathVariable("id") Integer id, Model model) {
        Event event=eventService.getEventById(id);
        model.addAttribute("event", event);
        return "eventEdit";
    }

    @RequestMapping("/deleteMylist/{id}")
    public String deleteEvent(@PathVariable("id")Integer id) {
        eventService.deleteById(id);
        return"redirect:/available_events";
    }

//    @PostMapping("/search")
//    public String doSearchEvent(@ModelAttribute("eventSearchFormData") Event formData, Model model) {
//        Event event = eventService.getEventById(formData.getId());
//        model.addAttribute("Event", event);
//        return "user";
//    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("event", new Event());
        return "event_register";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories () {
        List<Category> list = new ArrayList<Category>();
        list.add(new Category("Crime", "Robbery"));
        list.add(new Category("Crime", "Criminal Killing"));
        list.add(new Category("Crime", "Arrest"));
        list.add(new Category("Crime", "Fire"));
        list.add(new Category("Crime", "Gang Activities"));
        return list;
    }
}

