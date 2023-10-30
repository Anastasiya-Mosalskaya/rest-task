package org.epam.resttask.cloud.converter;

import org.epam.resttask.dto.User;
import org.epam.resttask.dto.request.UserRequestDto;
import org.epam.resttask.dto.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public User convertToUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        user.setBirthday(LocalDate.parse(userRequestDto.getBirthday()));
        return user;
    }

    public UserResponseDto convertToResponseDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }
}
