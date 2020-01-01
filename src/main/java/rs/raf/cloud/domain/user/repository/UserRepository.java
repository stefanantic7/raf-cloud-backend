package rs.raf.cloud.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.cloud.domain.user.entity.User;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
