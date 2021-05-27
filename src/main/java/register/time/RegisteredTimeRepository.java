package register.time;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RegisteredTimeRepository extends CrudRepository<RegisteredTime, Integer> {

	public List<RegisteredTime> findByUserId(@Param("user_id") Integer userId);

}
