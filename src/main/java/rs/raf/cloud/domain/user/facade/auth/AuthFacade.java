package rs.raf.cloud.domain.user.facade.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rs.raf.cloud.domain.user.entity.User;
import rs.raf.cloud.domain.user.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFacade implements IAuthFacade {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Long getId() {
        return this.getUser().getId();
    }

    public void setUser(User user) {
        request.setAttribute("user", user);
    }

    @Override
    public User getUser() {
        User user = (User) this.request.getAttribute("user");
        if (user == null) {
            System.out.println("tttt");
            var email = SecurityContextHolder.getContext().getAuthentication().getName();
            user = this.userRepository.findByEmail(email);
            this.setUser(this.userRepository.findByEmail(email));
        }

        return user;
    }


}
