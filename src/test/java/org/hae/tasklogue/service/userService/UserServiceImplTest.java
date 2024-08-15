package org.hae.tasklogue.service.userService;

import org.hae.tasklogue.dto.requestdto.ApplicationUserSignUp;
import org.hae.tasklogue.dto.response.Response;
import org.hae.tasklogue.entity.ApplicationUser;
import org.hae.tasklogue.exceptions.AccountExist;
import org.hae.tasklogue.exceptions.EmptyRequiredFields;
import org.hae.tasklogue.repository.ApplicationUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_Success() {
        // Arrange
        ApplicationUserSignUp signUp = new ApplicationUserSignUp();
        signUp.setUserName("testuser");
        signUp.setEmail("test@example.com");
        signUp.setPassword("password");
        signUp.setDisplayName("Test User");
        signUp.setBio("Test bio");
        signUp.setPhotoUrl("http://example.com/photo.jpg");

        when(applicationUserRepository.findApplicationUserByUserNameOrEmail(anyString(), anyString()))
                .thenReturn(Optional.empty());

        // Act
        ResponseEntity responseEntity = userService.createUser(signUp);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof Response);
        Response response = (Response) responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertEquals("testuser successfully created", response.getMessage());
    }

    @Test
    void createUser_AccountExists() {
        // Arrange
        ApplicationUserSignUp signUp = new ApplicationUserSignUp();
        signUp.setUserName("existinguser");
        signUp.setEmail("existing@example.com");
        signUp.setPassword("password");

        when(applicationUserRepository.findApplicationUserByUserNameOrEmail(anyString(), anyString()))
                .thenReturn(Optional.of(new ApplicationUser()));

        // Act & Assert
        assertThrows(AccountExist.class, () -> userService.createUser(signUp));
    }

    @Test
    void createUser_EmptyRequiredFields() {
        // Arrange
        ApplicationUserSignUp signUp = new ApplicationUserSignUp();
        signUp.setUserName("");
        signUp.setEmail("test@example.com");
        signUp.setPassword("password");

        // Act & Assert
        assertThrows(EmptyRequiredFields.class, () -> userService.createUser(signUp));
    }
}