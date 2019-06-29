package server.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import server.config.ServerConfigration;
import server.dao.StudyDao;
import server.dao.UserInfoDao;
import server.dao.VocaDao;
import server.dto.Study;
import server.dto.UserInfo;
import server.dto.Voca;
import vo.UserWord;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class MemUIServiceTest {

	@Autowired
	@InjectMocks
	private MemUIService service;
	
	@Mock
	private UserInfoDao userInfoDao;
	
	@Mock
	private StudyDao studyDao;
	
	@Mock
	private VocaDao vocaDao;
	
	private String id;
	private String grade;
	private int userNo;
	private int wordNo1;
	private int wordNo2;
	private UserInfo userInfo;
	private Study s1;
	private Study s2;
	private Voca v1;
	private Set<Integer> studyWordNo;
	
	@Before
	public void setUp() throws Exception {
		id = "testId1";
		grade = "N1";
		userNo = 100;
		wordNo1 = 1;
		wordNo2 = 2;
		
		userInfo = new UserInfo(id, null, null, grade, null, userNo);
		s1 = new Study(userNo, grade, wordNo1, "0");
		s2 = new Study(userNo, grade, wordNo2, "0");
		v1 = new Voca(wordNo1, "word", "mean", grade);
		studyWordNo = new HashSet<Integer>(Arrays.asList(wordNo1, wordNo2));
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getStudyWord() {
		when(userInfoDao.findUser(new UserInfo(id))).thenReturn(userInfo);
		when(studyDao.findStudy(new Study(userNo))).thenReturn(Arrays.asList(s1, s2));
		when(vocaDao.findAll(new Voca(grade))).thenReturn(Arrays.asList(new Voca[1358]));
		when(vocaDao.findVoca(any(Voca.class))).thenReturn(v1);
		
		List<UserWord> userWords = service.getStudyWord(id, grade);
		
		assertThat(userWords.size(), is(20));
		verify(userInfoDao, times(1)).findUser(any());
		verify(studyDao, times(1)).findStudy(any());
		verify(vocaDao, times(1)).findAll(any());
		verify(vocaDao, times(20)).findVoca(any());
	}

	@Test
	public void saveStudyWord() {
		when(userInfoDao.findUser(new UserInfo(id))).thenReturn(userInfo);
		
		service.saveStudyWord(studyWordNo, id);
		
		verify(userInfoDao, times(1)).findUser(any());
		ArgumentCaptor<Study> studyArg = ArgumentCaptor.forClass(Study.class);
		verify(studyDao, times(2)).addStudy(studyArg.capture());
		List<Study> addedStudies = studyArg.getAllValues();
		checkSameStudy(addedStudies.get(0), s1);
		checkSameStudy(addedStudies.get(1), s2);
	}
	
	private void checkSameStudy(Study s1, Study s2) {
        assertThat(s1.getUserno(), is(s2.getUserno()));
        assertThat(s1.getGrade(), is(s2.getGrade()));
        assertThat(s1.getWordno(), is(s2.getWordno()));
        assertThat(s1.getScount(), is(s2.getScount()));
    }
	
}
