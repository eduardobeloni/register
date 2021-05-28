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
import register.user.UserTO;

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

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public List<UserTO> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/{id}")
	public UserTO getUser(@PathVariable Integer id) {
		return this.userService.getUserById(id);
	}

}
