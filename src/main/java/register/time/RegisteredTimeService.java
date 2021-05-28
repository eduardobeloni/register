package register.time;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredTimeService {

	@Autowired
	RegisteredTimeRepository regTimeRepo;

	public void registerTime(Integer userId) {
		RegisteredTime regTime = new RegisteredTime();

		if (userId != null) {
			regTime.setTimeRegistered(LocalDateTime.now());
			regTime.setUserId(userId);
			this.regTimeRepo.save(regTime);
		}
	}
}
