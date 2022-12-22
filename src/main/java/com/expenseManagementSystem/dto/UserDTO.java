package com.expenseManagementSystem.dto;

import org.springframework.stereotype.Component;

public class UserDTO {

    private String emailId;

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

}
