package server.dao;

import java.util.List;

import server.dto.Voca;

public interface VocaDao {
	Voca findVoca(Voca voca);
	List<Voca> findAll(Voca voca);
}
