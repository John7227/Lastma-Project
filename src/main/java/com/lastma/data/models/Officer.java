package com.lastma.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Officer {

    private String fullName;
    private String officerNumber;
    private String email;
    @Id
    private String id;
    private String phoneNumber;
    private boolean isActive = true;
    private String password;
}
