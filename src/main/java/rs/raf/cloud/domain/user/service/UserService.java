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
        String email = createUserRequest.getEmail();
        if (this.userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("The given email is already taken.");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(createUserRequest.getPassword());
        String firstName = createUserRequest.getFirstName();
        String lastName = createUserRequest.getLastName();

        return this.userRepository.save(new User(email, encodedPassword, firstName, lastName));
    }
}
