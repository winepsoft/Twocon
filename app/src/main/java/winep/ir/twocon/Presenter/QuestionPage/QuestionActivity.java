      package winep.ir.twocon.Presenter.QuestionPage;

      import android.content.Context;
      import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
      import android.view.Menu;

      import java.util.ArrayList;

      import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
      import winep.ir.twocon.DataModel.Question;
import winep.ir.twocon.R;
      import winep.ir.twocon.Utility.Utilities;

      /**
 * Created by ShaisteS on 10/14/2016.
 */
public class QuestionActivity extends AppCompatActivity {

    private ArrayList<Question> allQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.question_activity_title));
        allQuestions=new ArrayList<>();
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new QuestionPagerAdapter(createQuestion(),this));
    }

    private ArrayList<Question> createQuestion(){
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

    @Override
    public void onBackPressed()
    {
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
      super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
      Utilities.getInstance().setSettingLanguage(newBase);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.title_back_menu, menu);
      return true;
    }
}

