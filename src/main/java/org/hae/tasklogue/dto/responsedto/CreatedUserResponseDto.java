package org.hae.tasklogue.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedUserResponseDto {
    private String message;
    private SignUpDto signUpDto;

}
