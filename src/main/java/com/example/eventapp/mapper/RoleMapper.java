package com.example.eventapp.mapper;

import com.example.eventapp.dto.RoleDto;
import com.example.eventapp.entity.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoleMapper {

    public static RoleDto map(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setUsers(UserMapper.mapUserList(role.getUsers()));
        return roleDto;
    }

    public static Role map(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setUsers(UserMapper.mapUserDtoList(roleDto.getUsers()));
        return role;
    }

    public static List<RoleDto> mapRoleList(List<Role> roles) {
        List<RoleDto> roleDtos = new ArrayList<>();
        roles.forEach(el -> roleDtos.add(map(el)));
        return roleDtos;
    }

    public static List<Role> mapRoleDtoList(List<RoleDto> roleDtos) {
        List<Role> roles = new ArrayList<>();
        roleDtos.forEach(el->roles.add(RoleMapper.map(el)));
        return roles;
    }

}
