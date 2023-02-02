package fr.ulco.springshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String name;
    private String firstname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled;
}
