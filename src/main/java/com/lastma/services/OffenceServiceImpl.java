package com.lastma.services;

import com.lastma.data.models.Offence;
import com.lastma.data.repositories.OffenceRepository;
import com.lastma.dtos.requests.AddOffenceRequest;
import com.lastma.dtos.response.AddOffenceResponse;
import com.lastma.exceptions.DuplicateOffenceCodeException;
import com.lastma.exceptions.OffenceException;
import com.lastma.validator.OffenceRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lastma.utils.OffenceMapper.map;

@Service
public class OffenceServiceImpl implements OffenceService {

    @Autowired
    private OffenceRepository offenceRepository;

    @Override
    public AddOffenceResponse addOffence(AddOffenceRequest request) {

        OffenceRequestValidator.validator(request);
        if(offenceRepository.existsByCode(request.getCode())) {
            throw new DuplicateOffenceCodeException("Offence code already exists");
        }

        Offence newOffence = map(request);
        newOffence.setActive(true);

        Offence savedOffence = offenceRepository.save(newOffence);

        return map(savedOffence);
    }

    @Override
    public AddOffenceResponse findOffenceByCode(String code) {
        Optional<Offence> result = offenceRepository.findByCode(code);

        if(result.isEmpty()) {
            throw new OffenceException("Offence not found");
        }
        Offence offence = result.get();
        return map(offence);
    }

    @Override
    public List<AddOffenceResponse> findAllOffences() {
        List<Offence> offences = offenceRepository.findAll();
        List<AddOffenceResponse> responses = new ArrayList<>();

        for(Offence offence: offences) {
            responses.add(map(offence));
        }
        return responses;
    }
}
