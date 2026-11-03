package com.lastma.services;

import com.lastma.data.repositories.OffenceRepository;
import com.lastma.dtos.requests.AddOffenceRequest;
import com.lastma.dtos.response.AddOffenceResponse;
import com.lastma.exceptions.DuplicateOffenceCodeException;
import com.lastma.exceptions.OffenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OffenceServiceImplTest {

    @Autowired
    private OffenceService offenceService;

    @Autowired
    private OffenceRepository offenceRepository;

    @BeforeEach
    public void setUp() {
        offenceRepository.deleteAll();
    }

    @Test
    public void testAddOffence_SavesOffence() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode("JOH");
        request.setName("Traffic Control Violation");
        request.setDescription("Disobeying traffic control personnel");
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");

        AddOffenceResponse savedOffence = offenceService.addOffence(request);

        assertNotNull(savedOffence.getId());
        assertEquals("JOH", savedOffence.getCode());
        assertEquals("Traffic Control Violation", savedOffence.getName());
        assertEquals(20000, savedOffence.getFirstOffenceFine());
        assertTrue(savedOffence.isActive());
        assertEquals(1, offenceRepository.count());
    }

    @Test
    public void testAddOffence_duplicateCode_throwsException() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode("JOH");
        request.setName("Traffic Control Violation");
        request.setDescription("Disobeying traffic control personnel");
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");
        offenceService.addOffence(request);

        AddOffenceRequest duplicate = new AddOffenceRequest();
        duplicate.setCode("JOH");
        duplicate.setName("Traffic Control Violation");

        assertThrows(DuplicateOffenceCodeException.class, ()-> offenceService.addOffence(duplicate));

        assertEquals(1, offenceRepository.count());
    }

    @Test
    public void testAddOffenceWithDifferentCodesSucceeds_AndCountIsTwo() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode("JOH");
        request.setName("Traffic Control Violation");
        request.setDescription("Disobeying traffic control personnel");
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");
        offenceService.addOffence(request);

        assertEquals(1, offenceRepository.count());
        AddOffenceRequest requests = new AddOffenceRequest();
        requests.setCode("DAN");
        requests.setName("Illegal U-turn");
        requests.setDescription("Illegal U-turn");
        requests.setPenaltyPoints(3);
        requests.setFirstOffenceFine(20000);
        requests.setSubsequentOffenceFine(30000);
        requests.setAdditionalPenaltyDescription("Driver training");
        offenceService.addOffence(requests);

        assertEquals(2, offenceRepository.count());
    }

    @Test
    public void testAddOffenceWithoutACode_ThrowsException() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode(" ");
        request.setName("Traffic Control Violation");
        request.setDescription("Disobeying traffic control personnel");
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");

        assertThrows(OffenceException.class, ()-> offenceService.addOffence(request));
        assertEquals(0, offenceRepository.count());
    }

    @Test
    public void testAddOffenceWhenCodeIsNull_ThrowsException() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode(null);
        request.setName("Traffic Control Violation");
        request.setDescription("Disobeying traffic control personnel");
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");

        assertThrows(OffenceException.class, ()-> offenceService.addOffence(request));
        assertEquals(0, offenceRepository.count());
    }

}