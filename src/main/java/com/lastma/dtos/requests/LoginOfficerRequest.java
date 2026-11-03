package com.lastma.dtos.requests;

import lombok.Data;

@Data
public class LoginOfficerRequest {

    private String email;
    private String password;
}
