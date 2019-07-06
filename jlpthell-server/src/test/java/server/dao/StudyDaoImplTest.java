package server.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import server.config.ServerConfigration;
import server.dto.Study;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class StudyDaoImplTest {
	
	@Autowired
    private StudyDao dao;

    private Study s1;
    private Study s2;
    
    @Before
    public void setUp() {
    	s1 = new Study(1, "N1", 11, "0");
    	s2 = new Study(2, "N2", 22, "0");
    }

	@Test
	public void findStudy() {
		List<Study> studies1 = dao.findStudy(new Study(1));
		assertThat(studies1.size(), is(0));
    	
        dao.addStudy(s1);
        dao.addStudy(s2);
        assertThat(dao.findAll().size(), is(2));
        
        Study study2 = dao.findStudy(new Study(1)).get(0);
        checkSameStudy(study2, s1);
	}
	
	@Test
	public void addStudy() {
		dao.addStudy(s1);
    	dao.addStudy(s2);
    	List<Study> studies = dao.findAll();
    	assertThat(studies.size(), is(2));
	}

	@Test
	public void updateStudy() {
		int updated = dao.updateStudy(s1);
		assertThat(updated, is(0));

		dao.addStudy(s1);
		dao.addStudy(s2);

		s1.setScount("1");
		updated = dao.updateStudy(s1);
		assertThat(updated, is(1));

		checkSameStudy(dao.findStudy(new Study(s1.getUserno())).get(0), s1);

	}

	@Test
	public void deleteStudy() {
		int updated = dao.deleteStudy(s1);
		assertThat(updated, is(0));

		assertThat(dao.findAll().size(), is(0));
		dao.addStudy(s1);
		dao.addStudy(s2);
		assertThat(dao.findAll().size(), is(2));
		updated = dao.deleteStudy(s1);
		assertThat(updated, is(1));
		assertThat(dao.findAll().size(), is(1));
		updated = dao.deleteStudy(s2);
		assertThat(updated, is(1));
		assertThat(dao.findAll().size(), is(0));
	}
	
	@Test
    public void findAll() {
    	assertThat(dao.findAll().size(), is(0));
    	
    	dao.addStudy(s1);
    	List<Study> studies1 = dao.findAll();
    	assertThat(studies1.size(), is(1));
    	checkSameStudy(studies1.get(0), s1);
    	
    	dao.addStudy(s2);
    	List<Study> studies2 = dao.findAll();
    	assertThat(studies2.size(), is(2));
    	checkSameStudy(studies2.get(0), s1);
    	checkSameStudy(studies2.get(1), s2);
    }
	
	private void checkSameStudy(Study s1, Study s2) {
        assertThat(s1.getUserno(), is(s2.getUserno()));
        assertThat(s1.getGrade(), is(s2.getGrade()));
        assertThat(s1.getWordno(), is(s2.getWordno()));
        assertThat(s1.getScount(), is(s2.getScount()));
    }

}
