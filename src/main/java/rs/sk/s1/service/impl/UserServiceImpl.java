package rs.sk.s1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.sk.s1.dao.UserDao;
import rs.sk.s1.domain.User;
import rs.sk.s1.domain.dto.UserRequestDto;
import rs.sk.s1.domain.dto.UserResponseDto;
import rs.sk.s1.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserResponseDto saveUser(UserRequestDto requestUser) {

        User user = new User();
            user.setEmail(requestUser.getEmail());
            user.setPassword(requestUser.getPassword());
            user.setName(requestUser.getName());
            user.setSurname(requestUser.getSurname());

        userDao.save(user);

        return new UserResponseDto(user);
    }

    @Override
    public User logIn(String username, String password) {
        return null;
    }

}
