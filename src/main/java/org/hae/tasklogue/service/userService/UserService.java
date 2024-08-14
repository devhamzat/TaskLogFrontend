package org.hae.tasklogue.service.userService;

import org.hae.tasklogue.dto.requestdto.ApplicationUserSignUp;
import org.hae.tasklogue.dto.responsedto.CreatedUserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    CreatedUserResponseDto createUser(ApplicationUserSignUp applicationUserSignUp);
}
