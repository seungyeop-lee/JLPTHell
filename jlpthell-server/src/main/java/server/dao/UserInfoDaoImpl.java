package server.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Repository;

import server.dto.UserInfo;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setEmbeddedDatabase(EmbeddedDatabase dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<UserInfo> mapper = (rs, rowNum)->{
		UserInfo temp = new UserInfo();
		temp.setUserid(rs.getString("userid"));
		temp.setUserpw(rs.getString("userpw"));
		temp.setPwhint(rs.getString("pwhint"));
		temp.setGrade(rs.getString("grade"));
		temp.setInsertdate(rs.getDate("insertdate"));
		temp.setUserno(rs.getInt("userno"));
		return temp;
	};

	@Override
	public UserInfo findUser(UserInfo userInfo) {
		String sql = "select * from userinfo where userid = ?";
		
		try {
			return jdbcTemplate.queryForObject(sql, mapper, userInfo.getUserid());
		} catch (EmptyResultDataAccessException e) {
			return new UserInfo();
		}
	}

	@Override
	public int addUser(UserInfo userInfo) {
		String sql = "insert into userinfo(userid,userpw,pwhint,grade,insertdate,USERNO) " +
				"values(?,?,?,?,sysdate,SEQ_USERNO.NEXTVAL)";

		return jdbcTemplate.update(sql,
				userInfo.getUserid(),
				userInfo.getUserpw(),
				userInfo.getPwhint(),
				userInfo.getGrade());
	}
	
	@Override
	public List<UserInfo> findAll() {
		String sql = "select * from userinfo";
		
		try {
			return jdbcTemplate.query(sql, mapper);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<UserInfo>();
		}
	}
	
}
