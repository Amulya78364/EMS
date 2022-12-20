package com.expenseManagementSystem.controller;

import com.expenseManagementSystem.Model.Expense;
import com.expenseManagementSystem.Model.User;
import com.expenseManagementSystem.repository.ExpenseRepository;
import com.expenseManagementSystem.repository.UserRepository;
import com.nimbusds.oauth2.sdk.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/webapi")
@CrossOrigin(origins="http://localhost:4200")
public class EmsController {

    @Autowired
    ExpenseRepository expenseRepo;

    @Autowired
    UserRepository userRepo;

    static String uname="";

    String uname1="";


    @GetMapping("/ems")
    public String testing1(){
        return "I am connected";
    }

    @PostMapping("ems/postExpenses")
    public Expense postExpenses(@RequestBody Expense exp){
        User user1=new User();
        List<User> data = userRepo.findAll();
        for(User u:data){
            String name=u.userName;
            if(name.equals(uname)){
                user1 = u;
            }
            exp.user=user1;
        }
        return expenseRepo.save(exp);
//       return exp;
    }

    @PostMapping("/ems/postUsername")
    public String postUserName(@RequestBody User username) throws ParseException {
//        JSONUtils.parseJSON(uname1);
        uname1=""+username.userName;
        return uname1;
    }

    @PostMapping("/ems/postUser")
    public User postUser(@RequestBody User user){
        uname=user.userName;
//         return "User Details are Submitted";
        User u = userRepo.save(user);
        return u;
    }


    @GetMapping("/ems/getExpenses")
    public List<Expense> getExpenses(){
        List<Expense> result=new ArrayList<>();
        List<User> data = userRepo.findAll();
        int id=0;
        for(User u:data) {
            String name = u.userName;
            if (name.equals(uname)) {
                id = u.userId;
                break;
            }
        }
        List<Expense> exp = expenseRepo.findAll();
        for(Expense e:exp){
            if(e.user.userId==id){
                result.add(e);
            }
        }
        return result;
    }

    @GetMapping("expensedetails/{id}")
    public Expense getUserDetails(@PathVariable int id, Principal principal){

        return expenseRepo.findById(id).get();
    }

    @GetMapping("/ems/getAllExpenses")
    public List<Expense> getAllExpenses(){
        List<Expense> list = new ArrayList<>();
        if(uname.equals("admin"))
            return expenseRepo.findAll();
        return list;
    }
}
