package rs.raf.cloud.domain.user.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class JwtRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public JwtRequest(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
