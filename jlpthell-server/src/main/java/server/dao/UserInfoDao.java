package server.dao;

import java.util.List;

import server.dto.UserInfo;

public interface UserInfoDao {
	UserInfo findUser(UserInfo userInfo);
	int addUser(UserInfo userInfo);
	int updateUser(UserInfo userInfo);
	int deleteUser(UserInfo userInfo);
	List<UserInfo> findAll();
}
