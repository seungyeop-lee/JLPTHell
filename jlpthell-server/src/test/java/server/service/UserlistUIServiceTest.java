package server.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import server.dto.Study;
import server.dto.UserInfo;
import vo.UserForList;

import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class UserlistUIServiceTest {

    @Autowired
    @InjectMocks
    private UserlistUIService service;

    @Mock
    private UserInfoDao userInfoDao;

    @Mock
    private StudyDao studyDao;

    private List<UserInfo> userInfos;
    private Map<Integer, List<Study>> userStudyRecords;

    @Before
    public void setUp() {
        UserInfo u1 = new UserInfo("id1", "pw1", "hint1", "N1", null, 1);
        UserInfo u2 = new UserInfo("id2", "pw2", "hint2", "N2", null, 2);
        UserInfo u3 = new UserInfo("id3", "pw3", "hint3", "N3", null, 3);
        UserInfo u4 = new UserInfo("id4", "pw4", "hint4", "N4", null, 4);
        UserInfo u5 = new UserInfo("id4", "pw4", "hint4", "N4", null, 5);
        userInfos = Arrays.asList(u1, u2, u3, u4, u5);

        userStudyRecords = new HashMap<>();
        for(UserInfo userInfo : userInfos) {
            List<Study> studies = new ArrayList<>();
            for (int count : IntStream.rangeClosed(1, 5).toArray()) {
                studies.add(new Study(userInfo.getUserno(), userInfo.getGrade(), 0, String.valueOf(count)));
            }
            userStudyRecords.put(userInfo.getUserno(), studies);
        }

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userList() {
        when(userInfoDao.findAll()).thenReturn(userInfos);
        for(UserInfo userInfo : userInfos) {
            int userno = userInfo.getUserno();
            when(studyDao.findStudy(new Study(userno))).thenReturn(userStudyRecords.get(userno));
        }

        List<UserForList> userLists = service.userList();

        assertThat(userLists.size(), is(5));
        for(UserForList userForList : userLists) {
            assertThat(userForList.getStudyingCount(), is(4));
            assertThat(userForList.getStudiedCount(), is(1));
        }

    }
}