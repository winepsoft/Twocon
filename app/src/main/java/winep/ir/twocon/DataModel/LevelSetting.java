package winep.ir.twocon.DataModel;

/**
 * Created by ShaisteS on 10/19/2016.
 */
public class LevelSetting {
    private int levelNumber;
    private int levelDay;
    private int levelHour;

    public LevelSetting(int levelNumber,int levelDay,int levelHour){
        this.levelNumber=levelNumber;
        this.levelDay =levelDay;
        this.levelHour=levelHour;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelDay() {
        return levelDay;
    }

    public void setLevelDay(int levelDay) {
        this.levelDay = levelDay;
    }

    public int getLevelHour() {
        return levelHour;
    }

    public void setLevelHour(int levelHour) {
        this.levelHour = levelHour;
    }
}
