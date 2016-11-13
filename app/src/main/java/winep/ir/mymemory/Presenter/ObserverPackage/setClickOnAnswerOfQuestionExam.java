package winep.ir.mymemory.Presenter.ObserverPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaisteS on 11/13/2016.
 */
public class setClickOnAnswerOfQuestionExam {

    private static Boolean clickOnAnswerStatus;
    private final static List<setClickOnAnswerOfQuestionExamListener> clickOnAnswerAllListener =new ArrayList<>();

    public static Boolean getClickOnAnswerStatus() {
        return clickOnAnswerStatus;
    }
    public static void setClickOnAnswerStatus(Boolean changeFragmentParamet) {
        setClickOnAnswerOfQuestionExam.clickOnAnswerStatus = changeFragmentParamet;
        for (setClickOnAnswerOfQuestionExamListener clickOnAnswer : clickOnAnswerAllListener) {
            clickOnAnswer.clickOn();
        }
    }

    public static void setClickOnAnswerOfQuestionExamListener(setClickOnAnswerOfQuestionExamListener click){
        clickOnAnswerAllListener.add(click);
    }
}
