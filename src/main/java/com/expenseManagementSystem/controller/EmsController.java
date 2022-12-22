package com.expenseManagementSystem.controller;

import com.expenseManagementSystem.Model.Expense;
import com.expenseManagementSystem.Model.User;
import com.expenseManagementSystem.dto.ExpenseDTO;
import com.expenseManagementSystem.dto.UserDTO;
import com.expenseManagementSystem.repository.ExpenseRepository;
import com.expenseManagementSystem.repository.UserRepository;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/webapi")
@CrossOrigin(origins="http://34.212.231.73/")
public class EmsController {

    @Autowired
    ExpenseRepository expenseRepo;

    @Autowired
    UserRepository userRepo;

    static String uname1;


    @GetMapping("/ems")
    public String testing1(){
        return "I am connected";
    }

    @PostMapping("ems/postExpenses")
    public ExpenseDTO postExpenses(@RequestBody Expense exp,Principal principal){
        ExpenseDTO expenseDTO = new ExpenseDTO();
        String uname = principal.getName();
        User user1=new User();
        List<User> data = userRepo.findAll();
        for(User u:data){
            String name=u.emailId;
            if(name.equals(uname)){
                user1 = u;
            }
        }
        user1.expense.add(exp);
        expenseRepo.save(exp);
        expenseDTO.setExpenseType(exp.expenseType);
        expenseDTO.setAmount(exp.amount);

        return expenseDTO;
//       return exp;
    }

    @PostMapping("ems/postAdminExpenses")
    public void postAdminExpenses(@RequestBody Expense expense){
        User user=userRepo.findByEmailId(uname1);
        Expense exp = new Expense();
        exp.expenseType=expense.expenseType;
        exp.amount=expense.amount;
        user.expense.add(exp);
        expenseRepo.save(exp);
    }

    @GetMapping("/ems/getUserName")
    public List<String> getUserName(Principal principal){
        String uname = principal.getName();
        List<User> user = userRepo.findAll();
        List<String> listnames= new ArrayList<>();
        if(uname.equals("amulya.nathala.736@my.csun.edu") || uname.equals("felix@gmail.com")){
            for(User u:user) {
                listnames.add(u.emailId);
            }
        }
        return listnames;
    }


    @GetMapping("/ems/postUser")
    public UserDTO postUser(Principal principal){
        String name = principal.getName();
        User u = new User();
        u.emailId=name;
//        List<User> user = new ArrayList<>();
//        for(User user1:user){
//            if(!(user1.equals(name))){
                 userRepo.save(u);
//        }
//        else
//            continue;
//    }
        UserDTO userDTO=new UserDTO();
        userDTO.setEmailId(u.emailId);
        return userDTO;
    }

    @PostMapping("/ems/postUsername")
    public String postUserName(@RequestBody User userName) throws ParseException {
        uname1=""+userName.emailId;
        return uname1;
    }


    @GetMapping("/ems/getExpenses")
    public List<Expense> getExpenses(Principal principal){
        String uname = principal.getName();
        User data = userRepo.findByEmailId(uname);
        List<Expense> exp = new ArrayList<>();
        for(Expense e :data.expense) {
            exp.add(e);
           }
        return exp;
    }

    @GetMapping("expensedetails/{id}")
    public Expense getUserDetails(@PathVariable int id, Principal principal){

        return expenseRepo.findById(id).get();
    }

    @GetMapping("/ems/getAllExpenses")
    public List<User> getAllExpenses(Principal principal){
        String uname= principal.getName();
        List<User> list = new ArrayList<>();
        if(uname.equals("amulya.nathala.736@my.csun.edu") || uname.equals("felix@gmail.com")) {
            return userRepo.findAll();
        }
        return list;
    }
}
