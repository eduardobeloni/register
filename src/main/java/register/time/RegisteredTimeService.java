package register.time;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import register.user.User;

@Service
public class RegisteredTimeService {

	@Autowired
	RegisteredTimeRepository regTimeRepo;

	public void registerTime(User user) {
		RegisteredTime regTime = new RegisteredTime();

		if (user.getId() != null) {
			regTime.setTimeRegistered(LocalDateTime.now());
			regTime.setUserId(user.getId());
			this.regTimeRepo.save(regTime);
		}
	}
}
