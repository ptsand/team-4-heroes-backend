package kea.group4.backend.dto;

import kea.group4.backend.entities.Address;
import kea.group4.backend.security.UserWithPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    private String firstName;
    private String lastName;
    private long phoneNumber;
    private Address address;

    @NotBlank
    @Size(min = UserWithPassword.USER_NAME_MIN_SIZE, max = UserWithPassword.USER_NAME_MAX_SIZE)
    private String username;

    @NotBlank
    @Size(max = UserWithPassword.EMAIL_MAX_SIZE)
    @Email
    private String email;

    @NotBlank
    @Size(min = UserWithPassword.PASSWORD_MIN_SIZE, max = UserWithPassword.PASSWORD_MAX_SIZE)
    private String password;
}
