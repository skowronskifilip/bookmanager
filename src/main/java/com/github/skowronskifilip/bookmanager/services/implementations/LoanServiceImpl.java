package com.github.skowronskifilip.bookmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.skowronskifilip.bookmanager.dto.LoanInformationDTO;
import com.github.skowronskifilip.bookmanager.exceptions.BookNotAvailableException;
import com.github.skowronskifilip.bookmanager.exceptions.LoansNotFoundedException;
import com.github.skowronskifilip.bookmanager.models.database.Book;
import com.github.skowronskifilip.bookmanager.models.database.Loan;
import com.github.skowronskifilip.bookmanager.models.database.User;
import com.github.skowronskifilip.bookmanager.repositories.BookRepository;
import com.github.skowronskifilip.bookmanager.repositories.LoanRepository;
import com.github.skowronskifilip.bookmanager.repositories.UserRepository;
import com.github.skowronskifilip.bookmanager.services.LoanService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private  BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void loan(int userId, int bookId) throws Exception {
        User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found"));
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new Exception("Book not found"));
        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is not available for borrowing");
        }
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        loan.setLoan(now);

        LocalDateTime localDateTime = now.toLocalDateTime();
        LocalDateTime newDateTime = localDateTime.plusDays(30);
        Timestamp newTimestamp = Timestamp.valueOf(newDateTime);
        loan.setReturnDate(newTimestamp);

        book.setAvailable(false);
        bookRepo.save(book);

        loanRepo.save(loan);
    }

    @Override
    public void cancel(int userId, int bookId) throws Exception {
        Loan loan = loanRepo.findByUserIdAndBookId(userId, bookId);
        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepo.save(book);
        loanRepo.delete(loan);
    }

    @Override
    public LoanInformationDTO getLoanInfo(int loanId) throws Exception {
        LoanInformationDTO response = new LoanInformationDTO();
        Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new Exception("Loan not found"));

        response.setLoanId(loan.getId());
        response.setBookId(loan.getBook().getId());
        response.setUsername(loan.getUser().getUsername());
        response.setBookName(loan.getBook().getTitle());
        response.setLoanDate(loan.getLoan());
        response.setReturnDate(loan.getReturnDate());

        return response;
    }

    @Override
    public List<LoanInformationDTO> getAllLoansByUserId(int userId) {
        List<Loan> loans = loanRepo.findByUserId(userId);
        if (loans.isEmpty()) {
            throw new LoansNotFoundedException("No loans found for the user with id " + userId);
        }

        List<LoanInformationDTO> responses = new ArrayList<>();
        for (Loan loan : loans) {
            LoanInformationDTO response = new LoanInformationDTO();
            response.setLoanId(loan.getId());
            response.setBookId(loan.getBook().getId());
            response.setUsername(loan.getUser().getUsername());
            response.setBookName(loan.getBook().getTitle());
            response.setLoanDate(loan.getLoan());
            response.setReturnDate(loan.getReturnDate());
            responses.add(response);
        }

        return responses;
    }
}