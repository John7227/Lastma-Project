package com.lastma.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class RegisterDriverRequest {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;

}
