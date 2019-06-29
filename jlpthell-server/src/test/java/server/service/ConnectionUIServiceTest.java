package server.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
import server.dao.UserInfoDao;
import server.dto.UserInfo;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class ConnectionUIServiceTest {
	
	@Autowired
	@InjectMocks
	private ConnectionUIService service;
	
	@Mock
	private UserInfoDao dao;

	private String userid;
	private String userpw;
	private String grade;
	private String pwhint;
	private UserInfo userInfo;
	
	@Before
	public void setUp() {
		userid = "testId";
		userpw = "testPw";
		pwhint = "testHint";
		grade = "N1";
		userInfo = new UserInfo(userid, userpw, pwhint, grade, null, 0);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void pwHintNotExistUser() {
		when(dao.findUser(new UserInfo(userid))).thenReturn(new UserInfo());
		
		String result = service.pwHint(userInfo.getUserid());
		
		verify(dao, times(1)).findUser(Mockito.any());
		assertThat(result, nullValue());
	}
	
	@Test
	public void pwHintExistUser() {
		when(dao.findUser(new UserInfo(userid))).thenReturn(userInfo);
		
		String result = service.pwHint(userInfo.getUserid());
		
		verify(dao, times(1)).findUser(Mockito.any());
		assertThat(result, is(userInfo.getPwhint()));
	}

	@Test
	public void loginNotExistUser() {
		when(dao.findUser(new UserInfo(userid, userpw))).thenReturn(new UserInfo());
		
		String result = service.login(new String[] {userid, userpw});
		
		verify(dao, times(1)).findUser(Mockito.any());
		assertThat(result, nullValue());
	}
	
	@Test
	public void logintExistUser() {
		when(dao.findUser(new UserInfo(userid, userpw))).thenReturn(userInfo);
		
		String result = service.login(new String[] {userid, userpw});
		
		verify(dao, times(1)).findUser(Mockito.any());
		assertThat(result, is(userInfo.getGrade()));
	}

}
