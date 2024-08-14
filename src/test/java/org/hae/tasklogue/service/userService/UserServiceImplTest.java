package org.hae.tasklogue.service.userService;

import org.hae.tasklogue.dto.requestdto.ApplicationUserSignUp;
import org.hae.tasklogue.dto.responsedto.CreatedUserResponseDto;
import org.hae.tasklogue.entity.ApplicationUser;
import org.hae.tasklogue.exceptions.AccountExist;
import org.hae.tasklogue.repository.ApplicationUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void createUser() {
        ApplicationUserSignUp signUp = new ApplicationUserSignUp();
        signUp.setUserName("testUser");
        signUp.setDisplayName("testDisplayName");
        signUp.setBio("test user's test bio");
        signUp.setPhotoUrl("https://example.com/testuser.jpg");
        signUp.setPassword("testuserpassword");
        signUp.setEmail("testuserEmail@tasklogue.com");
        when(applicationUserRepository.findApplicationUserByUserNameOrEmail(signUp.getEmail(), signUp.getUserName())).thenReturn(Optional.empty());

        CreatedUserResponseDto response = userService.createUser(signUp);

        // Assert
        assertNotNull(response);
        assertEquals("user successfully registered", response.getMessage());
        assertNotNull(response.getSignUpDto());
        assertEquals(signUp.getUserName(), response.getSignUpDto().getUserName());
        assertEquals(signUp.getEmail(), response.getSignUpDto().getEmail());
        assertEquals(signUp.getDisplayName(), response.getSignUpDto().getDisplayName());
        assertEquals(signUp.getBio(), response.getSignUpDto().getBio());
        assertEquals(signUp.getPhotoUrl(), response.getSignUpDto().getPhotoUrl());
        verify(applicationUserRepository, times(1)).findApplicationUserByUserNameOrEmail(anyString(), anyString());
        verify(applicationUserRepository, times(1)).save(any(ApplicationUser.class));
    }

    @Test
    void createUser_AccountExists() {
        // Arrange
        ApplicationUserSignUp signUp = new ApplicationUserSignUp();
        signUp.setUserName("existinguser");
        signUp.setEmail("existing@example.com");

        when(applicationUserRepository.findApplicationUserByUserNameOrEmail(anyString(), anyString()))
                .thenReturn(Optional.of(new ApplicationUser()));

        // Act & Assert
        assertThrows(AccountExist.class, () -> userService.createUser(signUp));

        verify(applicationUserRepository, times(1)).findApplicationUserByUserNameOrEmail(anyString(), anyString());
        verify(applicationUserRepository, never()).save(any(ApplicationUser.class));
    }
}
