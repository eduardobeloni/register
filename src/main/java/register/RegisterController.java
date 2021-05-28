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

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public RedirectView login(@RequestBody User user) {
		String redirectUrl;
		UserTO userTO = this.userService.getUserByLogin(user.getEmail(), user.getPassword());

		if (userTO.getId() != null) {
			redirectUrl = "/myregisters/" + userTO.getId();
			if (userTO.getName().equals("admin")) {
				redirectUrl = "/dashboard";
			}
		} else {
			redirectUrl = "/invalidlogin";
		}

		return new RedirectView(redirectUrl);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dashboard")
	public List<UserTO> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/myregisters/{id}")
	public UserTO getUser(@PathVariable Integer id) {
		return this.userService.getUserById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "register/{id}")
	public Boolean register(@PathVariable Integer id) {
		return this.regTimeService.registerTime(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/invalidlogin")
	public String getInvalidLogin() {
		return "This login is invalid. Try again";
	}
}
