package server.dto;

import java.util.Date;

public class UserInfo {
	
	private String userid;
	private String userpw;
	private String pwhint;
	private String grade;
	private Date insertdate;
	private int userno;
	
	public UserInfo() {
	}
	
	public UserInfo(String userid, String userpw, String pwhint, String grade, Date insertdate, int userno) {
		this.userid = userid;
		this.userpw = userpw;
		this.pwhint = pwhint;
		this.grade = grade;
		this.insertdate = insertdate;
		this.userno = userno;
	}

	public UserInfo(String userid) {
		this(userid, null, null, null, null, 0);
	}
	
	public UserInfo(String userid, String userpw) {
		this(userid, userpw, null, null, null, 0);
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getPwhint() {
		return pwhint;
	}
	public void setPwhint(String pwhint) {
		this.pwhint = pwhint;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}

	@Override
	public String toString() {
		return "UserInfo [userid=" + userid + ", userpw=" + userpw + ", pwhint=" + pwhint + ", grade=" + grade
				+ ", insertdate=" + insertdate + ", userno=" + userno + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((insertdate == null) ? 0 : insertdate.hashCode());
		result = prime * result + ((pwhint == null) ? 0 : pwhint.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		result = prime * result + userno;
		result = prime * result + ((userpw == null) ? 0 : userpw.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (insertdate == null) {
			if (other.insertdate != null)
				return false;
		} else if (!insertdate.equals(other.insertdate))
			return false;
		if (pwhint == null) {
			if (other.pwhint != null)
				return false;
		} else if (!pwhint.equals(other.pwhint))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		if (userno != other.userno)
			return false;
		if (userpw == null) {
			if (other.userpw != null)
				return false;
		} else if (!userpw.equals(other.userpw))
			return false;
		return true;
	}
	
}
