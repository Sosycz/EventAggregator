package com.example.eventapp.controller;

import com.example.eventapp.dto.CommentDto;
import com.example.eventapp.dto.EventDto;
import com.example.eventapp.entity.Comment;
import com.example.eventapp.entity.Event;
import com.example.eventapp.mapper.CommentMapper;
import com.example.eventapp.mapper.EventMapper;
import com.example.eventapp.repository.CommentRepository;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class CommentController {

    @Autowired
    CommentService commentService;

//    @GetMapping("/events/{id}")
//    public String provideComments(@PathVariable("id") long id, Model model) {
//
//
////        model.addAttribute("comments", commentDtos);
////        model.addAttribute("comment", commentDto);
//        return "redirect:/events/"+ id;
//    }

    @PostMapping("/comment/{id}")
    public String addComment(@PathVariable("id") long id, @ModelAttribute("comment") CommentDto commentDto, Errors errors, Model model) {
        if(errors.hasErrors()){
            return "redirect:/events/" + id;
        }

        commentService.addComment(commentDto, id);
        return "redirect:/events/" + id;


    }
}
