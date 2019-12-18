package rs.sk.s1.service;

import rs.sk.s1.domain.User;
import rs.sk.s1.domain.dto.UserRequestDto;
import rs.sk.s1.domain.dto.UserResponseDto;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto requestUser);

    User logIn(String username, String password);

}
