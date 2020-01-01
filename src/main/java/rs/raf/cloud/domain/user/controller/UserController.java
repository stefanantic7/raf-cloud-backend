package rs.raf.cloud.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.raf.cloud.domain.user.dto.UserDto;
import rs.raf.cloud.domain.user.mapper.UserMapper;
import rs.raf.cloud.domain.user.service.UserService;
import rs.raf.cloud.domain.user.entity.User;
import rs.raf.cloud.domain.user.request.CreateUserRequest;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid CreateUserRequest createUserRequest) throws ValidationException {
        return UserMapper.instance.userToUserDto(this.userService.create(createUserRequest));
    }
}
