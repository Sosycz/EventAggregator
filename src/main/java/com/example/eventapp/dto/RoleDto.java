package com.example.eventapp.dto;

import java.util.Collection;
import java.util.List;

public class RoleDto {
    private long id;
    private String name;
    private List<UserDto> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
