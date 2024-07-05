package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getUsers() {
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("User list retrieved successfully")
                .data(userService.listAllUsers()).build());
    }

    @GetMapping("/{username}")
    public ResponseEntity<ResponseWrapper> getUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("User " + username + " retrieved succesfully")
                .data(userService.findByUserName(username)).build());
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseWrapper.builder()
                        .code(HttpStatus.CREATED.value())
                        .success(true)
                        .message("User created successfully")
                        .build());
    }

    @PutMapping("/{username}")
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user, @PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(ResponseWrapper.builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .success(true)
                        .message("User " + username + " updated successfully")
                        .data(userService.update(user)).build());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("username") String username) {
        userService.delete(username);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("User " + username + " deleted successfully")
                .build());
    }
}
