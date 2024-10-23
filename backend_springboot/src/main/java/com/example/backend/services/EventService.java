package com.example.backend.services;

import com.example.backend.dto.EventDTO;
import com.example.backend.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<List<EventDTO>> getAllEvents() {
        
    }

    public ResponseEntity<EventDTO> getEventById(Long eventId) {
    }

    public ResponseEntity<EventDTO> addEvent(EventDTO eventDTO) {
    }

    public ResponseEntity<EventDTO> editEvent(Long eventId, EventDTO eventDTO) {
    }
}
