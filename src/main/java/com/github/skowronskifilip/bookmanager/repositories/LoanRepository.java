package com.github.skowronskifilip.bookmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.skowronskifilip.bookmanager.models.database.Loan;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByUserId(int userId);
    Loan findByUserIdAndBookId(int userId, int bookId);
    void deleteByBookId(int bookId);
}