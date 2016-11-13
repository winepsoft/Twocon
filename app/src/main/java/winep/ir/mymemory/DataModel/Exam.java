package winep.ir.mymemory.DataModel;

/**
 * Created by ShaisteS on 11/12/2016.
 */
public class Exam {
    private Question question;
    private String  answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private int userSelectAnswer; //default=0
    private int answerTrue;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public int getUserSelectAnswer() {
        return userSelectAnswer;
    }

    public void setUserSelectAnswer(int userSelectAnswer) {
        this.userSelectAnswer = userSelectAnswer;
    }

    public int getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(int answerTrue) {
        this.answerTrue = answerTrue;
    }
}
