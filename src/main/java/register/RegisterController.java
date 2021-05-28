package register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import register.time.RegisteredTimeService;
import register.user.User;
import register.user.UserService;

@RestController
public class RegisterController {

	@Autowired
	RegisteredTimeService regTimeService;

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public void registerTime(@RequestBody User user) {
		Integer userId = this.userService.getUserId(user.getEmail(), user.getPassword());
		this.regTimeService.registerTime(userId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/check")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/check/{id}")
	public User getUser(@PathVariable Integer id) {
		return this.userService.getById(id);
	}

}
