package org.hae.tasklogue.service.userService;

import org.hae.tasklogue.dto.requestdto.ApplicationUserSignUp;
import org.hae.tasklogue.dto.response.Response;
import org.hae.tasklogue.entity.ApplicationUser;
import org.hae.tasklogue.exceptions.AccountExist;
import org.hae.tasklogue.exceptions.EmptyRequiredFields;
import org.hae.tasklogue.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public ResponseEntity<Response> createUser(ApplicationUserSignUp applicationUserSignUp) {
        Optional<ApplicationUser> findUser = applicationUserRepository.findApplicationUserByUserNameOrEmail(applicationUserSignUp.getUserName(), applicationUserSignUp.getEmail());
        if (findUser.isPresent()) {
            throw new AccountExist("An account with this username already exists");
        }
        if (applicationUserSignUp.getUserName().isEmpty() || applicationUserSignUp.getEmail().isEmpty() || applicationUserSignUp.getPassword().isEmpty()) {
            throw new EmptyRequiredFields("required fields are empty");
        }
        Response response = getResponse(applicationUserSignUp);

        return ResponseEntity.ok(response);
    }

    private static Response getResponse(ApplicationUserSignUp applicationUserSignUp) {
        ApplicationUser user = new ApplicationUser();
        user.setUserName(applicationUserSignUp.getUserName());
        user.setEmail(applicationUserSignUp.getEmail());
        user.setDisplayName(applicationUserSignUp.getDisplayName());
        user.setBio(applicationUserSignUp.getBio());
        user.setPhotoUrl(applicationUserSignUp.getPhotoUrl());
        user.setSecretPassword(applicationUserSignUp.getPassword());
        Response response = new Response();
        response.setStatus(HttpStatus.CREATED);
        response.setMessage(user.getUserName() + " successfully created");
        return response;
    }

}
