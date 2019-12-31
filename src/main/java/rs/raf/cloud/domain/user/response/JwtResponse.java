package rs.raf.cloud.domain.user.response;


public class JwtResponse {

    private final String jwtToken;

    public JwtResponse(String jwttoken) {
        this.jwtToken = jwttoken;
    }

    public String getToken() {
        return this.jwtToken;
    }

}
