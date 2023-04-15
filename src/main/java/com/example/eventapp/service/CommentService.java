package com.example.eventapp.service;

import com.example.eventapp.dto.CommentDto;
import com.example.eventapp.entity.Comment;
import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.User;
import com.example.eventapp.mapper.CommentMapper;
import com.example.eventapp.repository.CommentRepository;
import com.example.eventapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {

    @Autowired
    public EventRepository eventRepository;
    @Autowired
    public CommentRepository commentRepository;
    @Autowired
    public EventService eventService;



    public void addComment(CommentDto commentDto, long id) {



        Event event = eventRepository.findById(id).get();
        Comment comment = CommentMapper.map(commentDto);

        comment.setEvent(event);
        comment.setAddedBy((User)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal());

        commentRepository.save(comment);

    }

    public List<CommentDto> findCommentsByEventId(long id) {
        try{

            return CommentMapper.map(commentRepository.findCommentsByEventId(id));


        }catch(NoSuchElementException e) {
            e.printStackTrace();
            return new ArrayList<CommentDto>();
        }

    }
}
