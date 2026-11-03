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

import java.util.List;

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
        duplicate.setName("Illegal U-turn");
        duplicate.setDescription("Illegal U-turn");

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
        AddOffenceRequest request2 = new AddOffenceRequest();
        request2.setCode("DAN");
        request2.setName("Illegal U-turn");
        request2.setDescription("Illegal U-turn");
        request2.setPenaltyPoints(3);
        request2.setFirstOffenceFine(20000);
        request2.setSubsequentOffenceFine(30000);
        request2.setAdditionalPenaltyDescription("Driver training");
        offenceService.addOffence(request2);

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

    @Test
    public void testAddOffenceWithoutAName_ThrowsException() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode("JOH");
        request.setName(" ");
        request.setDescription("Disobeying traffic control personnel");
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");

        assertThrows(OffenceException.class, ()-> offenceService.addOffence(request));
        assertEquals(0, offenceRepository.count());
    }

    @Test
    public void testAddOffenceWhenNameIsNull_ThrowsException() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode("JOH");
        request.setName(null);
        request.setDescription("Disobeying traffic control personnel");
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");

        assertThrows(OffenceException.class, ()-> offenceService.addOffence(request));
        assertEquals(0, offenceRepository.count());
    }

    @Test
    public void testAddOffenceWithoutADescription_ThrowsException() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode("JOH");
        request.setName("Traffic Control Violation");
        request.setDescription(" ");
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");

        assertThrows(OffenceException.class, ()-> offenceService.addOffence(request));
        assertEquals(0, offenceRepository.count());
    }

    @Test
    public void testAddOffenceWhenDescriptionIsNull_ThrowsException() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest request = new AddOffenceRequest();
        request.setCode("JOH");
        request.setName("Traffic Control Violation");
        request.setDescription(null);
        request.setPenaltyPoints(2);
        request.setFirstOffenceFine(20000);
        request.setSubsequentOffenceFine(30000);
        request.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");

        assertThrows(OffenceException.class, ()-> offenceService.addOffence(request));
        assertEquals(0, offenceRepository.count());
    }

    @Test
    public void testFindOffenceByCode_ReturnsOffence() {
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

        AddOffenceResponse foundOffence = offenceService.findOffenceByCode("JOH");

        assertEquals(savedOffence.getId(), foundOffence.getId());
        assertEquals("JOH", foundOffence.getCode());
        assertEquals("Traffic Control Violation", foundOffence.getName());
        assertEquals("Disobeying traffic control personnel", foundOffence.getDescription());

    }

    @Test
    public void testFindOffenceByCode_WhenNotFound_ThrowsException() {
        assertThrows(OffenceException.class, ()-> offenceService.findOffenceByCode("JOH"));
    }

    @Test
    public void testFindAllOffences_ReturnsAllOffences() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest first = new AddOffenceRequest();
        first.setCode("JOH");
        first.setName("Traffic Control Violation");
        first.setDescription("Disobeying traffic control personnel");
        first.setPenaltyPoints(2);
        first.setFirstOffenceFine(20000);
        first.setSubsequentOffenceFine(30000);
        first.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");
        offenceService.addOffence(first);

        assertEquals(1, offenceRepository.count());
        AddOffenceRequest second = new AddOffenceRequest();
        second.setCode("DAN");
        second.setName("Illegal U-turn");
        second.setDescription("Illegal U-turn");
        second.setPenaltyPoints(3);
        second.setFirstOffenceFine(20000);
        second.setSubsequentOffenceFine(30000);
        second.setAdditionalPenaltyDescription("Driver training");
        offenceService.addOffence(second);

        List<AddOffenceResponse> offences = offenceService.findAllOffences();

        assertEquals(2, offences.size());
    }

    @Test
    public void testDeactivateOffenceSetsActiveToFalse() {
        assertEquals(0, offenceRepository.count());
        AddOffenceRequest first = new AddOffenceRequest();
        first.setCode("JOH");
        first.setName("Traffic Control Violation");
        first.setDescription("Disobeying traffic control personnel");
        first.setPenaltyPoints(2);
        first.setFirstOffenceFine(20000);
        first.setSubsequentOffenceFine(30000);
        first.setAdditionalPenaltyDescription("Vehicle impoundment and LASDRI training");
        AddOffenceResponse addedOffence = offenceService.addOffence(first);

        assertTrue(addedOffence.isActive());

        AddOffenceResponse deactivated = offenceService.deactivateOffence("JOH");

        assertFalse(deactivated.isActive());
        assertEquals(1, offenceRepository.count());
    }

}