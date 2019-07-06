package server.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Repository;

import server.dto.Study;

@Repository
public class StudyDaoImpl implements StudyDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setEmbeddedDatabase(EmbeddedDatabase dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Study> mapper = (rs, rowNum)->{
		Study temp = new Study();
		temp.setUserno(rs.getInt("userno"));
		temp.setGrade(rs.getString("grade"));
		temp.setWordno(rs.getInt("wordno"));
		temp.setScount(rs.getString("scount"));
		return temp;
	};
	
	@Override
	public List<Study> findStudy(Study study) {
		String sql = "select * from study where userno = ?";
		
		try {
			return jdbcTemplate.query(sql, mapper, study.getUserno());
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Study>();
		}
	}

	@Override
	public int addStudy(Study study) {
		String sql = "insert into study (userno, grade, wordno, scount) "
				+ "values (?, ?, ?, 0)";

		try {
			return jdbcTemplate.update(sql,
					study.getUserno(),
					study.getGrade(),
					study.getWordno());
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int updateStudy(Study study) {
		String sql = "update study set scount = ? where wordno = ? and grade = ? and userno = ?";

		try {
			return jdbcTemplate.update(sql,
					study.getScount(),
					study.getWordno(),
					study.getGrade(),
					study.getUserno());
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int deleteStudy(Study study) {
		String sql = "delete study where userno = ? and grade = ?";

		try {
			return jdbcTemplate.update(sql, 
					study.getUserno(), 
					study.getGrade());
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Study> findAll() {
		String sql = "select * from study";
		
		try {
			return jdbcTemplate.query(sql, mapper);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Study>();
		}
	}

}