package register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
	public RedirectView registerTime(@RequestBody User user) {
		String redirectUrl;
		User u = this.userService.getUser(user.getEmail(), user.getPassword());

		if (u.getId() != null)
			redirectUrl = "list/" + u.getId();
		else
			redirectUrl = "/invalidlogin";

		this.regTimeService.registerTime(u);

		return new RedirectView(redirectUrl);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public List<UserTO> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/{id}")
	public UserTO getUser(@PathVariable Integer id) {
		return this.userService.getUserById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/invalidlogin")
	public String getInvalidLogin() {
		return "This login is invalid. Try again";
	}
}
