package org.hae.tasklogue.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserSignUp {
    private String userName;
    private String displayName;
    private String email;
    private String bio;
    private String photoUrl;
    private String password;
}
