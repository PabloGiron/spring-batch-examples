package com.example.cap63.domain;

import java.util.List;

public interface TransactionDao {
    List<Transaction> getTransactionsByAccountNumber(String accountNumber);
}
