package com.lastma.utils;

import com.lastma.data.models.Driver;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.response.RegisterDriverResponse;

public class DriverMapper {
    public static Driver map(RegisterDriverRequest driverRequest) {
        Driver newDriver = new Driver();
        newDriver.setFullName(driverRequest.getFullName());
        newDriver.setEmail(driverRequest.getEmail());
        newDriver.setPassword(driverRequest.getPassword());
        newDriver.setPhoneNumber(driverRequest.getPhoneNumber());
        return newDriver;

    }

    public static RegisterDriverResponse map(Driver driver) {
        RegisterDriverResponse response = new RegisterDriverResponse();
        response.setId(driver.getId());
        response.setFullName(driver.getFullName());
        return response;
    }
}
