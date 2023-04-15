package com.example.eventapp.repository;

import com.example.eventapp.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.title like concat('%', ?1, '%')")
    public List<Event> findAllByTitle(String text);

    public List<Event> findAll();



}
