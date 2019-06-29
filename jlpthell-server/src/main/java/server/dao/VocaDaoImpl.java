package server.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Repository;

import server.dto.Voca;

@Repository
public class VocaDaoImpl implements VocaDao {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setEmbeddedDatabase(EmbeddedDatabase dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Voca> mapper = (rs, rowNum)->{
		Voca temp = new Voca();
		temp.setNo(rs.getInt("no"));
		temp.setWord(rs.getString("word"));
		temp.setMean(rs.getString("mean"));
		return temp;
	};

	@Override
	public Voca findVoca(Voca voca) {
		String sql = String.format("select * from %s where no = ?", voca.getGrade());
		
		try {
			return jdbcTemplate.queryForObject(sql, mapper, voca.getNo());
		} catch (EmptyResultDataAccessException e) {
			return new Voca();
		}
	}

	@Override
	public List<Voca> findAll(Voca voca) {
		String sql = String.format("select * from %s", voca.getGrade());
		
		try {
			return jdbcTemplate.query(sql, mapper);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Voca>();
		}
	}

}
