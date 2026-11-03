package com.lastma.services;

import com.lastma.data.models.Offence;
import com.lastma.data.repositories.OffenceRepository;
import com.lastma.dtos.requests.AddOffenceRequest;
import com.lastma.dtos.response.AddOffenceResponse;
import com.lastma.exceptions.DuplicateOffenceCodeException;
import com.lastma.exceptions.OffenceException;
import com.lastma.exceptions.OfficerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lastma.utils.OffenceMapper.map;

@Service
public class OffenceServiceImpl implements OffenceService {

    @Autowired
    private OffenceRepository offenceRepository;

    @Override
    public AddOffenceResponse addOffence(AddOffenceRequest request) {
        if(offenceRepository.existsByCode(request.getCode())) {
            throw new DuplicateOffenceCodeException("Offence code already exists");
        }

        Offence newOffence = map(request);
        newOffence.setActive(true);

        Offence savedOffence = offenceRepository.save(newOffence);

        return map(savedOffence);
    }
}
