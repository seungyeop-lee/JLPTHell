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
public class MiniGameUIService {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    StudyDao studyDao;

    @Autowired
    VocaDao vocaDao;

    /**
     * 학습한 단어 중 미니게임에서 사용 할 단어 검색 및 반환
     * @param 사용자의 id
     * @param 사용자의 grade
     * @return 미니게임에서 사용할 단어
     */
    public List<UserWord> getGameWordList(String id) {

        UserInfo userInfo = userInfoDao.findUser(new UserInfo(id));
        List<Study> currentGradeStudiedList = getCurrentGradeStudiedList(userInfo);

        //학습한 단어의 총 갯수가 10개 이상인지 확인
        if(currentGradeStudiedList.size() < 10) {
            return new ArrayList<>();
        }

        //random함수를 이용한 게임에 사용 할 wordNo 난수 발생
        Set<Study> gameStudySet = getGameStudySet(currentGradeStudiedList);

        return getGameUserWord(gameStudySet);
    }

    //사용자가 기존에 학습한 단어 정보를 저장
    private List<Study> getCurrentGradeStudiedList(UserInfo userInfo) {
        List<Study> studiedList = studyDao.findStudy(new Study(userInfo.getUserno()));

        List<Study> currentGradeStudiedList = new ArrayList<>();
        String currentGrade = userInfo.getGrade();
        for(Study study : studiedList) {
            if(study.getGrade().equals(currentGrade)) {
                currentGradeStudiedList.add(study);
            }
        }
        return currentGradeStudiedList;
    }

    private Set<Study> getGameStudySet(List<Study> currentGradeStudiedList) {
        Set<Study> gameStudySet = new HashSet<>();
        int currentGradeStudiedListSize = currentGradeStudiedList.size();
        Random r = new Random();
        while(gameStudySet.size() < 10) {
            Study temp = currentGradeStudiedList.get(r.nextInt(currentGradeStudiedListSize));
            gameStudySet.add(temp);
        }
        return gameStudySet;
    }

    //단어장에서 게임에 사용 할 단어를 불러와서 ArrayList에 담기
    private List<UserWord> getGameUserWord(Set<Study> gameStudySet) {
        ArrayList<UserWord> result = new ArrayList<>();
        for(Study study : gameStudySet) {
            Voca temp = vocaDao.findVoca(new Voca(study.getWordno(), study.getGrade()));
            result.add(new UserWord(temp.getNo(), temp.getWord(), temp.getMean()));
        }

        return result;
    }
}
