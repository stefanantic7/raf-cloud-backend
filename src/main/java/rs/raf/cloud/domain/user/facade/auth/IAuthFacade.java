package rs.raf.cloud.domain.user.facade.auth;

import rs.raf.cloud.domain.user.entity.User;

public interface IAuthFacade {

    String getUsername();

    Long getId();

    User getUser();

    void setUser(User user);

}
