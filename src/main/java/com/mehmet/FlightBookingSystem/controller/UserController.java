package com.mehmet.FlightBookingSystem.controller;

import com.mehmet.FlightBookingSystem.model.dto.UserDataDTO;
import com.mehmet.FlightBookingSystem.model.dto.UserLoginDTO;
import com.mehmet.FlightBookingSystem.model.dto.UserResponseDTO;
import com.mehmet.FlightBookingSystem.model.entity.User;
import com.mehmet.FlightBookingSystem.service.iml.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public String login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return userService.signin(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid UserDataDTO user) {
        ModelMapper modelMapper = new ModelMapper();
        User userEntity = modelMapper.map(user, User.class);
        return userService.signup(userEntity);
    }

    @DeleteMapping(value = "/{username}")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/me")
    public UserResponseDTO whoami(HttpServletRequest req) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }
}
