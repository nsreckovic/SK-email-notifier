package rs.sk.s1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Email(message = "Email is required!")
    private String email;

    @Length(min = 4, max = 16, message = "Password must be between 4 and 16 characters long!")
    private String password;

    private String name;

    private String surname;

}
