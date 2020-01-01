package rs.raf.cloud.domain.user.request;

import rs.raf.cloud.core.validation.constraints.UniqueField;
import rs.raf.cloud.domain.user.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank
    @UniqueField(entity = User.class, fieldName = "email", message = "User with given email already exists")
    @Email
    private String email;

    @NotBlank
    private String password;

    private String firstName;
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
