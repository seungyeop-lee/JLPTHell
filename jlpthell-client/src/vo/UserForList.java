//유저가 학습 한 정보 vo
package vo;

import java.io.Serializable;

public class UserForList implements Serializable {
	private String id;
	private String grade;
	private int studyingCount;
	private int studiedCount;
	
	public UserForList(String id, String grade, int studyingCount, int studiedCount) {
		this.id = id;
		this.grade = grade;
		this.studyingCount = studyingCount;
		this.studiedCount = studiedCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getStudyingCount() {
		return studyingCount;
	}

	public void setStudyingCount(int studyingCount) {
		this.studyingCount = studyingCount;
	}

	public int getStudiedCount() {
		return studiedCount;
	}

	public void setStudiedCount(int studiedCount) {
		this.studiedCount = studiedCount;
	}
}
