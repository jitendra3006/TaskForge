package com.taskforge.controller;

import com.taskforge.dto.LoginRequestDto;
import com.taskforge.dto.UserDto;
import com.taskforge.dto.UserResponseDto;
import com.taskforge.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        return "TaskForge User API Working";
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @PostMapping("/login")
    public String login(
            @Valid @RequestBody LoginRequestDto loginRequestDto) {

        return userService.loginUser(loginRequestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public String updateUser(
            @PathVariable String id,
            @Valid @RequestBody UserDto userDto) {

        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}