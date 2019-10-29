package testGUI;

public class User {
	private String rjset;
	private String username;
	private String password;
	private String student_number;
	private String student_password;
	private String jset;

	public User() {
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
    public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username =username;
	}

	public void set_json() {
		this.jset="{\"username\": "  +    "\""+this.username  + "\",\"password\": \"" +this.password+"\"}";
	}
	public String get_json() {
		return this.jset;
	}
////////////////////////////////////////////////////////
	public String getStudent_number() {
		return this.student_number;
	}
	public void setStudent_number(String student_number) {
		this.student_number=student_number;
	}
    public String getStudent_password() {
		return this.student_password;
	}
	public void setStudent_password(String student_password) {
		this.student_password =student_password;
	}
	public void set_rjson() {
		this.rjset="{\"username\": "  +    "\""+this.username  + "\",\"password\": \"" +this.password+"\",\"student_number\": \""+this.student_number+"\",\"student_password\": \""+this.student_password+"\"}";
	}

	public String get_rjson() {
		return this.rjset;
	}
}