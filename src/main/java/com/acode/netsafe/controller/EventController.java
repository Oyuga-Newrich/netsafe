package com.acode.netsafe.controller;

import com.acode.netsafe.entity.Event;
import com.acode.netsafe.entity.MajorCategory;
import com.acode.netsafe.entity.Minorcategory;
import com.acode.netsafe.entity.MyEventList;
import com.acode.netsafe.service.EventService;
import com.acode.netsafe.service.MyEventListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/about")
    public  String about() {
        return "about";
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
    public String addEvent(@ModelAttribute Event event, Model model) {
        eventService.save(event);
        model.addAttribute("message", "Incident Reported Successfull.");
        return "eventRegister";
//        return "redirect:/available_events";
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

//    @RequestMapping("/viewEvent/{id}")
//    public String viewEvent(@PathVariable("id") Integer id) {
//        Event event= eventService.getEventById(id);
//        MyEventList myEvent=new MyEventList(event.getId(),event.getLocation(),event.getTown(),event.getDetails(),event.getMajorCategory(),event.getMinorCategory(),event.getDate(),event.getTime());
//        myEventListService.saveMyEvents(myEvent);
//        return "viewEvent";
//
//    }

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

    @ModelAttribute("minorcategories")
    public List<Minorcategory> getMinorcategories () {
        List<Minorcategory> list = new ArrayList<Minorcategory>();
        list.add(new Minorcategory("Robbery", "Robbery"));
        list.add(new Minorcategory("Criminal Killing", "Criminal Killing"));
        list.add(new Minorcategory("Arrest", "Arrest"));
        list.add(new Minorcategory("Fire", "Fire"));
        list.add(new Minorcategory("Gang Activities", "Gang Activities"));
        list.add(new Minorcategory("Civil Disruption", "Civil Disruption"));
        list.add(new Minorcategory("Shooting", "Shooting"));
        list.add(new Minorcategory("Floods", "Floods"));
        return list;
    }

    @ModelAttribute("majorCategories")
    public List<MajorCategory> getMajorcategories () {
        List<MajorCategory> list = new ArrayList<MajorCategory>();
        list.add(new MajorCategory("Crime", "Crime"));
        list.add(new MajorCategory("Accident", "Accident"));
        list.add(new MajorCategory("Terrorism", "Terrorism"));
        list.add(new MajorCategory("Air Strike", "Air Strike"));
        list.add(new MajorCategory("Natural Disaster", "Natural Disaster"));
        return list;
    }


    @GetMapping("/search")
    public String search(@Param("keyword") String keyword) {
        System.out.println("keyword: " + keyword);
        return "my_events";
    }

}

