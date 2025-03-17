package com.anger.FinancialTracker.controller;

import com.anger.FinancialTracker.dto.ExpenseDTO;
import com.anger.FinancialTracker.repository.AccountRepository;
import com.anger.FinancialTracker.repository.ExpenseRepository;
import com.anger.FinancialTracker.repository.entity.Account;
import com.anger.FinancialTracker.repository.entity.Expense;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseRepository expenseRepository;
    private final AccountRepository accountRepository;

    public ExpenseController(ExpenseRepository expenseRepository, AccountRepository accountRepository) {
        this.expenseRepository = expenseRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/account/{account_id}")
    public ResponseEntity<List<Expense>> getExpensesByAccountId(@PathVariable("account_id") Long accountId) {
        return new ResponseEntity<>(expenseRepository.findAllByAccount_id(accountId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        Account account = accountRepository.findById(expenseDTO.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        Expense expense = new Expense().toBuilder()
                                                                .title(expenseDTO.getTitle())
                                                                .amount(expenseDTO.getAmount())
                                                                .account(account)
                                                                .build();
        return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.CREATED);
    }


}