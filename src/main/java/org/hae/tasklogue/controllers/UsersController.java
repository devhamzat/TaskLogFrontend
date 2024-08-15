package org.hae.tasklogue.controllers;

import lombok.AllArgsConstructor;
import org.hae.tasklogue.dto.requestdto.ApplicationUserSignUp;
import org.hae.tasklogue.dto.response.Response;
import org.hae.tasklogue.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tasklogue/user")
public class UsersController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/create_account")
    public ResponseEntity<Response> createAccount(@RequestBody ApplicationUserSignUp applicationUserSignUp) {
        ResponseEntity<Response> response = userService.createUser(applicationUserSignUp);
        return response;
    }

}
