package winep.ir.twocon.Presenter.ExamPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rey.material.widget.Button;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.DataModel.Exam;
import winep.ir.twocon.DataModel.Question;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/14/2016.
 */
public class ExamActivity extends AppCompatActivity {

    private ArrayList<Exam> exam;
    private Context context;
    private Button btnExamEnd;
    private Button btnExamNext;
    private Button btnExamPreview;
    private TextView txtExamProcess;
    private ProgressView progressBarExamProcess;
    private int previewPage=0;
    private int allQuestionTest=0;
    private int currentQuestion=0;
    private double percent;
    private ViewPager examViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);
        allQuestionTest=getIntent().getExtras().getInt("examQuestionNumber");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.exam_activity_title));
        context=this;

        exam=new ArrayList<>();
        examViewPager=(ViewPager)findViewById(R.id.exam_pager);
        //examViewPager.setOffscreenPageLimit(1);
        examViewPager.setAdapter(new ExamPagerAdapter(createExam(allQuestionTest),this));


        btnExamEnd=(Button)findViewById(R.id.exam_end);
        btnExamNext=(Button)findViewById(R.id.exam_next);
        btnExamPreview=(Button)findViewById(R.id.exam_preview);
        progressBarExamProcess =(ProgressView)findViewById(R.id.progress_bar_exam_process);
        txtExamProcess=(TextView)findViewById(R.id.txt_exam_process);
        Utilities.getInstance().setFontTextView(context,txtExamProcess);


        if (allQuestionTest!=0) {
            percent = (double) 1 / (double) allQuestionTest;
            btnExamPreview.setVisibility(View.GONE);
        }
        else
            percent=0;

        progressBarExamProcess.setProgress(0);
        progressBarExamProcess.setSecondaryProgress(allQuestionTest);
        txtExamProcess.setText(Integer.toString(currentQuestion)+"/"+Integer.toString(allQuestionTest));

        examViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(previewPage<position)
                    nextQuestion();
                else if(previewPage>position)
                    previewQuestion();

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state== ViewPager.SCROLL_STATE_DRAGGING)
                    previewPage=examViewPager.getCurrentItem();

            }
        });

        btnExamNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examViewPager.setCurrentItem(examViewPager.getCurrentItem()+1);
                nextQuestion();

            }
        });

        btnExamPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examViewPager.setCurrentItem(examViewPager.getCurrentItem()-1);
                previewQuestion();

            }
        });

    }

    private ArrayList<Exam> createExam(int examQuestionNumber){
        for (int i=0;i<examQuestionNumber;i++){
            Question question=new Question();
            Exam aQuestion=new Exam();
            aQuestion.setQuestion(question);
            aQuestion.setAnswerOne("Answer1");
            aQuestion.setAnswerTwo("Answer2");
            aQuestion.setAnswerThree("Answer3");
            aQuestion.setAnswerFour("Answer4");
            aQuestion.setUserSelectAnswer(0);
            exam.add(aQuestion);
        }

        /*Question question=new Question();
        Exam aQuestion=new Exam();
        aQuestion.setQuestion(question);
        aQuestion.setAnswerOne("Answer1");
        aQuestion.setAnswerTwo("Answer2");
        aQuestion.setAnswerThree("Answer3");
        aQuestion.setAnswerFour("Answer4");
        aQuestion.setUserSelectAnswer(0);
        exam.add(aQuestion);

        Question question1=new Question();
        Exam aQuestion1=new Exam();
        aQuestion1.setQuestion(question1);
        aQuestion1.setAnswerOne("Answer1");
        aQuestion1.setAnswerTwo("Answer2");
        aQuestion1.setAnswerThree("Answer3");
        aQuestion1.setAnswerFour("Answer4");
        aQuestion1.setUserSelectAnswer(0);
        exam.add(aQuestion1);

        Question question2=new Question();
        Exam aQuestion2=new Exam();
        aQuestion2.setQuestion(question2);
        aQuestion2.setAnswerOne("Answer1");
        aQuestion2.setAnswerTwo("Answer2");
        aQuestion2.setAnswerThree("Answer3");
        aQuestion2.setAnswerFour("Answer4");
        aQuestion2.setUserSelectAnswer(0);
        exam.add(aQuestion2);

        Question question3=new Question();
        Exam aQuestion3=new Exam();
        aQuestion3.setQuestion(question3);
        aQuestion3.setAnswerOne("Answer1");
        aQuestion3.setAnswerTwo("Answer2");
        aQuestion3.setAnswerThree("Answer3");
        aQuestion3.setAnswerFour("Answer4");
        aQuestion3.setUserSelectAnswer(0);
        exam.add(aQuestion3);

        Question question4=new Question();
        Exam aQuestion4=new Exam();
        aQuestion4.setQuestion(question4);
        aQuestion4.setAnswerOne("Answer1");
        aQuestion4.setAnswerTwo("Answer2");
        aQuestion4.setAnswerThree("Answer3");
        aQuestion4.setAnswerFour("Answer4");
        aQuestion4.setUserSelectAnswer(0);
        exam.add(aQuestion4);*/
        return exam;

    }

    private void nextQuestion(){
        if (currentQuestion<allQuestionTest){
            currentQuestion=currentQuestion+1;
            txtExamProcess.setText(Integer.toString(currentQuestion)+"/"+Integer.toString(allQuestionTest));
            progressBarExamProcess.setProgress(progressBarExamProcess.getProgress()+(float) percent);
            if (currentQuestion==1)
                btnExamPreview.setVisibility(View.VISIBLE);
        }
        else
            btnExamNext.setVisibility(View.GONE);

    }

    private void previewQuestion(){
        if (currentQuestion>0){
            currentQuestion=currentQuestion-1;
            txtExamProcess.setText(currentQuestion+"/"+allQuestionTest);
            progressBarExamProcess.setProgress(progressBarExamProcess.getProgress()-(float) percent);
            if (currentQuestion<allQuestionTest)
                btnExamNext.setVisibility(View.VISIBLE);

        }
        else
            btnExamPreview.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.title_back_menu, menu);
        if(Utilities.getInstance().isRTL()) {
            menu.getItem(0).setIcon(R.mipmap.back_fa);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        else {
            menu.getItem(0).setVisible(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==android.R.id.home)
            finish();
        if (id==R.id.action_back)
            finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }
}
