package server.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.dao.StudyDao;
import server.dao.UserInfoDao;
import server.dto.Study;
import server.dto.UserInfo;
import vo.UserForList;

@Service
public class UserlistUIService {
	
	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	StudyDao studyDao;
	
	/**
	 * 현재 가입 중인 유저 정보 획득
	 *
	 * @return 유저의 학습 정보
	 */
	public ArrayList<UserForList> userList() {
		ArrayList<UserForList> result = new ArrayList<>();

		//가입되어있는 회원의 목록 검색
		List<UserInfo> userInfos = userInfoDao.findAll();

		for(UserInfo userInfo : userInfos) {
			List<Study> studies = studyDao.findStudy(new Study(userInfo.getUserno()));
			int studiedCount = 0;
			int studyingCount = 0;
			for(Study study : studies) {
				if(study.getGrade().equals(userInfo.getGrade())) {
					if(study.getScount().equals("5")) {
						studiedCount++;
					} else {
						studyingCount++;
					}
				}
			}
			result.add(new UserForList(userInfo.getUserid(), userInfo.getGrade(), studyingCount, studiedCount));
		}

		return result;
	}
}
