package com.expenseManagementSystem.controller;

import com.expenseManagementSystem.Model.Expense;
import com.expenseManagementSystem.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class EmsController {
    @GetMapping("/ems")
    public String testing1(){

return "I am connected";
    }


    @GetMapping("/ems/getExpenses")
    public List<Expense> testing(){
        User user = new User();
        user.userName="Amulya";
        user.designation="Software Engineer";
        Expense expense=new Expense();
        expense.expenseType="part time";
        expense.user=user;
        expense.amount=1000;
        User u2=new User();

        u2.designation="Student";
        u2.userName="Anusha";
        Expense exp2=new Expense();
        exp2.amount=2000;
        exp2.expenseType="rent";
        exp2.user=u2;
        List<Expense> list=new ArrayList<>();
        list.add(expense);
        list.add(exp2);


        return list;

    }
}
