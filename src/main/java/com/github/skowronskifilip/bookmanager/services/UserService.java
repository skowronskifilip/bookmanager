package com.github.skowronskifilip.bookmanager.services;

import com.github.skowronskifilip.bookmanager.dto.UserLoginPasswordDTO;
import com.github.skowronskifilip.bookmanager.exceptions.UsernameAlreadyTakenException;
import com.github.skowronskifilip.bookmanager.models.database.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(UserLoginPasswordDTO userLoginPasswordDTO) throws UsernameAlreadyTakenException;
    String verifyUser(UserLoginPasswordDTO userLoginPasswordDTO);

    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    void deleteUser(int id);

    int getUserIdByUsername(String currentUserName);
}