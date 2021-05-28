package register.user;

import java.time.LocalDateTime;
import java.util.List;

public class UserTO {

	String name;
	String email;
	String role;
	List<LocalDateTime> timesRegistered;

	public UserTO() {
		super();
	}

	public UserTO(String name, String email, String role, List<LocalDateTime> timesRegistered) {
		super();
		this.name = name;
		this.email = email;
		this.role = role;
		this.timesRegistered = timesRegistered;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<LocalDateTime> getTimesRegistered() {
		return timesRegistered;
	}

	public void setTimesRegistered(List<LocalDateTime> timesRegistered) {
		this.timesRegistered = timesRegistered;
	}
}
