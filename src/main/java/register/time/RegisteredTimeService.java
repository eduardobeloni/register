package register.time;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import register.user.User;
import register.user.UserRepository;

@Service
public class RegisteredTimeService {

	@Autowired
	RegisteredTimeRepository regTimeRepo;

	@Autowired
	UserRepository userRepo;

	public Boolean registerTime(Integer id) {
		RegisteredTime regTime = new RegisteredTime();
		Optional<User> userOpt = this.userRepo.findById(id);

		if (userOpt.isPresent()) {
			regTime.setTimeRegistered(LocalDateTime.now());
			regTime.setUserId(userOpt.get().getId());
			this.regTimeRepo.save(regTime);

			return true;
		}

		return false;
	}
}
