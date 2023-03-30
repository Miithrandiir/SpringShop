package fr.ulco.springshop.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
    private String email;
    private String password;
    private String passwordConfirm;

    private String firstName;
    private String lastName;


}
