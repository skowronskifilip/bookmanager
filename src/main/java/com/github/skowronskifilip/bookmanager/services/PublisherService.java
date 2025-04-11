package com.github.skowronskifilip.bookmanager.services;

import com.github.skowronskifilip.bookmanager.models.database.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAllPublishers();
    void addPublisher(String name);
}
