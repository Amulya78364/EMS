package com.expenseManagementSystem.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    public int userId;

    public String userName;

    public String designation;
}
