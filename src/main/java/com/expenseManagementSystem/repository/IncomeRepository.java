package com.expenseManagementSystem.repository;

import com.expenseManagementSystem.Model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IncomeRepository extends JpaRepository<Income,Integer> {
}
