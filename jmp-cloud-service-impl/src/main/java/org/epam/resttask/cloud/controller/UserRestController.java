package org.epam.resttask.cloud.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.epam.resttask.cloud.converter.UserConverter;
import org.epam.resttask.controller.UserController;
import org.epam.resttask.dto.User;
import org.epam.resttask.dto.request.UserRequestDto;
import org.epam.resttask.dto.response.UserResponseDto;
import org.epam.resttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserRestController implements UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new user")
    public HttpEntity<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        User user = userConverter.convertToUser(userRequestDto);
        User createdUser = userService.createUser(user);
        return ResponseEntity.created(linkTo(methodOn(UserRestController.class).getUser(createdUser.getId())).toUri())
                .body(userConverter.convertToResponseDto(createdUser));
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Update user according to provided id")
    public HttpEntity<UserResponseDto> updateUser(UserRequestDto userRequestDto) {
        User user = userConverter.convertToUser(userRequestDto);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.created(linkTo(methodOn(UserRestController.class).getUser(updatedUser.getId())).toUri())
                .body(userConverter.convertToResponseDto(updatedUser));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    @Operation(summary = "Delete a user by id")
    public HttpEntity<?> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get a user by id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    public HttpEntity<UserResponseDto> getUser(Long id) {
        User user = userService.getUser(id);
        UserResponseDto userResponseDto = userConverter.convertToResponseDto(user);
        userResponseDto.add(linkTo(methodOn(UserRestController.class).getUser(id)).withSelfRel());
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Return all users")
    public HttpEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> responseDto = users.stream()
                .map(user -> {
                    UserResponseDto userResponseDto = userConverter.convertToResponseDto(user);
                    userResponseDto.add(linkTo(methodOn(UserRestController.class).getUser(userResponseDto.getId()))
                            .withSelfRel());
                    return userResponseDto;
                }).toList();
        return ResponseEntity.ok(responseDto);
    }
}
