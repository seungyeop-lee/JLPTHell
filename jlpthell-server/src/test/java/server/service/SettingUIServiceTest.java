package server.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import server.config.ServerConfigration;
import server.dao.StudyDao;
import server.dao.UserInfoDao;
import server.dto.Study;
import server.dto.UserInfo;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class SettingUIServiceTest {
	
	@Autowired
	@InjectMocks
	private SettingUIService service;
	
	@Mock
	private UserInfoDao userInfoDao;
	
	@Mock
	private StudyDao studyDao;
	
	private String userid;
	private String userpw;
	private String grade;
	private String pwhint;
	private int userno;
	private UserInfo userInfo;
	private String changeGrade;
	private UserInfo changedUserInfo;
	private Study study;

	@Before
	public void setUp() throws Exception {
		userid = "testId";
		userpw = "testPw";
		pwhint = "testHint";
		grade = "N1";
		userInfo = new UserInfo(userid, userpw, pwhint, grade, null, 0);
		changeGrade = "N2";
		changedUserInfo = new UserInfo(userid, userpw, pwhint, changeGrade, null, 0);
		study = new Study(userno, grade);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void changeGradeSameGrade() {
		when(userInfoDao.findUser(new UserInfo(userid))).thenReturn(userInfo);
		
		int affctedColumns = service.changeGrade(userid, grade);
		
		assertThat(affctedColumns, is(0));
		verify(userInfoDao, times(1)).findUser(Mockito.any(UserInfo.class));
	}
	
	@Test
	public void changeGradeDifferentGrade() {
		when(userInfoDao.findUser(new UserInfo(userid))).thenReturn(userInfo);
		when(userInfoDao.updateUser(changedUserInfo)).thenReturn(1);
		
		int affctedColumns = service.changeGrade(userid, changeGrade);
		
		assertThat(affctedColumns, is(1));
		verify(userInfoDao, times(1)).findUser(Mockito.any(UserInfo.class));
		verify(userInfoDao, times(1)).updateUser(Mockito.any(UserInfo.class));
	}

	@Test
	public void initialize() {
		when(userInfoDao.findUser(new UserInfo(userid))).thenReturn(userInfo);
		when(studyDao.deleteStudy(study)).thenReturn(0);
		
		boolean result = service.initialize(userid, grade);
		
		assertThat(result, is(false));
		verify(userInfoDao, times(1)).findUser(Mockito.any(UserInfo.class));
		verify(studyDao, times(1)).deleteStudy(Mockito.any(Study.class));
	}
	
	@Test
	public void initializeSuccess() {
		when(userInfoDao.findUser(new UserInfo(userid))).thenReturn(userInfo);
		when(studyDao.deleteStudy(study)).thenReturn(1);
		
		boolean result = service.initialize(userid, grade);
		
		assertThat(result, is(true));
		verify(userInfoDao, times(1)).findUser(Mockito.any(UserInfo.class));
		verify(studyDao, times(1)).deleteStudy(Mockito.any(Study.class));
	}

	@Test
	public void deleteUserFail() {
		when(userInfoDao.deleteUser(new UserInfo(userid))).thenReturn(0);
		
		boolean result = service.deleteUser(userid);
		
		assertThat(result, is(false));
		verify(userInfoDao, times(1)).deleteUser(Mockito.any(UserInfo.class));
	}
	
	@Test
	public void deleteUserSuccess() {
		when(userInfoDao.deleteUser(new UserInfo(userid))).thenReturn(1);
		
		boolean result = service.deleteUser(userid);
		
		assertThat(result, is(true));
		verify(userInfoDao, times(1)).deleteUser(Mockito.any(UserInfo.class));
	}

}
