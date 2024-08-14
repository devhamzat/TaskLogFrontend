package org.hae.tasklogue.dto.mappers;

import org.hae.tasklogue.dto.responsedto.SignUpDto;
import org.hae.tasklogue.entity.ApplicationUser;

public class ApplicationUserDataDtoMapper {
    public SignUpDto mapUserToDto(ApplicationUser user) {
        SignUpDto userDto = new SignUpDto();
        userDto.setUserName(user.getUserName());
        userDto.setDisplayName(user.getDisplayName());
        userDto.setBio(user.getBio());
        userDto.setPhotoUrl(user.getPhotoUrl());
        userDto.setEmail(user.getEmail());
//        Task task  = new Task();
//        TaskDTO taskDTO = new TaskDTO();
//        taskDTO.setTittle(task.getTaskTittle());
//        taskDTO.setTaskDetails(taskDTO.getTaskDetails());
//        taskDTO.setStatus(task.getStatus());
        userDto.setTasks(user.getUserTasks());
        return userDto;

    }
}
