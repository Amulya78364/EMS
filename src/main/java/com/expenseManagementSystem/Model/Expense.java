package com.expenseManagementSystem.Model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int expenseId;

    public String expenseType;

    public int amount;

    @ManyToOne(cascade = {CascadeType.ALL})
    @NotFound(action = NotFoundAction.IGNORE)
    public User user;






}
