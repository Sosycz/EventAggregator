package com.example.eventapp.service;

import com.example.eventapp.dto.UserDto;
import com.example.eventapp.entity.User;
import com.example.eventapp.mapper.UserMapper;
import com.example.eventapp.repository.RoleRepository;
import com.example.eventapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    public boolean registerUser(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = UserMapper.map(userDto);
        user.getRoles().add(roleRepository.findRoleByName("ROLE_USER").get());
        user = userRepository.save(user);

        return user.getId() != 0;
    }
    public User getUserByName(String name){
        return userRepository.findByUserName(name);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
    public com.example.eventapp.entity.User getLoggedInUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return (com.example.eventapp.entity.User) authentication.getPrincipal();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
