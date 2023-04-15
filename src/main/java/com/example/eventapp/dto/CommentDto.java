package com.example.eventapp.dto;

import com.example.eventapp.entity.Event;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.constraints.NotEmpty;

public class CommentDto {
    private long id;
    private EventDto event;
    @NotEmpty
    private String text;
    private String createdAt;
    private UserDto addedBy;



    public UserDto getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserDto addedBy) {
        this.addedBy = addedBy;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }





    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
