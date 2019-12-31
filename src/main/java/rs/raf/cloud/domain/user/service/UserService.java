package rs.raf.cloud.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.raf.cloud.domain.user.repository.UserRepository;
import rs.raf.cloud.domain.user.entity.User;
import rs.raf.cloud.domain.user.request.CreateUserRequest;

import javax.xml.bind.ValidationException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(CreateUserRequest createUserRequest) throws ValidationException {
        String username = createUserRequest.getUsername();
        if (this.userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("The value is already in the list.");
//            throw new ValidationException("Username already exists");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(createUserRequest.getPassword());
        String firstName = createUserRequest.getFirstName();
        String lastName = createUserRequest.getLastName();

        return this.userRepository.save(new User(username, encodedPassword, firstName, lastName));
    }
}
