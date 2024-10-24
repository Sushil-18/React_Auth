package com.example.backend.repository;

import com.example.backend.entities.EventEntity;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {
}
