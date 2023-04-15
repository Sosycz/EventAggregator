package com.example.eventapp.repository;

import com.example.eventapp.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    public List<Comment> findCommentsByEventId(long id);
}
