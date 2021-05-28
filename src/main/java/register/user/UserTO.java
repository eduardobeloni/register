package register.user;

import java.time.LocalDateTime;
import java.util.List;

public class UserTO {

	Integer id;
	String name;
	List<LocalDateTime> timesRegistered;

	public UserTO() {
		super();
	}

	public UserTO(Integer id, String name, List<LocalDateTime> timesRegistered) {
		super();
		this.id = id;
		this.name = name;
		this.timesRegistered = timesRegistered;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LocalDateTime> getTimesRegistered() {
		return timesRegistered;
	}

	public void setTimesRegistered(List<LocalDateTime> timesRegistered) {
		this.timesRegistered = timesRegistered;
	}
}
