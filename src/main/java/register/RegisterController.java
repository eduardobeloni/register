package register;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private Integer loggedUserId = null;

	private boolean isAdmin = false;

	private static final int SESSION_EXPIRE_TIME = 2;

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
		UserTO userTO = this.userService.getUserByLogin(user.getEmail(), user.getPassword());
		if (userTO.getId() == null)
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);

		loggedUserId = userTO.getId();
		isAdmin = userTO.getName().equals("admin");

		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();

		session = request.getSession(true);
		session.setMaxInactiveInterval(this.SESSION_EXPIRE_TIME * 60);

		return ResponseEntity.ok("Success");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dashboard")
	public List<UserTO> getAllUsers(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || !isAdmin)
			return new ArrayList<UserTO>();

		return this.userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/myregisters")
	public UserTO getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null)
			return new UserTO();

		return this.userService.getUserById(loggedUserId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public Boolean register(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null)
			return false;

		return this.regTimeService.registerTime(loggedUserId);
	}
}
