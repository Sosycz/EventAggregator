package com.example.eventapp.mapper;

import com.example.eventapp.dto.EventDto;
import com.example.eventapp.dto.RoleDto;
import com.example.eventapp.dto.UserDto;
import com.example.eventapp.entity.Event;
import com.example.eventapp.entity.Role;
import com.example.eventapp.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
//        userDto.setAttendedEvents(EventMapper.mapEventList(user.getAttendedEvents()));
        userDto.setCreatedEvents(EventMapper.mapEventList(user.getCreatedEvents()));

//        userDto.setAttendedEvents(EventMapper.mapEventList(user.getAttendedEvents()));


        userDto.setCreatedEvents(EventMapper.mapEventList(user.getCreatedEvents()));


        return userDto;
    }

    public static User map(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

//        user.setAttendedEvents(EventMapper.mapEventDtoList(userDto.getAttendedEvents()));
        user.setCreatedEvents(EventMapper.mapEventDtoList(userDto.getCreatedEvents()));

        return user;
    }






    public static List<User> mapUserDtoList(List<UserDto> userDtos) {
        List<User> users = new ArrayList<>();
        userDtos.forEach(el -> users.add(UserMapper.map(el)));
        return users;

    }

    public static List<UserDto> mapUserList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(el -> userDtos.add(UserMapper.map(el)));
        return userDtos;
    }




}
