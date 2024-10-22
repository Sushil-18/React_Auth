package com.example.backend.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDTO {
    @NotBlank(message = "Title should not be empty")
    private String title;
    private String imageURL;
    @Future(message = "The event date should be in future")
    private LocalDate date;
    @NotBlank(message = "The description must have some content")
    private String description;
}
