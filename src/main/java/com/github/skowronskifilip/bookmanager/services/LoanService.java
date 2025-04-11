package com.github.skowronskifilip.bookmanager.services;

import com.github.skowronskifilip.bookmanager.dto.LoanInformationDTO;

import java.util.List;

public interface LoanService {
    void loan(int userId, int bookId) throws Exception;
    void cancel(int userId, int bookId) throws Exception;
    LoanInformationDTO getLoanInfo(int loanId) throws Exception;
    List<LoanInformationDTO> getAllLoansByUserId(int userId) throws Exception;
}
