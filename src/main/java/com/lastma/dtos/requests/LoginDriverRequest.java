package com.lastma.dtos.requests;

import lombok.Data;

@Data
public class LoginDriverRequest {

    private String email;
    private String password;
}
