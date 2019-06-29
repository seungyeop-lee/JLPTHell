//User 정보 vo
package client.vo;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -8989373840072928381L;
	
	private String id;
	private String password;
	private String grade;
	private String pwHint;
	
	public User(String id, String password, String grade, String pwHint) {
		super();
		this.id = id;
		this.password = password;
		this.grade = grade;
		this.pwHint = pwHint;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getPwHint() {
		return pwHint;
	}

	public void setPwHint(String pwHint) {
		this.pwHint = pwHint;
	}
}
