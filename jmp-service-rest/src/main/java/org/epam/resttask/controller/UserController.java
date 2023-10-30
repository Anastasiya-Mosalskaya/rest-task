package org.epam.resttask.controller;

import org.epam.resttask.dto.request.UserRequestDto;
import org.epam.resttask.dto.response.UserResponseDto;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    HttpEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto);
    HttpEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto);
    HttpEntity<?> deleteUser(@PathVariable Long id);
    HttpEntity<UserResponseDto> getUser(@PathVariable Long id);
    HttpEntity<List<UserResponseDto>> getAllUsers();
}
