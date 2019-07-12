package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.dao.StudyDao;
import server.dao.UserInfoDao;
import server.dao.VocaDao;
import server.dto.Study;
import server.dto.UserInfo;
import server.dto.Voca;
import vo.UserWord;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryUIService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private StudyDao studyDao;

    @Autowired
    private VocaDao vocaDao;

    //현재 사용자의 학습 이력 검색 메소드, 학습 이력을 UserWord객체에 담고, arraylist에 넣어서 반환
    public ArrayList<UserWord> userWordList(String id) {
        ArrayList<UserWord> result = new ArrayList<>();

        UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));

        //사용자가 학습한 이력 검색
        List<Study> studyList = studyDao.findStudy(new Study(userInfo.getUserno(), userInfo.getGrade()));

        //학습한 단어의 wordno와 grade를 이용하여 실제 학습한 단어와 뜻을 검색
        for (Study study : studyList) {
            Voca voca = getVoca(study);
            result.add(new UserWord(voca.getWord(), voca.getMean(), Integer.parseInt(study.getScount())));
        }

        return result;
    }

    private Voca getVoca(Study study) {
        return vocaDao.findVoca(new Voca(study.getWordno(), study.getGrade()));
    }

}
