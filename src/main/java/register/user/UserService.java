package register.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	private UserTO assembleUserTO(User user) {
		List<LocalDateTime> timesRegistered = new ArrayList<>();

		List<RegisteredTime> regTimes = this.regTimeRepo.findByUserId(user.getId());
		for (RegisteredTime regTime : regTimes)
			timesRegistered.add(regTime.getTimeRegistered());

		return new UserTO(user.getId(), user.getName(), timesRegistered);
	}

	public void createUser(User user) {
		this.userRepo.save(user);
	}

	public UserTO getUserByLogin(String email, String password) {
		UserTO userTO = new UserTO();
		User user = this.userRepo.findByEmailAndPassword(email, password);

		if (user != null)
			userTO = assembleUserTO(user);

		return userTO;
	}

	public UserTO getUserById(Integer id) {
		UserTO userTO = new UserTO();
		Optional<User> userOpt = this.userRepo.findById(id);

		if (userOpt.isPresent())
			userTO = assembleUserTO(userOpt.get());

		return userTO;
	}

	public List<UserTO> getAllUsers() {
		List<User> users = new ArrayList<>();
		List<UserTO> usersTO = new ArrayList<>();

		this.userRepo.findAll().forEach(users::add);
		for (User user : users)
			usersTO.add(assembleUserTO(user));

		return usersTO;
	}

}
