package org.hae.tasklogue.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hae.tasklogue.entity.Task;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String displayName;
    private String userName;
    private String bio;
    private String photoUrl;
    private Set<Task> tasks;
    private String email;
}
