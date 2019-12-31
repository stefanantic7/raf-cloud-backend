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
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

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
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = this.userRepository.findByUsername(username);
        this.setUser(user);

        return user;
    }


}
