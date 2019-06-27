//유저 현황 창 UI ServerManager
package server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.UserForList;

public class UserlistUIServerManager {
	
	//현재 가입 중인 유저 정보 획득, 유저정보를 답은 ArrayList로 반환
	public ArrayList<UserForList> userList() {
		ArrayList<UserForList> result = new ArrayList<>();
		ArrayList<String> userIdList = new ArrayList<>();
		ArrayList<String> userGradeList = new ArrayList<>();
		ArrayList<Integer> userStudyingNumList = new ArrayList<>(); 
		ArrayList<Integer> userStudiedNumList = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
		
		//가입되어있는 회원의 목록 검색
		try {
			String sql = "select userid, grade from userinfo";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				userIdList.add(rs.getString(1));
				userGradeList.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//학습 중인 단어 저장
		try {
			for(int i = 0; i < userIdList.size(); ++i) {
				String sql = "select count(*) from study where userno = (select userno from userinfo where userid = ?) and grade = ? and scount != 5";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userIdList.get(i));
				pstmt.setString(2, userGradeList.get(i));
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					userStudyingNumList.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//학습 완료한 단어 저장
		try {
			for(int i = 0; i < userIdList.size(); ++i) {
				String sql = "select count(*) from study where userno = (select userno from userinfo where userid = ?) and grade = ? and scount = 5";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userIdList.get(i));
				pstmt.setString(2, userGradeList.get(i));
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					userStudiedNumList.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//검색한 결과를  UserForList객체에 담고, ArrayList로 넣음
		for(int i = 0; i < userIdList.size(); ++i) {
			result.add(new UserForList(userIdList.get(i), userGradeList.get(i), userStudyingNumList.get(i), userStudiedNumList.get(i)));
		}
		
		return result;
	}
}
