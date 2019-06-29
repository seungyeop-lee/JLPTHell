package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.dao.UserInfoDao;
import server.dto.UserInfo;

@Service
public class ConnectionUIService {
	
	@Autowired
	UserInfoDao userInfoDao;
	
	/**
	 * 패스워드 힌트 반환 
	 * 
	 * @param id
	 * @return 패스워드 힌트, 존재하지 않는 id일 경우 null
	 */
	public String pwHint(String id) {
		UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));
		return userInfo.getPwhint();
	}
	
	/**
	 * login 메소드
	 * 
	 * @param loginInfo
	 * @return user의 grade를 반환
	 */
	public String login(String[] loginInfo) {
		String userId = loginInfo[0];
		String userPw = loginInfo[1];
		UserInfo userInfo = userInfoDao.findUser(new UserInfo(userId, userPw));
		return userInfo.getGrade();
	}
	
}
