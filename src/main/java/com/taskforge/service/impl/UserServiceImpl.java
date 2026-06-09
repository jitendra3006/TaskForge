package com.taskforge.service.impl;

import com.taskforge.dto.LoginRequestDto;
import com.taskforge.dto.UserDto;
import com.taskforge.dto.UserResponseDto;
import com.taskforge.entity.User;
import com.taskforge.repository.UserRepository;
import com.taskforge.service.UserService;
import com.taskforge.util.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String registerUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        user.setPassword(
                passwordEncoder.encode(userDto.getPassword()));

        user.setRole("ROLE_USER");

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        return convertToResponseDto(user);
    }

    @Override
    public String updateUser(String id, UserDto userDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        user.setPassword(
                passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);

        return "User Updated Successfully";
    }

    @Override
    public String deleteUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        userRepository.delete(user);

        return "User Deleted Successfully";
    }

    @Override
    public String loginUser(LoginRequestDto loginRequestDto) {

        User user = userRepository
                .findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email"));

        boolean matches = passwordEncoder.matches(
                loginRequestDto.getPassword(),
                user.getPassword());

        if (!matches) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    private UserResponseDto convertToResponseDto(User user) {

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole());
    }
}