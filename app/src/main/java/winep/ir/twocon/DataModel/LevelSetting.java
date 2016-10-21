package winep.ir.twocon.DataModel;

/**
 * Created by ShaisteS on 10/19/2016.
 */
public class LevelSetting {
    private int levelNumber;
    private int levelTimeType; //0=month , 1=day
    private int levelTime;

    public LevelSetting(int levelNumber,int levelTimeType,int levelTime){
        this.levelNumber=levelNumber;
        this.levelTimeType=levelTimeType;
        this.levelTime=levelTime;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelTimeType() {
        return levelTimeType;
    }

    public void setLevelTimeType(int levelTimeType) {
        this.levelTimeType = levelTimeType;
    }

    public int getLevelTime() {
        return levelTime;
    }

    public void setLevelTime(int levelTime) {
        this.levelTime = levelTime;
    }
}
