package rs.sk.s1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.sk.s1.domain.User;

@Data
public class UserResponseDto {

    private String email;

    private String name;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.name  = user.getName();
    }

}
