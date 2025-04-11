package com.github.skowronskifilip.bookmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.github.skowronskifilip.bookmanager.models.database.Publisher;
import com.github.skowronskifilip.bookmanager.services.PublisherService;

import java.util.List;

@RestController
@RequestMapping("api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/")
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }


    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public ResponseEntity<String> addPublisher(@RequestParam String name) {
        publisherService.addPublisher(name);
        return ResponseEntity.ok("Success");
    }

}
