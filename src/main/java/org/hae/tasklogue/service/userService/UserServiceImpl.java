package org.hae.tasklogue.service.userService;

import org.hae.tasklogue.dto.mappers.ApplicationUserDataDtoMapper;
import org.hae.tasklogue.dto.requestdto.ApplicationUserSignUp;
import org.hae.tasklogue.dto.responsedto.CreatedUserResponseDto;
import org.hae.tasklogue.entity.ApplicationUser;
import org.hae.tasklogue.exceptions.AccountExist;
import org.hae.tasklogue.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public CreatedUserResponseDto createUser(ApplicationUserSignUp applicationUserSignUp) {
        Optional<ApplicationUser> findUser = applicationUserRepository.findApplicationUserByUserNameOrEmail(applicationUserSignUp.getUserName(), applicationUserSignUp.getEmail());
        if (findUser.isPresent()) {
            throw new AccountExist("An account with this username already exists");
        }
        ApplicationUser user = new ApplicationUser();
        user.setUserName(applicationUserSignUp.getUserName());
        user.setEmail(applicationUserSignUp.getEmail());
        user.setDisplayName(applicationUserSignUp.getDisplayName());
        user.setBio(applicationUserSignUp.getBio());
        user.setPhotoUrl(applicationUserSignUp.getPhotoUrl());
        user.setSecretPassword(applicationUserSignUp.getPassword());
        ApplicationUserDataDtoMapper applicationUserDataDtoMapper = new ApplicationUserDataDtoMapper();
        CreatedUserResponseDto createdUserResponseDto = new CreatedUserResponseDto();
        createdUserResponseDto.setMessage("user successfully registered");

        createdUserResponseDto.setSignUpDto(applicationUserDataDtoMapper.mapUserToDto(user));


        return createdUserResponseDto;
    }

}
