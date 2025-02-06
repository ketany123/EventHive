package com.eventmanagement.demo.service;

import com.eventmanagement.demo.entity.Event;
import com.eventmanagement.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public Optional<Event> getEventById(Long id){
        Optional<Event> byId =  eventRepository.findById(id);
        return byId;
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public void deleteEventById(Long id){
        eventRepository.deleteById(id);
    }


}
