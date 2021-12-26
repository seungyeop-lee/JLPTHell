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
import server.dto.UserInfo;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class UserInfoDaoImplTest {

    @Autowired
    private UserInfoDao dao;

    private UserInfo u1;
    private UserInfo u2;

    @Before
    public void setUp() {
        u1 = new UserInfo("id1", "pw1", "h1", "g1", null, 0);
        u2 = new UserInfo("id2", "pw2", "h2", "g2", null, 0);
    }

    @Test
    public void findUser() {
    	UserInfo findUser1 = dao.findUser(new UserInfo("id1"));
    	checkSameUser(findUser1, new UserInfo());
    	
        dao.addUser(u1);
        dao.addUser(u2);
        assertThat(dao.findAll().size(), is(2));
        
        UserInfo findUser2 = dao.findUser(new UserInfo("id1"));
        checkSameUser(findUser2, u1);
    }

    @Test
    public void findUserByUseridAndUserpw() {
        UserInfo findUser1 = dao.findUserByUseridAndUserpw(new UserInfo("id1", "pw1"));
        checkSameUser(findUser1, new UserInfo());

        dao.addUser(u1);
        dao.addUser(u2);
        assertThat(dao.findAll().size(), is(2));

        UserInfo findUser2 = dao.findUserByUseridAndUserpw(new UserInfo("id1"));
        checkSameUser(findUser2, new UserInfo());

        UserInfo findUser3 = dao.findUserByUseridAndUserpw(new UserInfo("id1", "invalid"));
        checkSameUser(findUser3, new UserInfo());

        UserInfo findUser4 = dao.findUserByUseridAndUserpw(new UserInfo("id1", "pw1"));
        checkSameUser(findUser4, u1);
    }
    
    @Test
    public void addUser() {
    	dao.addUser(u1);
    	dao.addUser(u2);
    	List<UserInfo> userinfos = dao.findAll();
    	assertThat(userinfos.size(), is(2));
    }
    
    @Test
    public void findAll() {
    	assertThat(dao.findAll().size(), is(0));
    	
    	dao.addUser(u1);
    	List<UserInfo> userinfos1 = dao.findAll();
    	assertThat(userinfos1.size(), is(1));
    	checkSameUser(userinfos1.get(0), u1);
    	
    	dao.addUser(u2);
    	List<UserInfo> userinfos2 = dao.findAll();
    	assertThat(userinfos2.size(), is(2));
    	checkSameUser(userinfos2.get(0), u1);
    	checkSameUser(userinfos2.get(1), u2);
    }

    private void checkSameUser(UserInfo u1, UserInfo u2) {
        assertThat(u1.getUserid(), is(u2.getUserid()));
        assertThat(u1.getUserpw(), is(u2.getUserpw()));
        assertThat(u1.getPwhint(), is(u2.getPwhint()));
        assertThat(u1.getGrade(), is(u2.getGrade()));
    }

}
