package rs.sk.s1.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rs.sk.s1.dao.UserDao;
import rs.sk.s1.domain.User;
import rs.sk.s1.domain.dto.LoginDto;
import rs.sk.s1.domain.dto.UserRequestDto;
import rs.sk.s1.domain.dto.UserResponseDto;
import rs.sk.s1.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

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
    public List<UserResponseDto> findAll() {
        List<User> users = userDao.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(User user: users) {
            userResponseDtos.add(new UserResponseDto(user));
        }
        return userResponseDtos;
    }

    @Override
    public UserResponseDto login(LoginDto loginDto) {
        Optional<User> optUser = userDao.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

        if(optUser.isPresent()) {
            logger.info("User with email: \"" + loginDto.getEmail() + "\" exists.");
            return new UserResponseDto(optUser.get());
        } else {
            logger.warn("User with email: \"" + loginDto.getEmail() + "\" does NOT exist.");
            return null;
        }

    }

}
