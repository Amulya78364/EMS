package com.expenseManagementSystem.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userId;


    @Id
    public String emailId;

    @OneToMany(cascade = {CascadeType.ALL})
    @NotFound(action = NotFoundAction.IGNORE)
    public List<Expense> expense;

}
