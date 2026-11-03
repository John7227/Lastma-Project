package com.lastma.dtos.requests;

import lombok.Data;

@Data
public class RegisterOfficerRequest {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
}
