package com.expenseManagementSystem.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Income {

    @Id
    public int incomeId;

    public String incomeType;

    @ManyToOne
    public User user;
}
