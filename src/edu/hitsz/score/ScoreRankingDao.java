package edu.hitsz.score;

import java.util.List;

public interface ScoreRankingDao {
    List<ScoreRanking> getAllScores();
    void doAdd(ScoreRanking scoreRanking);
    void doDelete(String time);
}
