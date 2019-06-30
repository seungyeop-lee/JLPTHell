package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.dao.StudyDao;
import server.dao.UserInfoDao;
import server.dto.Study;
import server.dto.UserInfo;

@Service
public class SettingUIService {
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private StudyDao studyDao;
	
	/**
	 * 급수 변경 메소드
	 * 
	 * @param id 사용자 id
	 * @param grade 변경하고 싶은 사용자 학습 등급
	 * @return 급수 변경 결과, 변경에 성공하면 1, 현재와 같은 급수이면 0을 반환 
	 */
	public int changeGrade(String id, String grade) {
		UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));
		
		if(userInfo.getGrade().equals(grade)) {
			return 0;
		}
		
		userInfo.setGrade(grade);
		return userInfoDao.updateUser(userInfo);
	}

	/**
	 * 학습 이력 초기화 메소드
	 * 
	 * @param id 사용자 id
	 * @param grade 사용자의 현재 학습 등급
	 * @return 학습 이력 초기화 결과
	 */
	public boolean initialize(String id, String grade) {
		UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));
		int affectedColumns = studyDao.deleteStudy(new Study(userInfo.getUserno(), userInfo.getGrade()));
		if(affectedColumns != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 아이디 삭제 메소드
	 * 
	 * @param id 사용자 아이디
	 * @return 아이디 삭제 결과
	 */
	public boolean deleteUser(String id) {
		int affectedColumns = userInfoDao.deleteUser(new UserInfo(id));
		if(affectedColumns != 0) {
			return true;
		} else {
			return false;
		}
	}
}
