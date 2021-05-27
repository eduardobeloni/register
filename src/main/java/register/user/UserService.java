package register.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public User getUser(String email, String password) {
		List<User> users = this.userRepo.findByEmailAndPassword(email, password);
		if (users.size() > 0)
			return users.get(0);

		return new User();
	}

	public User getById(Integer id) {
		Optional<User> opt = this.userRepo.findById(id);
		User user = new User();

		if (opt.isPresent()) {
			user = opt.get();
			user.setRegisteredTimes(this.regTimeRepo.findByUserId(user.getId()));
		}

		return user;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		this.userRepo.findAll().forEach(users::add);
		for (User user : users)
			user.setRegisteredTimes(this.regTimeRepo.findByUserId(user.getId()));

		return users;
	}

}
