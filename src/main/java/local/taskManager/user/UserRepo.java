package local.taskManager.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, String> {
	Optional<User> findByEmailAndPass(String email, String password);

}
