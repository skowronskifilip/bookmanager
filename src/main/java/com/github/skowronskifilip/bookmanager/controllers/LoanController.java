package com.github.skowronskifilip.bookmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.github.skowronskifilip.bookmanager.dto.LoanInformationDTO;
import com.github.skowronskifilip.bookmanager.services.LoanService;
import com.github.skowronskifilip.bookmanager.services.UserService;

import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @Secured("ROLE_USER")
    @PostMapping("/rent/{bookId}")
    public ResponseEntity<String> rentBook(@PathVariable int bookId, Authentication authentication) {
        try {
            String username = authentication.getName();
            int userId = userService.getUserIdByUsername(username);
            loanService.loan(userId, bookId);
            return ResponseEntity.ok("Success " + username + " borrowed " + bookId);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/rent/{bookId}")
    public ResponseEntity<String> cancelLoan(@PathVariable int bookId, Authentication authentication) {
        try {
            String username = authentication.getName();
            int userId = userService.getUserIdByUsername(username);
            loanService.cancel(userId, bookId);
            return ResponseEntity.ok("Success " + username + " cancelled borrowing " + bookId);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<LoanInformationDTO> getLoanInfoById(@PathVariable int id) {
        try {
            LoanInformationDTO loan = loanService.getLoanInfo(id);
            return ResponseEntity.ok(loan);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("{userId}")
    public ResponseEntity<List<LoanInformationDTO>> getLoansByUserId(@PathVariable int userId) {
        try {
            List<LoanInformationDTO> loans = loanService.getAllLoansByUserId(userId);
            return ResponseEntity.ok(loans);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Secured("ROLE_USER")
    @GetMapping()
    public ResponseEntity<List<LoanInformationDTO>> getLoansForCurrentUser(Authentication authentication) {
        try {
            String currentUserName = authentication.getName();
            int currentUserId = userService.getUserIdByUsername(currentUserName);

            List<LoanInformationDTO> loans = loanService.getAllLoansByUserId(currentUserId);
            return ResponseEntity.ok(loans);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}