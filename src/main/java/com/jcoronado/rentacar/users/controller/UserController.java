package com.jcoronado.rentacar.users.controller;

import com.jcoronado.rentacar.users.dto.UserRequest;
import com.jcoronado.rentacar.users.dto.UserResponse;
import com.jcoronado.rentacar.users.model.User;
import com.jcoronado.rentacar.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request){
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User saved = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(
                        saved.getId(),
                        saved.getFirstName(),
                        saved.getLastName(),
                        saved.getEmail()
                ));
    }



}
