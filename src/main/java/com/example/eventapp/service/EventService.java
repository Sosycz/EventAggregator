package com.example.eventapp.service;

import com.example.eventapp.dto.EventComparator;
import com.example.eventapp.dto.EventDto;
import com.example.eventapp.dto.UserDto;
import com.example.eventapp.entity.Event;
import com.example.eventapp.mapper.EventMapper;
import com.example.eventapp.mapper.UserMapper;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    public List<EventDto> getAllEvents() {
     Comparator<EventDto> comparator = new EventComparator();

     List<EventDto> events = EventMapper.mapEventList(eventRepository.findAll());
        Collections.sort(events, comparator);
        return events;

    }

    public Event addEvent(EventDto eventDto) {
        eventDto.setCreatedBy(UserMapper.map(userRepository.findByUserName(userService.getLoggedInUser().getUsername())));

        return eventRepository.save(EventMapper.map(eventDto));
    }

    public Event subscribeToEvent(EventDto eventDto, UserDto userDto) {
//        if(!userDto.getAttendedEvents().isEmpty()) {
//            for (EventDto el : userDto.getAttendedEvents()) {
//                if (el.compareTo(eventDto) == 0) {
//                    return EventMapper.map(eventDto);
//                }
//            }
//        }
        if(!eventDto.getAttenders().isEmpty()) {
            for (UserDto el : eventDto.getAttenders()) {
                if (el.compareTo(userDto) == 0) {
                    return EventMapper.map(eventDto);
                }
            }
        }
//        userDto.getAttendedEvents().add(eventDto);
        eventDto.getAttenders().add(userDto);
        return eventRepository.save(EventMapper.map(eventDto));
    }
    public Event unsubscribeFromEvent(EventDto eventDto, UserDto userDto) {

//        if(!eventDto.getAttenders().isEmpty()) {
//            for (UserDto el : eventDto.getAttenders()) {
//                if (el.compareTo(userDto) == 0) {
//                    return EventMapper.map(eventDto);
//                }
//            }
//        }
        eventDto.getAttenders().removeIf(el->el.getUserName().equals(userDto.getUserName()));
        return eventRepository.save(EventMapper.map(eventDto));
    }



    public Event findEventById(long id) {
        return eventRepository.findById(id).get();
    }

    public List<EventDto> findEventByTitle(String title){
        List<Event> events = eventRepository.findAllByTitle(title);

        List<EventDto> eventDtos = EventMapper.mapEventList(eventRepository.findAllByTitle(title));
        Collections.sort(eventDtos);
        return eventDtos;
    }

}



