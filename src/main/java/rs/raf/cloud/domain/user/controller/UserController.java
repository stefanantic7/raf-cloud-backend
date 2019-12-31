package rs.raf.cloud.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.raf.cloud.domain.user.service.UserService;
import rs.raf.cloud.domain.user.entity.User;
import rs.raf.cloud.domain.user.facade.auth.IAuthFacade;
import rs.raf.cloud.domain.user.request.CreateUserRequest;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IAuthFacade authenticationFacade;

    @PostMapping
    public User create(@RequestBody @Valid CreateUserRequest createUserRequest) throws ValidationException {
        return this.userService.create(createUserRequest);
    }

    @GetMapping
    public String get() throws ValidationException {
        this.authenticationFacade.getUser();

        return "asf";
    }
}
