package server.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import server.config.ServerConfigration;
import server.dto.Voca;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class VocaDaoImplTest {

	@Autowired
	VocaDao dao;
	
	private Voca v1;
	
	@Before
	public void setUp() {
		v1 = new Voca(1, "もうしこみ[申(し)込(み)]", "신청", "N1");
	}
	
	@Test
	public void findVoca() {
		Voca voca = dao.findVoca(new Voca(1, "N1"));
		checkSameVoca(voca, v1);
	}

	@Test
	public void findAll() {
		assertThat(dao.findAll(new Voca("N1")).size(), is(1358));
		assertThat(dao.findAll(new Voca("N2")).size(), is(1446));
		assertThat(dao.findAll(new Voca("N3")).size(), is(1212));
		assertThat(dao.findAll(new Voca("N4")).size(), is(868));
		assertThat(dao.findAll(new Voca("N5")).size(), is(625));
	}

	private void checkSameVoca(Voca v1, Voca v2) {
		assertThat(v1.getNo(), is(v2.getNo()));
		assertThat(v1.getWord(), is(v2.getWord()));
		assertThat(v1.getMean(), is(v2.getMean()));
	}
}
