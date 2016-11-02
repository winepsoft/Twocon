package winep.ir.twocon.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ShaisteS on 10/16/2016.
 */
public class Question implements Parcelable {
    private String questionTitle;
    private String questionAnswer;
    private String questionDescription;

    public Question(Parcel in) {
        questionTitle = in.readString();
        questionAnswer = in.readString();
        questionDescription = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public Question() {

    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(questionTitle);
        parcel.writeString(questionAnswer);
        parcel.writeString(questionDescription);
    }
}
