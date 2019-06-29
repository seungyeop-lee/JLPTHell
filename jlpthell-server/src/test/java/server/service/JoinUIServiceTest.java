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
import server.dao.UserInfoDao;
import server.dto.UserInfo;
import server.vo.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class JoinUIServiceTest {
	
	@Autowired
	@InjectMocks
	private JoinUIService service;
	
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
	public void join() {
		when(dao.addUser(userInfo)).thenReturn(1);
		
		boolean result = service.join(new User(userid, userpw, grade, pwhint));
		
		verify(dao, times(1)).addUser(Mockito.any());
		assertThat(result, is(true));
	}
	
	@Test
	public void joinDuplicate() {
		when(dao.addUser(userInfo)).thenReturn(0);
		
		boolean result = service.join(new User(userid, userpw, grade, pwhint));
		
		verify(dao, times(1)).addUser(Mockito.any());
		assertThat(result, is(false));
	}

}
