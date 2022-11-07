package com.expenseManagementSystem.controller;

import com.expenseManagementSystem.Model.Expense;
import com.expenseManagementSystem.Model.User;
import com.expenseManagementSystem.repository.ExpenseRepository;
import com.expenseManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class EmsController {

    @Autowired
    ExpenseRepository expenseRepo;

    @Autowired
    ExpenseRepository expRepo;

    @GetMapping("/ems")
    public String testing1(){
        return "I am connected";
    }


    @GetMapping("/ems/getExpenses")
    public List<Expense> testing(){



        User user = new User();
        user.userId=1;
        user.userName="Amulya";
        user.designation="Software Engineer";
        Expense expense=new Expense();
        expense.expenseId=1;
        expense.expenseType="part time";
        expense.user=user;
        expense.amount=1000;


        Expense exp2=new Expense();
        exp2.expenseId=2;
        exp2.amount=2000;
        exp2.expenseType="rent";
        exp2.user=user;
        List<Expense> list=new ArrayList<>();
        list.add(expense);
        list.add(exp2);

        return expenseRepo.saveAll(list);

    }

    @GetMapping("expensedetails/{id}")
    public Expense getUserDetails(@PathVariable int id){
        return expRepo.findById(id).get();
    }
}
