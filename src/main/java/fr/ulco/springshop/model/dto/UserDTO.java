package fr.ulco.springshop.model.dto;

import fr.ulco.springshop.model.bo.UserBO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;

    public UserDTO(UserBO userBO) {
        this.email = userBO.getEmail();
        this.firstName = userBO.getFirstname();
        this.lastName = userBO.getName();
    }
}
