package com.github.skowronskifilip.bookmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.skowronskifilip.bookmanager.models.database.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
