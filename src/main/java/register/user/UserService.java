package register.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import register.time.RegisteredTime;
import register.time.RegisteredTimeRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RegisteredTimeRepository regTimeRepo;

	public void createUser(User user) {
		this.userRepo.save(user);
	}

	public Integer getUserId(String email, String password) {
		List<User> users = this.userRepo.findByEmailAndPassword(email, password);
		if (users.size() > 0)
			return users.get(0).getId();

		return null;
	}

	public List<LocalDateTime> getRegisteredTimesByUserId(Integer id) {
		List<LocalDateTime> timesRegistered = new ArrayList<>();
		List<RegisteredTime> regTimes = regTimeRepo.findByUserId(id);

		for (RegisteredTime regTime : regTimes)
			timesRegistered.add(regTime.getTimeRegistered());

		return timesRegistered;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		this.userRepo.findAll().forEach(users::add);
		for (User user : users)
			user.setRegisteredTimes(this.regTimeRepo.findByUserId(user.getId()));

		return users;
	}

}
