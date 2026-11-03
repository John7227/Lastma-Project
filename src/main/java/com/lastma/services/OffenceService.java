package com.lastma.services;

import com.lastma.dtos.requests.AddOffenceRequest;
import com.lastma.dtos.response.AddOffenceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OffenceService {

    AddOffenceResponse addOffence(AddOffenceRequest request);
    AddOffenceResponse findOffenceByCode(String code);
    List<AddOffenceResponse> findAllOffences();
    AddOffenceResponse deactivateOffence(String code);
    AddOffenceResponse activateOffence(String code);
}
