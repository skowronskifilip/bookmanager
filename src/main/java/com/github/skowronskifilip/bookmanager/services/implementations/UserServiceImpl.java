package com.github.skowronskifilip.bookmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.github.skowronskifilip.bookmanager.dto.UserLoginPasswordDTO;
import com.github.skowronskifilip.bookmanager.exceptions.UsernameAlreadyTakenException;
import com.github.skowronskifilip.bookmanager.exceptions.UserNotFoundException;
import com.github.skowronskifilip.bookmanager.models.database.User;
import com.github.skowronskifilip.bookmanager.repositories.UserRepository;
import com.github.skowronskifilip.bookmanager.services.JwtService;
import com.github.skowronskifilip.bookmanager.services.UserService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public void registerUser(UserLoginPasswordDTO userLoginPasswordDTO) throws UsernameAlreadyTakenException {
        if (userRepo.existsByUsername(userLoginPasswordDTO.getUsername())) {
            throw new UsernameAlreadyTakenException("Username already taken");
        }

        User newUser = new User();
        newUser.setUsername(userLoginPasswordDTO.getUsername());
        newUser.setPassword(encoder.encode(userLoginPasswordDTO.getPassword()));
        newUser.setAdmin(false);
        newUser.setCreated(new Timestamp(System.currentTimeMillis()));
        userRepo.save(newUser);
    }

    @Override
    public String verifyUser(UserLoginPasswordDTO userLoginPasswordDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginPasswordDTO.getUsername(), userLoginPasswordDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userLoginPasswordDTO.getUsername());
        }
        else {
            return "Authorization failed (token error)";
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public void deleteUser(int id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public int getUserIdByUsername(String currentUserName) {
        return userRepo.findByUsername(currentUserName).getId();
    }
}