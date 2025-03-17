package com.anger.FinancialTracker.repository;

import com.anger.FinancialTracker.repository.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "expenses")
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByAccount_id(@Param("accountId") Long accountId);
}
