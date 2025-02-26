package rs.sk.s1.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.sk.s1.domain.dto.LoginDto;
import rs.sk.s1.domain.dto.UserRequestDto;
import rs.sk.s1.domain.dto.UserResponseDto;
import rs.sk.s1.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserCtrl {

    private final UserService userService;

    @CrossOrigin
    @PostMapping("/save")
    @ResponseBody
    public UserResponseDto save(@Validated @RequestBody UserRequestDto requestUser) {
        return userService.saveUser(requestUser);
    }

    @GetMapping("/findAll")
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public UserResponseDto login(@Validated @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

}
