package server.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.dao.StudyDao;
import server.dao.UserInfoDao;
import server.dao.VocaDao;
import server.dto.Study;
import server.dto.UserInfo;
import server.dto.Voca;
import vo.UserWord;

@Service
public class MemUIService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private StudyDao studyDao;
	
	@Autowired
	private VocaDao vocaDao;
	
	/**
	 * Client에 학습할 단어 List를 보낸다.
	 * @param 사용자의 id
	 * @param 사용자의 grade
	 * @return 학습할 단어가 담겨있는 ArrayList
	 */
	public List<UserWord> getStudyWord(String id, String grade) {
		Set<Integer> studiedIndexes = getStudiedIndex(id, grade);
		
		//등급에 따른 단어장 저장 갯수 확인
		int maxWordNum = vocaDao.findAll(new Voca(grade)).size();
		
		Set<Integer> studyIndexes = getStudyIndex(maxWordNum, studiedIndexes);
		
		List<Voca> studyVocas = getStudyVocas(studyIndexes, grade);
		return vocaListToWordList(studyVocas);
	}

	//사용자가 기존에 학습한 단어의 index를 획득
	private Set<Integer> getStudiedIndex(String id, String grade) {
		Set<Integer> studiedIndexes = new HashSet<Integer>();
		
		UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));
		List<Study> studies = studyDao.findStudy(new Study(userInfo.getUserno()));
		
		for(Study study : studies) {
			if(study.getGrade().equals(grade)) {
				studiedIndexes.add(study.getWordno());
			}
		}
		return studiedIndexes;
	}
	
	//random함수를 이용한 학습할 단어 wordNo 난수 발생, 학습한 단어의 wordNo는 제외
	private Set<Integer> getStudyIndex(int maxWordNum, Set<Integer> studiedIndexes) {
		Set<Integer> studyIndexes = new HashSet<Integer>();
		Random r = new Random();
		while(studyIndexes.size() < 20) {
			int temp = r.nextInt(maxWordNum) + 1;
			if(!studiedIndexes.contains(temp)) {
				studyIndexes.add(temp);
			}
		}
		return studyIndexes;
	}
	
	private List<Voca> getStudyVocas(Set<Integer> studyIndexes, String grade) {
		List<Voca> vocas = new ArrayList<Voca>();
		for(int i : studyIndexes) {
			vocas.add(vocaDao.findVoca(new Voca(i, grade)));
		}
		return vocas;
	}
	
	private List<UserWord> vocaListToWordList(List<Voca> studyVocas) {
		List<UserWord> userWords = new ArrayList<UserWord>();
		for(Voca voca : studyVocas) {
			userWords.add(new UserWord(voca.getNo(), voca.getWord(), voca.getMean()));
		}
		return userWords;
	}
	
	/**
	 * 사용자가 학습한 index를 study 테이블에 저장
	 * @param 사용자가 학습한 wordno가 담겨있는 studyWordNo
	 */
	public void saveStudyWord(Set<Integer> studyWordNo, String id) {
		UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));
		
		for(int i : studyWordNo) {
			Study study = new Study();
			study.setUserno(userInfo.getUserno());
			study.setGrade(userInfo.getGrade());
			study.setWordno(i);
			study.setScount("0");
			studyDao.addStudy(study);
		}
	}
}
