package mg.inclusiv.cdan8.repository;

import mg.inclusiv.cdan8.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByUsernameAndPassword(String username, String password);
}
