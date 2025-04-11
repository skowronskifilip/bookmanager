package com.github.skowronskifilip.bookmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.github.skowronskifilip.bookmanager.dto.UserSmallDetailsDTO;
import com.github.skowronskifilip.bookmanager.models.database.User;
import com.github.skowronskifilip.bookmanager.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_USER")
    @GetMapping("/me")
    public ResponseEntity<UserSmallDetailsDTO> getUserMineDetails(Authentication authentication) {
        String username = authentication.getName();
        int userId = userService.getUserIdByUsername(username);
        Optional<User> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String responseUsername = user.getUsername();
            String responseCreated = user.getCreated().toString();
            boolean responseMastermind = user.isAdmin();

            UserSmallDetailsDTO userSmallDetailsDTO = new UserSmallDetailsDTO(responseUsername,  responseCreated, responseMastermind);
            return ResponseEntity.ok(userSmallDetailsDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}