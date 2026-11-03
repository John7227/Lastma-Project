package com.lastma.services;

import com.lastma.data.models.Offence;
import com.lastma.dtos.requests.AddOffenceRequest;
import com.lastma.dtos.response.AddOffenceResponse;
import org.springframework.stereotype.Service;

@Service
public interface OffenceService {

    AddOffenceResponse addOffence(AddOffenceRequest request);
}
