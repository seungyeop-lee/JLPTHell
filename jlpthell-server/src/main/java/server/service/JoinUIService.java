package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.dao.UserInfoDao;
import server.dto.UserInfo;
import server.vo.User;

@Service
public class JoinUIService {
	
	@Autowired
	UserInfoDao userInfoDao;
	
	/**
	 * 회원가입 처리
	 * 
	 * @param user 가입자 정보
	 * @return 처리결과
	 */
	public boolean join(User user) {
		int count = userInfoDao.addUser(userMapper(user));
		if(count == 1) {
			return true;
		}
		return false;
	}
	
	private UserInfo userMapper(User user) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserid(user.getId());
		userInfo.setUserpw(user.getPassword());
		userInfo.setPwhint(user.getPwHint());
		userInfo.setGrade(user.getGrade());
		return userInfo;
	}
}
