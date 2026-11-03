package com.lastma.services;

import com.lastma.data.repositories.DriverRepository;
import com.lastma.dtos.requests.LoginDriverRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.response.RegisterDriverResponse;
import com.lastma.exceptions.DriverException;
import com.lastma.exceptions.DuplicateEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class  DriverServiceImplTest {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverService driverService;

    @BeforeEach
    public void setUp() {
        driverRepository.deleteAll();
    }

    @Test
    public void testThatWhenIRegisterDriver_CountIsOne() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law123");
        request.setPhoneNumber("1234567890");

        driverService.registerDriver(request);
        assertEquals(1, driverRepository.count());

    }

    @Test
    public void testRegisterDriverWithDuplicateEmail_ThrowsException() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law123");
        request.setPhoneNumber("1234567890");
        driverService.registerDriver(request);

        RegisterDriverRequest request2 = new RegisterDriverRequest();
        request2.setFullName("Lawrence Daniel");
        request2.setEmail("lawjoh30@gmail.com");
        request2.setPassword("Dan321");
        request2.setPhoneNumber("0987654321");

        assertThrows(DuplicateEmailException.class, ()-> driverService.registerDriver(request2));
        assertEquals(1, driverRepository.count());
    }

    @Test
    public void testRegisterDriverWithDifferentEmailsSucceeds_AndCountIsTwo() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law123");
        request.setPhoneNumber("1234567890");
        driverService.registerDriver(request);

        RegisterDriverRequest request2 = new RegisterDriverRequest();
        request2.setFullName("Lawrence Daniel");
        request2.setEmail("lawDan20@gmail.com");
        request2.setPassword("Dan321");
        request2.setPhoneNumber("0987654321");
        driverService.registerDriver(request2);

        assertEquals(2, driverRepository.count());
    }

    @Test
    public void testRegisterDriverWithoutName_ThrowsException() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("  ");
        request.setEmail("lawjoh20@gmail.com");
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(DriverException.class, ()-> driverService.registerDriver(request));
        assertEquals(0, driverRepository.count());
    }

    @Test
    public void testRegisterDriverWithoutAnEmail_ThrowsException() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setEmail(null);
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(DriverException.class, ()-> driverService.registerDriver(request));
        assertEquals(0, driverRepository.count());
    }

    @Test
    public void testThatRegisterDriverWithInvalidEmail_ThrowsException() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setEmail("lawjoh2gmailcom");
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(DriverException.class, ()-> driverService.registerDriver(request));
        assertEquals(0, driverRepository.count());

    }

    @Test
    public void testRegisterDriverWithABlankEmail_ThrowsException() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setEmail(" ");
        request.setPassword("Law321");
        request.setPhoneNumber("1234567890");

        assertThrows(DriverException.class, ()-> driverService.registerDriver(request));
        assertEquals(0, driverRepository.count());
    }

    @Test
    public void testRegisterDriver_ReturnsNameAndId() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");

        RegisterDriverResponse response = driverService.registerDriver(request);
        assertEquals("Lawrence John", response.getFullName());
        assertNotNull(response.getId());
    }

    @Test
    public void testFindDriverByEmail_ReturnsDriver() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");
        RegisterDriverResponse registered = driverService.registerDriver(request);

        RegisterDriverResponse found =  driverService.findDriverByEmail("lawjoh30@gmail.com");

        assertEquals("Lawrence John", found.getFullName());
        assertEquals(registered.getId(), found.getId());
    }

    @Test
    public void testFindDriverByEmail_WhenNotFound_ThrowsException() {
        assertThrows(DriverException.class, ()-> driverService.findDriverByEmail("lawjoh30@gmail.com"));

    }

    @Test
    public void testLoginDriverWithCorrectEmailAndPassword_Succeeds() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");
        driverService.registerDriver(request);

        LoginDriverRequest loginRequest = new LoginDriverRequest();
        loginRequest.setEmail("lawjoh30@gmail.com");
        loginRequest.setPassword("Law321");

        RegisterDriverResponse response = driverService.loginDriver(loginRequest);
        assertEquals("Lawrence John", response.getFullName());
        assertNotNull(response.getId());
    }

    @Test
    public void testLoginWithWrongEmail_ThrowsException() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");
        driverService.registerDriver(request);

        LoginDriverRequest loginRequest = new LoginDriverRequest();
        loginRequest.setEmail("lawjoh10@gmail.com");
        loginRequest.setPassword("Law321");

        assertThrows(DriverException.class, ()-> driverService.loginDriver(loginRequest));
    }

    @Test
    public void testLoginWithWrongPassword_ThrowsException() {
        assertEquals(0, driverRepository.count());
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setFullName("Lawrence John");
        request.setPhoneNumber("0123456789");
        request.setEmail("lawjoh30@gmail.com");
        request.setPassword("Law321");
        driverService.registerDriver(request);

        LoginDriverRequest loginRequest = new LoginDriverRequest();
        loginRequest.setEmail("lawjoh30@gmail.com");
        loginRequest.setPassword("Law322");

        assertThrows(DriverException.class, ()-> driverService.loginDriver(loginRequest));
    }
}