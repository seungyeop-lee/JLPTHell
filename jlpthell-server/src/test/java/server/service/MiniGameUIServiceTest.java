package server.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import server.dao.StudyDao;
import server.dao.UserInfoDao;
import server.dao.VocaDao;
import server.dto.Study;
import server.dto.UserInfo;
import server.dto.Voca;
import vo.UserWord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MiniGameUIServiceTest {

    @Autowired
    @InjectMocks
    private MiniGameUIService service;

    @Mock
    private UserInfoDao userInfoDao;

    @Mock
    private StudyDao studyDao;

    @Mock
    private VocaDao vocaDao;

    private String id;
    private String grade;
    private int userno;
    private UserInfo userInfo;
    private String scount;
    private List<Study> studies;
    private Map<Integer, Voca> vocas;

    @Before
    public void setUp() {
        id = "testId";
        grade = "N1";
        userno = 10;
        userInfo = new UserInfo(id, null, null, grade, null, userno);

        studies = new ArrayList<>();
        vocas = new HashMap<>();

        IntStream.rangeClosed(1, 10).forEach(i -> {
            studies.add(new Study(userno, grade, i, scount));
            vocas.put(i, new Voca(i, "word" + i, "mean" + i, grade));
        });

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getGameWordList() {
        when(userInfoDao.findUser(new UserInfo(id))).thenReturn(userInfo);
        when(studyDao.findStudy(new Study(userno))).thenReturn(studies);
        IntStream.rangeClosed(1, 10).forEach(i -> {
            when(vocaDao.findVoca(new Voca(i, grade))).thenReturn(vocas.get(i));
        });

        List<UserWord> result = service.getGameWordList(id);

        assertThat(result.size(), is(10));
        verify(userInfoDao, times(1)).findUser(any());
        verify(studyDao, times(1)).findStudy(any());
        verify(vocaDao, times(10)).findVoca(any());
    }

    @Test
    public void getNoGameWordList() {
        when(userInfoDao.findUser(new UserInfo(id))).thenReturn(userInfo);
        when(studyDao.findStudy(new Study(userno))).thenReturn(new ArrayList<>());

        List<UserWord> result = service.getGameWordList(id);

        assertThat(result.size(), is(0));
        verify(userInfoDao, times(1)).findUser(any());
        verify(studyDao, times(1)).findStudy(any());
        verify(vocaDao, times(0)).findVoca(any());
    }
}