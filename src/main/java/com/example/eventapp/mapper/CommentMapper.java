package com.example.eventapp.mapper;

import com.example.eventapp.dto.CommentDto;
import com.example.eventapp.entity.Comment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class CommentMapper {
    public static CommentDto map(Comment comment) {
        CommentDto commentDto = new CommentDto();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setEvent(EventMapper.map(comment.getEvent()));
        if(comment.getCreatedAt() != null) {
            commentDto.setCreatedAt(dateFormatter.format(comment.getCreatedAt()));
        }
        commentDto.setAddedBy(UserMapper.map(comment.getAddedBy()));
        return commentDto;
    }

    public static Comment map(CommentDto commentDto) {
        Comment comment = new Comment();

//      try {
//            comment.setCreatedAt(dateFormat.parse(commentDto.getCreatedAt()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        comment.setText(commentDto.getText());
//        comment.setEvent(EventMapper.map(commentDto.getEvent()));
//        comment.setAddedBy(UserMapper.map(commentDto.getAddedBy()));

        return comment;
    }

    public static List<CommentDto> map(List<Comment> comments) {
        List<CommentDto> dtos = new ArrayList<>();

        comments.forEach(el -> dtos.add(map(el)));
        return dtos;



    }


}
