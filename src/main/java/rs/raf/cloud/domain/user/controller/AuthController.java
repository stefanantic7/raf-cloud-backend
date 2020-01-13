package rs.raf.cloud.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rs.raf.cloud.domain.user.JwtTokenUtil;
import rs.raf.cloud.domain.user.request.JwtRequest;
import rs.raf.cloud.domain.user.response.JwtResponse;
import rs.raf.cloud.domain.user.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return ResponseEntity.ok(new JwtResponse(this.jwtUserDetailsService.authenticateAndGetToken(jwtRequest.getEmail(), jwtRequest.getPassword())));
    }
}
