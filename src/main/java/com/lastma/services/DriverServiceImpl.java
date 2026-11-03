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

import java.util.Optional;

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
        Optional<Driver> result = driverRepository.findByEmail(email);

        if(result.isEmpty()) {
            throw new DriverException("Driver not found");
        }

        Driver driver = result.get();

        return map(driver);
    }

    @Override
    public RegisterDriverResponse loginDriver(LoginDriverRequest request) {
        Optional <Driver> result = driverRepository.findByEmail(request.getEmail());

        if(result.isEmpty()) {
            throw new DriverException("Invalid email or password");
        }

        Driver driver = result.get();

        if(request.getPassword() == null || !request.getPassword().equals(driver.getPassword())) {
            throw new DriverException("Invalid email or password");
        }
        return map(driver);
    }

}
