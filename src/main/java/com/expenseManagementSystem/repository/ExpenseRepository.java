package com.expenseManagementSystem.repository;

import com.expenseManagementSystem.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {

}
