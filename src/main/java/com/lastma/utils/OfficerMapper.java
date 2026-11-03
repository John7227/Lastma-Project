package com.lastma.utils;

import com.lastma.data.models.Officer;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.response.RegisterOfficerResponse;

public class OfficerMapper {

    public static Officer map(RegisterOfficerRequest request) {
        Officer newOfficer = new Officer();
        newOfficer.setFullName(request.getFullName());
        newOfficer.setEmail(request.getEmail());
        newOfficer.setPassword(request.getPassword());
        newOfficer.setPassword(request.getPassword());
        return newOfficer;
    }

    public static RegisterOfficerResponse map(Officer officer) {
        RegisterOfficerResponse response = new RegisterOfficerResponse();
        response.setId(officer.getId());
        response.setFullName(officer.getFullName());
        return response;
    }
}
