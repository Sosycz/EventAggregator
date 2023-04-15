package com.example.eventapp.dto;


import com.example.eventapp.entity.Role;
import com.example.eventapp.validation.EmailTakenConstraint;
import com.example.eventapp.validation.PasswordConstraint;
import com.example.eventapp.validation.UserNameConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@PasswordConstraint
@UserNameConstraint
public class UserDto implements Comparable<UserDto> {

    private long id;

    @NotEmpty
    @Email
    @EmailTakenConstraint
    private String email;

    @NotEmpty
    @Size(min = 8, max = 30)
    private String password;

    @NotEmpty
    @Size(min = 8, max = 30)
    private String passwordRetype;

    @NotEmpty
    @Size(max = 50)
    @Pattern(regexp = "^\\S+", message = "user name cannot contain spaces")
    private String userName;

    Collection<RoleDto> roles;

    @Override
    public int compareTo(UserDto o) {
        return Long.compare(getId(), o.getId());
    }

    public Collection<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleDto> roles) {
        this.roles = roles;
    }

    private List<EventDto> attendedEvents = new ArrayList<>();

    private List<EventDto> createdEvents = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public List<EventDto> getAttendedEvents() {
//        return attendedEvents;
//    }
//
//    public void setAttendedEvents(List<EventDto> attendedEvents) {
//        this.attendedEvents = attendedEvents;
//    }
//
    public List<EventDto> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(List<EventDto> createdEvents) {
        this.createdEvents = createdEvents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRetype() {
        return passwordRetype;
    }

    public void setPasswordRetype(String passwordRetype) {
        this.passwordRetype = passwordRetype;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
