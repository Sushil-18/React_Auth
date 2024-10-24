package com.example.backend.services;

import com.example.backend.dto.EventDTO;
import com.example.backend.entities.EventEntity;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public List<EventDTO> getAllEvents() {
        return eventRepository
                .findAll()
                .stream()
                .map(eventEntity -> modelMapper.map(eventEntity,EventDTO.class))
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long eventId) throws ResourceNotFoundException{
        EventEntity eventEntity = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event with id "+eventId+ " does not exists"));

        return modelMapper.map(eventEntity,EventDTO.class);
    }

    public EventDTO addEvent(EventDTO eventDTO) {
        EventEntity eventEntity = modelMapper.map(eventDTO,EventEntity.class);
        return modelMapper.map(eventRepository.save(eventEntity),EventDTO.class);
    }

    public EventDTO editEvent(Long eventId, EventDTO eventDTO) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event with id "+eventId+" does not exists"));

        eventEntity.setTitle(eventDTO.getTitle());
        eventEntity.setDate(eventDTO.getDate());
        eventEntity.setImageURL(eventDTO.getImageURL());
        eventEntity.setDescription(eventDTO.getDescription());

        return modelMapper.map(eventRepository.save(eventEntity),EventDTO.class);
    }
}
