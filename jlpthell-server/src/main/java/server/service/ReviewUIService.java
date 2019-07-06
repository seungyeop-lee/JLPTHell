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

import java.util.*;

@Service
public class ReviewUIService {

    @Autowired
    private StudyDao studyDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private VocaDao vocaDao;

    /**
     * Client에 학습할 단어 List를 보낸다.
     *
     * @param id 사용자의 id
     * @param grade 사용자의 grade
     * @return 학습할 단어가 담겨있는 ArrayList
     */
    public List<UserWord> getReviewWord(String id, String grade) {
        List<Study> studied = getStudiedIndex(id, grade);

        List<Study> study = getStudyIndex(studied);

        return getWordList(study);
    }

    //사용자가 기존에 학습한 단어의 index를 획득
    private List<Study> getStudiedIndex(String id, String grade) {
        List<Study> currentGradeStudied = new ArrayList<>();

        UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));
        List<Study> studiedList = studyDao.findStudy(new Study(userInfo.getUserno()));

        for(Study studied : studiedList) {
            if(studied.getGrade().equals(grade) && Integer.parseInt(studied.getScount()) != 5) {
                currentGradeStudied.add(studied);
            }
        }
        return currentGradeStudied;
    }

    //random함수를 이용한 학습할 단어 wordNo 난수 발생
    private List<Study> getStudyIndex(List<Study> studied) {
        //복습 할 단어갯수 지정
        int studySize = 20;
        int studied_Size = studied.size();
        if(studied_Size < studySize) {
            studySize = studied_Size;
        }

        //복습 할 단어 선정 및 반환
        Set<Study> study = new HashSet<>();
        Random r = new Random();
        while(study.size() < studySize) {
            int temp = r.nextInt(studied_Size);
            study.add(studied.get(temp));
        }
        return new ArrayList<>(study);
    }

    private List<UserWord> getWordList(List<Study> studyList) {
        List<UserWord> userWords = new ArrayList<>();
        for(Study study : studyList) {
            Voca voca = vocaDao.findVoca(new Voca(study.getWordno(), study.getGrade()));
            userWords.add(new UserWord(
                    voca.getNo(), voca.getWord(), voca.getMean(), Integer.parseInt(study.getScount())));
        }
        return userWords;
    }

    /**
     * 사용자가 학습한 index를 study 테이블에 저장
     *
     * @param reviewWordNo 사용자가 학습한 wordno가 담겨있는 reviewWordNo
     */
    public void saveReviewWord(Set<Integer> reviewWordNo, String id) {
        UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));

        List<Study> studies = studyDao.findStudy(new Study(userInfo.getUserno()));
        List<Study> reviewedStudies = new ArrayList<>();

        for(Study study : studies) {
            if (study.getGrade().equals(userInfo.getGrade()) && reviewWordNo.contains(study.getWordno())) {
                int increasedSCount = Integer.parseInt(study.getScount()) + 1;
                String strIncreasedSCount = String.valueOf(increasedSCount);
                Study scountUpStudy = new Study(userInfo.getUserno(), userInfo.getGrade(), study.getWordno(), strIncreasedSCount);
                reviewedStudies.add(scountUpStudy);
            }
        }

        for(Study study : reviewedStudies) {
            studyDao.updateStudy(study);
        }
    }
}
