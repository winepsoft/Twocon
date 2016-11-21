package winep.ir.mymemory.Presenter.ObserverPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaisteS on 11/21/2016.
 */
public class setClickOnKnowButton {

    private static Boolean clickOnKnowButton;
    private final static List<setClickOnKnowButtonListener> clickOnKnowButtonListenerList =new ArrayList<>();

    public static Boolean getClickOnAnswerStatus() {
        return clickOnKnowButton;
    }
    public static void setClickOnAnswerStatus(Boolean clickOnKnowButtonStatus) {
        setClickOnKnowButton.clickOnKnowButton = clickOnKnowButtonStatus;
        for (setClickOnKnowButtonListener clickOnAnswer : clickOnKnowButtonListenerList) {
            clickOnAnswer.clickOnKnowButton();
        }
    }

    public static void setClickOnKnowButtonListener(setClickOnKnowButtonListener click){
        clickOnKnowButtonListenerList.add(click);
    }
}
