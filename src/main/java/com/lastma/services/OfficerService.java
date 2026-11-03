package com.lastma.services;

import com.lastma.dtos.requests.LoginDriverRequest;
import com.lastma.dtos.requests.LoginOfficerRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.response.RegisterOfficerResponse;
import org.springframework.stereotype.Service;

@Service
public interface OfficerService {

    RegisterOfficerResponse registerOfficer(RegisterOfficerRequest request);
    RegisterOfficerResponse findOfficerById(String Id);
    RegisterOfficerResponse loginOfficer(LoginOfficerRequest request);
}
