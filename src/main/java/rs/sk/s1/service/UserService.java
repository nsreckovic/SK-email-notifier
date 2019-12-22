package rs.sk.s1.service;

import rs.sk.s1.domain.dto.LoginDto;
import rs.sk.s1.domain.dto.UserRequestDto;
import rs.sk.s1.domain.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto requestUser);

    List<UserResponseDto> findAll();

    UserResponseDto login(LoginDto loginDto);

}
