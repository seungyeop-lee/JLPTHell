package server.service;

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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServerConfigration.class)
@Transactional
public class ReviewUIServiceTest {

    @Autowired
    @InjectMocks
    private ReviewUIService service;

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
    private Set<Integer> studyWordNo;
    private List<Study> studies;

    @Before
    public void setUp() throws Exception {
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
        studyWordNo = new HashSet<>(Arrays.asList(wordNo1, wordNo2));
        studies = Arrays.asList(s1, s2, s3);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getReviewWord() {
        when(userInfoDao.findUser(new UserInfo(id))).thenReturn(userInfo);
        when(studyDao.findStudy(new Study(userNo))).thenReturn(Arrays.asList(s1, s2, s3));
        when(vocaDao.findVoca(new Voca(wordNo1, grade))).thenReturn(v1);
        when(vocaDao.findVoca(new Voca(wordNo2, grade))).thenReturn(v2);

        List<UserWord> userWords = service.getReviewWord(id, grade);

        assertThat(userWords.size(), is(2));
        verify(userInfoDao, times(1)).findUser(any());
        verify(studyDao, times(1)).findStudy(any());
        verify(vocaDao, times(2)).findVoca(any());
    }

    @Test
    public void saveReviewWord() {
        when(userInfoDao.findUser(new UserInfo(id))).thenReturn(userInfo);
        when(studyDao.findStudy(new Study(userNo))).thenReturn(studies);

        service.saveReviewWord(studyWordNo, id);

        verify(userInfoDao, times(1)).findUser(any());
        ArgumentCaptor<Study> studyArg = ArgumentCaptor.forClass(Study.class);
        verify(studyDao, times(2)).updateStudy(studyArg.capture());
        List<Study> updatedStudies = studyArg.getAllValues();
        s1.setScount(String.valueOf(Integer.parseInt(s1.getScount()) + 1));
        s2.setScount(String.valueOf(Integer.parseInt(s2.getScount()) + 1));
        checkSameStudy(updatedStudies.get(0), s1);
        checkSameStudy(updatedStudies.get(1), s2);
    }

    private void checkSameStudy(Study s1, Study s2) {
        assertThat(s1.getUserno(), is(s2.getUserno()));
        assertThat(s1.getGrade(), is(s2.getGrade()));
        assertThat(s1.getWordno(), is(s2.getWordno()));
        assertThat(s1.getScount(), is(s2.getScount()));
    }
}