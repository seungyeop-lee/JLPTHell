package server.dao;

import java.util.List;

import server.dto.Study;

public interface StudyDao {
	List<Study> findStudy(Study study);
	int addStudy(Study study);
	int deleteStudy(Study study);
	List<Study> findAll();
}
