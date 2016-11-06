package winep.ir.twocon.DataModel;

/**
 * Created by ShaisteS on 11/6/2016.
 */
public class Reminder {
    private String day;
    private String hour;
    private String minute;
    private String timeType; //am or pm

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }
}
