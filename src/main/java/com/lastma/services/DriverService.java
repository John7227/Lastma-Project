package com.lastma.services;

import com.lastma.dtos.requests.LoginDriverRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.response.RegisterDriverResponse;
import org.springframework.stereotype.Service;

@Service
public interface DriverService {
    RegisterDriverResponse registerDriver(RegisterDriverRequest request);
    RegisterDriverResponse findDriverByEmail(String email);
    RegisterDriverResponse loginDriver(LoginDriverRequest request);
}
