package com.example.eventapp.mapper;

import com.example.eventapp.dto.EventDto;
import com.example.eventapp.entity.Event;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {

    public static Event map(EventDto eventDto) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        Event event = new Event();
        if(eventDto.getId()!=0)
        event.setId(eventDto.getId());


        event.setDescription(eventDto.getDescription());
        event.setTitle(eventDto.getTitle());
        event.setCreatedBy(UserMapper.map(eventDto.getCreatedBy()));
        event.setAttenders(UserMapper.mapUserDtoList(eventDto.getAttenders()));
        event.setFilePath(eventDto.getFilePath());




        try {
            event.setStartingTime(LocalDateTime.parse(eventDto.getStartingTime(), timeFormatter));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        try {
            event.setEndingTime(LocalDateTime.parse(eventDto.getEndingTime(), timeFormatter));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }




        return event;
    }

    public static EventDto map(Event event) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE_TIME;


        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setDescription(event.getDescription());
        eventDto.setTitle(event.getTitle());
        eventDto.setCreatedBy(UserMapper.map(event.getCreatedBy()));

        eventDto.setAttenders(UserMapper.mapUserList(event.getAttenders()));
        eventDto.setFilePath(event.getFilePath());


        eventDto.setStartingTime(dateFormatter.format(event.getStartingTime()));

        eventDto.setEndingTime(dateFormatter.format(event.getEndingTime()));

        eventDto.setComments(CommentMapper.map(event.getComments()));

        return eventDto;
    }

    public static List<EventDto> mapEventList(List<Event> events) {
        List<EventDto> eventDtos = new ArrayList<>();
        events.forEach(el -> eventDtos.add(map(el)));
        return eventDtos;
    }
    public static List<Event> mapEventDtoList(List<EventDto> eventDtos) {
        List<Event> events = new ArrayList<>();
        eventDtos.forEach(el -> events.add(map(el)));
        return events;
    }



}
