package com.example.eventapp.controller;

import com.example.eventapp.dto.UserDto;
import com.example.eventapp.repository.UserRepository;
import com.example.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute(value = "user") UserDto user, Errors errors, Model model){
        if(errors.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }

        userService.registerUser(user);
        return "redirect:/login";
    }

}
