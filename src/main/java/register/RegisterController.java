package register;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	private Integer getCookieUserId(HttpServletRequest request) {
		Integer userId = null;
		Cookie userCookies[] = request.getCookies();

		if (userCookies == null)
			return null;

		for (Cookie cookie : userCookies) {
			if (cookie.getName().equals("user-id")) {
				userId = Integer.parseInt(cookie.getValue());
				break;
			}
		}

		return userId;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpServletResponse response) {
		UserTO userTO = this.userService.getUserByLogin(user.getEmail(), user.getPassword());

		if (userTO.getId() == null)
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);

		Cookie userCookie = new Cookie("user-id", userTO.getId().toString());
		userCookie.setHttpOnly(true);
		userCookie.setSecure(false);
		response.addCookie(userCookie);

		return ResponseEntity.ok("Success");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dashboard")
	public List<UserTO> getAllUsers(HttpServletRequest request) {
		Integer cookieUserId = this.getCookieUserId(request);

		if (cookieUserId == null)
			return new ArrayList<UserTO>();

		UserTO userTO = this.userService.getUserById(cookieUserId);
		if (userTO.getId() != null && userTO.getName().equals("admin"))
			return this.userService.getAllUsers();

		return new ArrayList<UserTO>();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/myregisters")
	public UserTO getUser(HttpServletRequest request) {
		Integer cookieUserId = this.getCookieUserId(request);

		if (cookieUserId == null)
			return new UserTO();

		return this.userService.getUserById(cookieUserId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public Boolean register(HttpServletRequest request) {
		Integer cookieUserId = this.getCookieUserId(request);

		if (cookieUserId == null)
			return false;

		return this.regTimeService.registerTime(cookieUserId);
	}
}
