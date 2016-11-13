package winep.ir.mymemory.DataModel;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class Level {
    private int levelNumber;
    private int levelTotalQuestion;
    private int levelReadyQuestion;
    private int levelStatus; //0=level is ready(green) 1== level is waite(red) 2== no question in level(gray)

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelTotalQuestion() {
        return levelTotalQuestion;
    }

    public void setLevelTotalQuestion(int levelTotalQuestion) {
        this.levelTotalQuestion = levelTotalQuestion;
    }

    public int getLevelReadyQuestion() {
        return levelReadyQuestion;
    }

    public void setLevelReadyQuestion(int levelReadyQuestion) {
        this.levelReadyQuestion = levelReadyQuestion;
    }

    public int getLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(int levelStatus) {
        this.levelStatus = levelStatus;
    }
}
