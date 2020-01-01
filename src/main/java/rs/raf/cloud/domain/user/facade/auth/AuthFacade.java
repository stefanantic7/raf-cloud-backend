package rs.raf.cloud.domain.user.facade.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rs.raf.cloud.domain.user.entity.User;
import rs.raf.cloud.domain.user.repository.UserRepository;

@Component
public class AuthFacade implements IAuthFacade {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Override
    public Long getId() {
        return this.getUser().getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        if (user != null) {
            return user;
        }
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = this.userRepository.findByEmail(email);
        this.setUser(user);

        return user;
    }


}
