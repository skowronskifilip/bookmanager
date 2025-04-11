package com.github.skowronskifilip.bookmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.skowronskifilip.bookmanager.dto.UserLoginPasswordDTO;
import com.github.skowronskifilip.bookmanager.exceptions.UsernameAlreadyTakenException;
import com.github.skowronskifilip.bookmanager.services.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserLoginPasswordDTO userLoginPasswordDTO) {
        try {
            userService.registerUser(userLoginPasswordDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        }
        catch (UsernameAlreadyTakenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username taken");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginPasswordDTO userLoginPasswordDTO) {
        String token = userService.verifyUser(userLoginPasswordDTO);
        if (token != null) {
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error");
        }
    }
}