package com.taskforge.service;

import com.taskforge.dto.LoginRequestDto;
import com.taskforge.dto.UserDto;
import com.taskforge.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    String registerUser(UserDto userDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(String id);

    String updateUser(String id, UserDto userDto);

    String deleteUser(String id);

    String loginUser(LoginRequestDto loginRequestDto);
}