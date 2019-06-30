package server.dto;

public class Study {
	
	private int userno;
	private String grade;
	private int wordno;
	private String scount;
	
	public Study() {
	}
	
	public Study(int userno) {
		this(userno, null, 0, null);
	}

	public Study(int userno, String grade) {
		this(userno, grade, 0, null);
	}
	
	public Study(int userno, String grade, int wordno, String scount) {
		this.userno = userno;
		this.grade = grade;
		this.wordno = wordno;
		this.scount = scount;
	}

	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getWordno() {
		return wordno;
	}
	public void setWordno(int wordno) {
		this.wordno = wordno;
	}
	public String getScount() {
		return scount;
	}
	public void setScount(String scount) {
		this.scount = scount;
	}
	
	@Override
	public String toString() {
		return "Study [userno=" + userno + ", grade=" + grade + ", wordno=" + wordno + ", scount=" + scount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((scount == null) ? 0 : scount.hashCode());
		result = prime * result + userno;
		result = prime * result + wordno;
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
		Study other = (Study) obj;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (scount == null) {
			if (other.scount != null)
				return false;
		} else if (!scount.equals(other.scount))
			return false;
		if (userno != other.userno)
			return false;
		if (wordno != other.wordno)
			return false;
		return true;
	}
	
}
