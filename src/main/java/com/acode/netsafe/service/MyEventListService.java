package com.acode.netsafe.service;

import com.acode.netsafe.entity.MyEventList;
import com.acode.netsafe.repository.MyEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyEventListService {

    @Autowired
    private MyEventsRepository myEventsRepository;
    public void saveMyEvents(MyEventList event) {
        myEventsRepository.save(event);
    }

    public List<MyEventList> getAllMyEvents() {
        return myEventsRepository.findAll();
    }

    public void deleteById(Integer id) {
        myEventsRepository.deleteById(id);
    }

    public List<MyEventList> search(String keyword) {
        return myEventsRepository.search(keyword);
    }
}

