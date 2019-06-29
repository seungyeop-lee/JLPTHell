//유저가 학습 한 또는 학습 할 단어 vo
package server.vo;

import java.io.Serializable;

public class UserWord implements Serializable{
	
	private static final long serialVersionUID = -3847914468967265259L;
	
	private String word;
	private String mean;
	private int count;
	private int wordNo;
	
	public UserWord(int wordNo, String word, String mean) {
		this.word = word;
		this.mean = mean;
		this.wordNo = wordNo;
	}

	public UserWord(String word, String mean, int count) {
		this.word = word;
		this.mean = mean;
		this.count = count;
	}
	
	public UserWord(int wordNo, String word, String mean, int count) {
		this.wordNo = wordNo;
		this.word = word;
		this.mean = mean;
		this.count = count;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getWordNo() {
		return wordNo;
	}

	public void setWordNo(int wordNo) {
		this.wordNo = wordNo;
	}
}
