package server.dto;

public class Voca {
	
	private int no;
	private String word;
	private String mean;
	private String grade;
	
	public Voca() {
	}
	
	public Voca(String grade) {
		this(0, null, null, grade);
	}

	public Voca(int no, String grade) {
		this(no, null, null, grade);
	}
	
	public Voca(int no, String word, String mean, String grade) {
		this.no = no;
		this.word = word;
		this.mean = mean;
		this.grade = grade;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Voca [no=" + no + ", word=" + word + ", mean=" + mean + ", grade=" + grade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((mean == null) ? 0 : mean.hashCode());
		result = prime * result + no;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		Voca other = (Voca) obj;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (mean == null) {
			if (other.mean != null)
				return false;
		} else if (!mean.equals(other.mean))
			return false;
		if (no != other.no)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
}
