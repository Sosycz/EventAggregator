package com.example.eventapp.controller;

import com.example.eventapp.dto.*;
import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.User;
import com.example.eventapp.mapper.EventMapper;
import com.example.eventapp.mapper.UserMapper;
import com.example.eventapp.service.CommentService;
import com.example.eventapp.service.EventService;
import com.example.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller

@RequestMapping()
public class EventsController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    EventService eventService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @GetMapping("/events")
    public String getEvents(Model model) {
        List<EventDto> events = eventService.getAllEvents();
        Collections.sort(events, new EventComparator());
        Set<EventDto> set = new TreeSet<>(new EventComparator());
        set.addAll(events);
        model.addAttribute("allEvents", set);
        model.addAttribute("comparator", new EventComparator());
        model.addAttribute("keywords", new SearchKeywords());
        return "events";
    }



    @GetMapping("/addEvent")
    public String addEvent(Model model) {
        model.addAttribute("event", new EventDto());

        return"addEvent";
    }
    @PostMapping(value = "/addEvent", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String sendEvent(@Valid @ModelAttribute("event") EventDto eventDto, Errors errors, Model model) {

        if(errors.hasErrors()){

            model.addAttribute("event", eventDto);

            return "addEvent";
        }
        Event event = eventService.addEvent(eventDto);
        String folders = "uploads/" + event.getId();
        String path = "\\" +  event.getId() + ".jpg";
//        String ApplicationPath =
//                ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(folders);
        String realPath = request.getServletContext().getRealPath(folders);


        try {
//
            Files.createDirectories(Paths.get(realPath));
            InputStream inputStream = eventDto.getImage().getInputStream();
            Files.copy(inputStream, Paths.get(realPath+ path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        eventDto = EventMapper.map(event);
        eventDto.setFilePath(realPath + path);
        eventService.addEvent(eventDto);




        return "events";
    }

    @PostMapping("/events/subscribe/{id}")
    public String subscribeToEvent(@AuthenticationPrincipal User user, @PathVariable("id") long id, @ModelAttribute("event") EventDto eventDto, Model model) {
        UserDto userDto = UserMapper.map(userService.getUserByName(user.getUserName()));
        eventDto = EventMapper.map(eventService.findEventById(id));
//
        eventService.subscribeToEvent(eventDto, userDto);
        return "redirect:/events/" + id;
    }

    @PostMapping("/events/unsubscribe/{id}")
    public String unsubscribeFromEvent(@AuthenticationPrincipal User user, @PathVariable("id") long id, @ModelAttribute("event") EventDto eventDto, Model model) {
        UserDto userDto = UserMapper.map(userService.getUserByName(user.getUserName()));
        eventDto = EventMapper.map(eventService.findEventById(id));


        eventService.unsubscribeFromEvent(eventDto, userDto);
        return "redirect:/events/" + id;
    }


    @GetMapping("/events/{id}")
    public String viewEvent(@AuthenticationPrincipal User user, @PathVariable("id") long id, Model model) {


        UserDto userDto = UserMapper.map(userService.getUserByName(user.getUserName()));
        List<CommentDto> commentDtos = commentService.findCommentsByEventId(id);

        EventDto eventDto = EventMapper.map(eventService.findEventById(id));
        CommentDto commentDto = new CommentDto();

        model.addAttribute("comment", commentDto);
        model.addAttribute("event", eventDto);
        model.addAttribute("comments", commentDtos);
        model.addAttribute("isAttending", eventDto.isAttending(userDto));

        return "eventTemplate";
    }


    @GetMapping("/events/search")
    public String showFoundEvents(@RequestParam("phrase") String phrase, Model model) {

        List<EventDto> foundEvents = eventService.findEventByTitle(phrase);
        Collections.sort(foundEvents, new EventComparator());
        model.addAttribute("foundEvents", foundEvents);
        model.addAttribute("keywords", new SearchKeywords());
        return "foundEvents";
    }



}
