package com.example.eventapp.dto;

import com.example.eventapp.validation.TimeConstraint;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class EventDto implements Comparable<EventDto> {
    private long id;
    @NotEmpty()
    private String title;

    @NotEmpty
    @TimeConstraint
    private String startingTime;
    @NotEmpty
    @TimeConstraint
    private String endingTime;
    @NotEmpty
    private String description;
    private UserDto createdBy;
    private List<CommentDto> comments = new ArrayList<>();
    private List<UserDto> attenders = new ArrayList<>();
    private MultipartFile image;

    String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public int compareTo(EventDto o) {
        return Long.compare(getId(), o.getId());
    }

    public boolean isAttending(UserDto userDto) {

       for( UserDto attender : getAttenders()) {
           if(attender.getUserName().equals(userDto.getUserName())){
               return true;
           }
       }
       return false;
    }
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<UserDto> getAttenders() {
        return attenders;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public void setAttenders(List<UserDto> attenders) {
        this.attenders = attenders;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
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



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startingTime='" + startingTime + '\'' +
                ", endingTime='" + endingTime + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", comments=" + comments +
                ", attenders=" + attenders +
                ", image=" + image +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
