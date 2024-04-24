package com.acode.netsafe.service;

import com.acode.netsafe.entity.Event;
import com.acode.netsafe.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void save(Event event) {
        eventRepository.save(event);
    }

    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }
}
