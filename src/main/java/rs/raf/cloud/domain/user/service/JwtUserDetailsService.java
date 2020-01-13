package rs.raf.cloud.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rs.raf.cloud.domain.user.JwtTokenUtil;
import rs.raf.cloud.domain.user.facade.auth.IAuthFacade;
import rs.raf.cloud.domain.user.entity.User;
import rs.raf.cloud.domain.user.repository.UserRepository;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IAuthFacade authenticationFacade;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        this.authenticationFacade.setUser(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public String authenticateAndGetToken(String email, String password) throws DisabledException, BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        var userDetails = this.loadUserByUsername(email);

        return jwtTokenUtil.generateToken(userDetails);
    }


}
