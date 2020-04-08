package rs.sk.s1.domain.dto;

import lombok.Data;
import rs.sk.s1.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserResponseDto {

    @Email
    private String email;

    @NotNull
    private String name;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.name  = user.getName();
    }

}
