package com.expenseManagementSystem.dto;

import org.springframework.stereotype.Component;

public class ExpenseDTO {

    private String expenseType;

    private int amount;

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public int getAmount() {
        return amount;
    }
}
