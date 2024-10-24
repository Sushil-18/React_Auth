package com.example.backend.controllers;

import com.example.backend.dto.EventDTO;
import com.example.backend.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("events/")
public class EventController {

    private final EventService eventService;
    @GetMapping
    public List<EventDTO> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("{postId}")
    public EventDTO getEventById(@PathVariable("postId") Long eventId){
        return eventService.getEventById(eventId);
    }

    @PostMapping("new")
    public EventDTO addEvent(@RequestBody EventDTO eventDTO){
        return eventService.addEvent(eventDTO);
    }

    @PutMapping("{eventId}/edit")
    public EventDTO editEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO){
        return eventService.editEvent(eventId,eventDTO);
    }
}
