package register.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
