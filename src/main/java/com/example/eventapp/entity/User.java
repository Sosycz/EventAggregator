package com.example.eventapp.entity;

import com.example.eventapp.dto.EventDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.engine.FetchStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hibernate.annotations.FetchMode.*;

@Entity
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;
    private String repeatPassword;
    private String userName;

//    @LazyCollection(LazyCollectionOption.FALSE)
////    @ManyToMany
//    @ManyToMany(mappedBy = "attenders")
//    private List<Event> attendedEvents;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany

    private List<Event> createdEvents;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        getRoles().forEach(el -> {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(el.getName()));
        });
        return simpleGrantedAuthorities;
    }



    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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






    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public List<Event> getAttendedEvents() {
//        return attendedEvents;
//    }
//
//    public void setAttendedEvents(List<Event> attendedEvents) {
//        this.attendedEvents = attendedEvents;
//    }

    public List<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(List<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }
}
