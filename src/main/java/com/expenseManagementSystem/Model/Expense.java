package com.expenseManagementSystem.Model;

import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Component
public class Expense {

    @Id
    public int expenseId;

    public String expenseType;

    public int amount;

    @ManyToOne
    public User user;






}
