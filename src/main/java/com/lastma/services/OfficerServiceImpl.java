package com.lastma.services;

import com.lastma.data.models.Officer;
import com.lastma.data.repositories.OfficerRepository;
import com.lastma.dtos.requests.LoginOfficerRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.response.RegisterOfficerResponse;
import com.lastma.exceptions.DuplicateEmailException;
import com.lastma.exceptions.OfficerException;
import com.lastma.validator.OfficerRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastma.utils.OfficerMapper.map;

@Service
public class OfficerServiceImpl implements OfficerService{

    @Autowired
    private OfficerRepository officerRepository;

    @Override
    public RegisterOfficerResponse registerOfficer(RegisterOfficerRequest request) {

        OfficerRequestValidator.validator(request);
        if(officerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email is already registered");
        }

        Officer newOfficer = map(request);
        Officer savedOfficer = officerRepository.save(newOfficer);
        return map(savedOfficer);
    }

    @Override
    public RegisterOfficerResponse findOfficerById(String id) {
        Optional<Officer> result = officerRepository.findById(id);

        if(result.isEmpty()) {
            throw new OfficerException("Officer not found");
        }

        Officer officer = result.get();

        return map(officer);
    }

    @Override
    public RegisterOfficerResponse loginOfficer(LoginOfficerRequest request) {
        Officer officer = officerRepository.findByEmail(request.getEmail());
        if(officer == null || !officer.getPassword().equals(request.getPassword())) {
            throw new OfficerException("Invalid email or password");
        }
        return map(officer);
    }
}
