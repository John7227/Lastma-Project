package com.lastma.services;

import com.lastma.data.repositories.OfficerRepository;
import com.lastma.dtos.requests.LoginOfficerRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.response.RegisterOfficerResponse;
import com.lastma.exceptions.DuplicateEmailException;
import com.lastma.exceptions.OfficerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OfficerServiceImplTest {

    @Autowired
    private OfficerRepository officerRepository;

    @Autowired
    private OfficerService officerService;

    @BeforeEach
    public void setUp() {
        officerRepository.deleteAll();
    }

    @Test
    public void testThatWhenIRegisterOfficer_CountIsOne() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law123");
        request.setPhoneNumber("1234567890");

        officerService.registerOfficer(request);
        assertEquals(1, officerRepository.count());

    }

    @Test
    public void testRegisterOfficerWithDuplicateEmail_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law123");
        request.setPhoneNumber("1234567890");
        officerService.registerOfficer(request);

        RegisterOfficerRequest request2 = new RegisterOfficerRequest();
        request2.setFullName("Lawrence Daniel");
        request2.setEmail("lawjoh30@gmail.com");
        request2.setPassword("Dan123");
        request2.setPhoneNumber("0987654321");

        assertThrows(DuplicateEmailException.class, ()-> officerService.registerOfficer(request2));
        assertEquals(1, officerRepository.count());
    }

    @Test
    public void testRegisterOfficerWithDifferentEmailsSucceeds_AndCountIsTwo() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law123");
        request.setPhoneNumber("1234567890");
        officerService.registerOfficer(request);

        RegisterOfficerRequest request2 = new RegisterOfficerRequest();
        request2.setFullName("Lawrence Daniel");
        request2.setEmail("lawDan20@gmail.com");
        request2.setPassword("Dan123");
        request2.setPhoneNumber("0987654321");
        officerService.registerOfficer(request2);

        assertEquals(2, officerRepository.count());

    }

    @Test
    public void testRegisterOfficerWithoutName_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("  ");
        request.setEmail("lawjoh20@gmail.com");
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(OfficerException.class, ()-> officerService.registerOfficer(request));
        assertEquals(0, officerRepository.count());
    }

    @Test
    public void testRegisterOfficerWhenNameIsNull_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName(null);
        request.setEmail("lawjoh20@gmail.com");
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(OfficerException.class, ()-> officerService.registerOfficer(request));
        assertEquals(0, officerRepository.count());
    }

    @Test
    public void testRegisterOfficerWithoutAnEmail_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setEmail(" ");
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(OfficerException.class, ()-> officerService.registerOfficer(request));
        assertEquals(0, officerRepository.count());
    }

    @Test
    public void testRegisterOfficerWhenTheEmailIsNull_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setEmail(null);
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(OfficerException.class, ()-> officerService.registerOfficer(request));
        assertEquals(0, officerRepository.count());
    }

    @Test
    public void testThatRegisterOfficerWithInvalidEmail_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setEmail("lawjoh2gmailcom");
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(OfficerException.class, ()-> officerService.registerOfficer(request));
        assertEquals(0, officerRepository.count());

    }

    @Test
    public void testRegisterOfficerWithABlankEmail_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setEmail(" ");
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(OfficerException.class, ()-> officerService.registerOfficer(request));
        assertEquals(0, officerRepository.count());
    }

    @Test
    public void testRegisterOfficer_ReturnsNameAndId() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");

        RegisterOfficerResponse response = officerService.registerOfficer(request);
        assertEquals("Lawrence John", response.getFullName());
        assertNotNull(response.getId());
    }

    @Test
    public void testFindOfficerById_ReturnsOfficer() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");
        RegisterOfficerResponse registered = officerService.registerOfficer(request);

        RegisterOfficerResponse foundOfficer = officerService.findOfficerById(registered.getId());
        assertEquals("Lawrence John", foundOfficer.getFullName());
        assertEquals(registered.getId(), foundOfficer.getId());

    }

    @Test
    public void testFindOfficerById_WhenNotFound_ThrowsException() {
        assertThrows(OfficerException.class, ()-> officerService.findOfficerById("507f1f77bcf86cd799439011"));

    }

    @Test
    public void testLoginOfficerWithCorrectEmailAndPassword_Succeeds() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");
        officerService.registerOfficer(request);

        LoginOfficerRequest loginRequest = new LoginOfficerRequest();
        loginRequest.setEmail("lawjoh30@gmail.com");
        loginRequest.setPassword("Law321");

        RegisterOfficerResponse response = officerService.loginOfficer(loginRequest);
        assertEquals("Lawrence John", response.getFullName());
        assertNotNull(response.getId());
    }

    @Test
    public void testLoginWithWrongEmail_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");
        officerService.registerOfficer(request);

        LoginOfficerRequest loginRequest = new LoginOfficerRequest();
        loginRequest.setEmail("lawjoh10@gmail.com");
        loginRequest.setPassword("Law321");

        assertThrows(OfficerException.class, ()-> officerService.loginOfficer(loginRequest));
    }

    @Test
    public void testLoginWithWrongPassword_ThrowsException() {
        assertEquals(0, officerRepository.count());
        RegisterOfficerRequest request = new RegisterOfficerRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");
        officerService.registerOfficer(request);

        LoginOfficerRequest loginRequest = new LoginOfficerRequest();
        loginRequest.setEmail("lawjoh30@gmail.com");
        loginRequest.setPassword("Law322");

        assertThrows(OfficerException.class, ()-> officerService.loginOfficer(loginRequest));
    }
}