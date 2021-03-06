package winep.ir.mymemory.Presenter;

import android.content.Context;

import com.github.lzyzsd.randomcolor.RandomColor;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.Course;
import winep.ir.mymemory.DataModel.FlashCart;
import winep.ir.mymemory.DataModel.Question;
import winep.ir.mymemory.DataModel.SharedFlashCard;
import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 11/17/2016.
 */
public class ServerConnectionHandler {

    private Context context;

    public ServerConnectionHandler(Context context){
        this.context=context;

    }

    public String[] createListOfGroupsTitle(){
        String[] groupsTitle=new String[4];
        groupsTitle[0]=context.getString(R.string.filter_all);
        groupsTitle[1]="پزشکی";
        groupsTitle[2]="مهندسی پزشکی";
        groupsTitle[3]="English title";
        return groupsTitle;
    }

    public String[] createListOfSubGroupsOfAGroup(){
        String[] subGroupTitle=new String[3];
        subGroupTitle[0]=context.getString(R.string.filter_all);
        subGroupTitle[1]="فیزیولوژی";
        subGroupTitle[2]="دهان و دندان";
        return subGroupTitle;
    }

    public String[] createListOfCourseOfSubGroup(){
        String[] courseTitle=new String[3];
        courseTitle[0]=context.getString(R.string.filter_all);
        courseTitle[1]="زبان";
        courseTitle[2]="دندان جلو";
        return courseTitle;
    }

    public ArrayList<Course> createCourse(){

        ArrayList<Course> allCourse=new ArrayList();
        RandomColor rnd1 = new RandomColor();
        int color1 = rnd1.randomColor();
        Course course1=new Course(0);
        course1.setTitle("زبان");
        course1.setDescription("توضیحاتی در مورد این بخش");
        course1.setColor(color1);
        allCourse.add(course1);

        RandomColor rnd2 = new RandomColor();
        int color2 = rnd2.randomColor();
        Course course2=new Course(1);
        course2.setTitle("دندان جلو");
        course2.setDescription("توضیحاتی در مورد این بخش");
        course2.setColor(color2);
        allCourse.add(course2);
        return allCourse;

    }

    public ArrayList<FlashCart> createFlashCart(){
        ArrayList<FlashCart> flashCarts=new ArrayList<>();
        FlashCart flashCart=new FlashCart();
        flashCart.setTitle("دندانپزشکی پیشرفته");
        flashCart.setDownloadSize("1 مگ");
        flashCart.setMainPrice("0");
        flashCart.setPurchasePrice("1000 تومان");
        flashCart.setNumberOfDownload("47");
        flashCart.setNumberOfVisit("199");
        flashCarts.add(flashCart);

        FlashCart flashCart1=new FlashCart();
        flashCart1.setTitle("نام حیوانات");
        flashCart1.setDownloadSize("1 مگ");
        flashCart1.setMainPrice("1000 تومان");
        flashCart1.setPurchasePrice("500 تومان");
        flashCart1.setNumberOfDownload("47");
        flashCart1.setNumberOfVisit("199");
        flashCarts.add(flashCart1);

        FlashCart flashCart2=new FlashCart();
        flashCart2.setTitle("world people");
        flashCart2.setDownloadSize("1M");
        flashCart2.setMainPrice("1000 تومان");
        flashCart2.setPurchasePrice("500 تومان");
        flashCart2.setNumberOfDownload("47");
        flashCart2.setNumberOfVisit("199");
        flashCarts.add(flashCart2);
        return flashCarts;
    }

    public ArrayList<SharedFlashCard> createSharedFlashCards(){
        ArrayList<SharedFlashCard> sharedFlashCards=new ArrayList<>();
        SharedFlashCard sharedFlashCard=new SharedFlashCard();
        sharedFlashCard.setUserName("علی");
        FlashCart flashCart=new FlashCart();
        flashCart.setTitle("دندانپزشکی پیشرفته");
        flashCart.setDownloadSize("حجم: 1 مگ");
        flashCart.setMainPrice("0");
        flashCart.setPurchasePrice("1000 تومان");
        sharedFlashCard.setFlashCard(flashCart);
        sharedFlashCards.add(sharedFlashCard);

        SharedFlashCard sharedFlashCard2=new SharedFlashCard();
        sharedFlashCard2.setUserName("Mohammad");
        FlashCart flashCart2=new FlashCart();
        flashCart2.setTitle("world people");
        flashCart2.setDownloadSize("size:1M");
        flashCart2.setMainPrice("1000 تومان");
        flashCart2.setPurchasePrice("500 تومان");
        sharedFlashCard2.setFlashCard(flashCart2);
        sharedFlashCards.add(sharedFlashCard2);
        return sharedFlashCards;
    }

    public ArrayList<Question> createQuestion(){

        ArrayList<Question> allQuestions=new ArrayList<>();
        Question question1=new Question();
        question1.setQuestionTitle("Question1?");
        question1.setQuestionAnswer("Answer Of Question 1");
        question1.setQuestionDescription("Description Question 1");
        allQuestions.add(question1);

        Question question2=new Question();
        question2.setQuestionTitle("Question2?");
        question2.setQuestionAnswer("Answer Of Question 2");
        question2.setQuestionDescription("Description Question 2");
        allQuestions.add(question2);

        Question question3=new Question();
        question3.setQuestionTitle("Question3?");
        question3.setQuestionAnswer("Answer Of Question 3");
        question3.setQuestionDescription("Description Question 3");
        allQuestions.add(question3);

        Question question4=new Question();
        question4.setQuestionTitle("Question4?");
        question4.setQuestionAnswer("Answer Of Question 4");
        question4.setQuestionDescription("Description Question 4");
        allQuestions.add(question4);


        Question question5=new Question();
        question5.setQuestionTitle("Question5?");
        question5.setQuestionAnswer("Answer Of Question 5");
        question5.setQuestionDescription("Description Question 5");
        allQuestions.add(question5);


        return allQuestions;
    }

}
