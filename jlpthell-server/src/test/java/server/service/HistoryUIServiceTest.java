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
import server.dao.VocaDao;
import server.dto.Study;
import server.dto.UserInfo;
import server.dto.Voca;
import vo.UserWord;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class HistoryUIServiceTest {

    @Autowired
    @InjectMocks
    private HistoryUIService service;

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
    private int wordNo3;
    private UserInfo userInfo;
    private Study s1;
    private Study s2;
    private Study s3;
    private Voca v1;
    private Voca v2;
    private Voca v3;
    private List<Study> studies;

    @Before
    public void setUp() {
        id = "testId1";
        grade = "N1";
        userNo = 100;
        wordNo1 = 1;
        wordNo2 = 2;
        wordNo3 = 3;
        userInfo = new UserInfo(id, null, null, grade, null, userNo);
        s1 = new Study(userNo, grade, wordNo1, "0");
        s2 = new Study(userNo, grade, wordNo2, "3");
        s3 = new Study(userNo, grade, wordNo3, "5");
        v1 = new Voca(wordNo1, "word1", "mean1", grade);
        v2 = new Voca(wordNo2, "word2", "mean2", grade);
        v3 = new Voca(wordNo3, "word3", "mean3", grade);
        studies = Arrays.asList(s1, s2, s3);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userWordList() {
        when(userInfoDao.findUser(new UserInfo(id))).thenReturn(userInfo);
        when(studyDao.findStudy(new Study(userNo, grade))).thenReturn(studies);
        when(vocaDao.findVoca(new Voca(wordNo1, grade))).thenReturn(v1);
        when(vocaDao.findVoca(new Voca(wordNo2, grade))).thenReturn(v2);
        when(vocaDao.findVoca(new Voca(wordNo3, grade))).thenReturn(v3);

        List<UserWord> userWords = service.userWordList(id);

        assertThat(userWords.size(), is(3));
    }

    @Test
    public void emptyUserWordList() {
        when(userInfoDao.findUser(new UserInfo(id))).thenReturn(userInfo);
        when(studyDao.findStudy(new Study(userNo, grade))).thenReturn(Collections.emptyList());

        List<UserWord> userWords = service.userWordList(id);

        assertThat(userWords.size(), is(0));
    }

}