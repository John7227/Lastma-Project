package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.dtos.requests.LoginDriverRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.response.RegisterDriverResponse;
import com.lastma.exceptions.DriverException;
import com.lastma.exceptions.DuplicateEmailException;
import com.lastma.validator.DriverRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lastma.utils.DriverMapper.map;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public RegisterDriverResponse registerDriver(RegisterDriverRequest request) {

        DriverRequestValidator.validator(request);
        if(driverRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email is already registered");
        }

        Driver newDriver = map(request);
        Driver savedDriver = driverRepository.save(newDriver);

        return map(savedDriver);

    }

    @Override
    public RegisterDriverResponse findDriverByEmail(String email) {
        Driver driver = driverRepository.findByEmail(email);
        if(driver == null) {
            throw new DriverException("Driver not found");
        }

        return map(driver);
    }

    @Override
    public RegisterDriverResponse loginDriver(LoginDriverRequest request) {
        Driver driver = driverRepository.findByEmail(request.getEmail());
        if(driver == null || !driver.getPassword().equals(request.getPassword())) {
            throw new DriverException("Invalid email or password");
        }

        return map(driver);
    }

}
