package com.github.skowronskifilip.bookmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.skowronskifilip.bookmanager.models.database.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}