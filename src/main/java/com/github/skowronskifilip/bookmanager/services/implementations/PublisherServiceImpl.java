package com.github.skowronskifilip.bookmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.skowronskifilip.bookmanager.models.database.Publisher;
import com.github.skowronskifilip.bookmanager.repositories.PublisherRepository;
import com.github.skowronskifilip.bookmanager.services.PublisherService;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public void addPublisher(String name) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setBooks(null);
        publisherRepository.save(publisher);
    }
}
