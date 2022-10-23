package com.example.eventapp.entity;

import com.example.eventapp.dto.CommentDto;
import com.example.eventapp.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hibernate.annotations.FetchMode.JOIN;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String title;

//    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm")
    private LocalDateTime startingTime;


//    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern  = "yyyy-MM-ddTHH:mm")
    private LocalDateTime endingTime;

    @Column(length = Integer.MAX_VALUE)
    private String description;


    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany

    private List<User> attenders;

    @ManyToOne
    private User createdBy;



    String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getAttenders() {
        return attenders;
    }

    public void setAttenders(List<User> attenders) {
        this.attenders = attenders;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
